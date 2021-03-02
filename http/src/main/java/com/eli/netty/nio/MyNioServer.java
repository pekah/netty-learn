package com.eli.netty.nio;

import io.netty.buffer.ByteBuf;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyNioServer {
    private Selector selector;
    private ExecutorService tp = Executors.newCachedThreadPool();

    public static Map<Socket, Long> timeStat = new HashMap<>(10240);

    private void startServer() throws Exception{
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);// 设置成非阻塞状态

        InetSocketAddress isa = new InetSocketAddress(9000);
        ssc.socket().bind(isa);

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);

        for (;;){
            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator(); // for循环第一次运行到这里的时候，iterator里只有一个SelectionKey，是与当前selector绑定的ssc
            long e = 0;
            while (i.hasNext()) {
                SelectionKey sk = (SelectionKey)i.next();
                i.remove();// 处理完一个SelectionKey之后需要从集合内移除，否则下个for循环又会重复执行。

                if(sk.isAcceptable()) {
                    doAccept(sk);
                }else if(sk.isValid() && sk.isReadable()) {
                    if(!timeStat.containsKey(((SocketChannel)sk.channel()).socket())) {
                        timeStat.put(((SocketChannel)sk.channel()).socket(), System.currentTimeMillis());
                    }
                    doRead(sk);

                }else if(sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = timeStat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend：" + (e-b) + "ms");
                }

            }
        }

    }

    private void doAccept(SelectionKey sk) {
        ServerSocketChannel ssc = (ServerSocketChannel)sk.channel(); // 只有ssc往selector上注册了accept事件
        SocketChannel clientChannel;

        try {
            clientChannel = ssc.accept();
            clientChannel.configureBlocking(false);

            SelectionKey selectionKey = clientChannel.register(selector, SelectionKey.OP_READ);// clientChannel往selector上注册读事件

            EchoClient echoClient = new EchoClient();
            selectionKey.attach(echoClient);

            System.out.println("Accepted connection from" + clientChannel.socket().getInetAddress().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doRead(SelectionKey sk) {
        SocketChannel socketChannel = (SocketChannel)sk.channel(); // 只有SocketChannel在Selector上注册了Read事件
        ByteBuffer bb = ByteBuffer.allocate(8192);

        int len;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    

        try {
            // 从channel中读数据，并把数据写入bytebuffer
            socketChannel.read(bb);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bb.flip(); // 翻转bytebuffer，为后面把bytebuffer中的数据写入channel做准备
        tp.execute(new HandleMsg(sk, bb));
    }


    private void doWrite(SelectionKey sk) {
        SocketChannel channel = (SocketChannel)sk.channel();
        EchoClient echoClient = (EchoClient)sk.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();

        try {
            channel.write(bb);

            if(bb.remaining() == 0) {
                outq.removeLast();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        if(outq.size() == 0) {
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    class HandleMsg implements Runnable {
        SelectionKey sk;
        ByteBuffer bb;
        public HandleMsg(SelectionKey sk, ByteBuffer bb) {
            this.sk = sk;
            this.bb = bb;
        }

        @Override
        public void run() {
            EchoClient echoClient = (EchoClient) sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            selector.wakeup();
        }
    }

}

class EchoClient {
    private LinkedList<ByteBuffer> outq;
    EchoClient() {
        outq = new LinkedList<>();
    }

    public LinkedList<ByteBuffer> getOutputQueue() {
        return outq;
    }

    public void enqueue(ByteBuffer bb) {
        outq.addFirst(bb);
    }
}

