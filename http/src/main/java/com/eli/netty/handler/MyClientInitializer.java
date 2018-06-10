package com.eli.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.net.ServerSocket;

/**
 * Created by zhouyilin on 2018/6/9.
 * 入站事件：Decoder，Handler
 * 出站事件：encoder
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new MyLongToByteEncoder());
        pipeline.addLast(new MyLongToByteEncoder2());
        pipeline.addLast(new MyByteToLongDecoder2());
        pipeline.addLast(new MyLongToStringDecoder());
        pipeline.addLast(new MyClientHandler());
    }
}
