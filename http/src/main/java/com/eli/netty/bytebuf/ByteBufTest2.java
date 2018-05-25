package com.eli.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Created by elizhou on 2017/11/30.
 */
public class ByteBufTest2 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("张helloworld", CharsetUtil.UTF_8);

        if(byteBuf.hasArray()){
            // true即为java堆上缓冲，存储在字节数据上。
            byte[] content = byteBuf.array();
            System.out.println(new String(content, CharsetUtil.UTF_8));

            System.out.println("arrayOffset : " + byteBuf.arrayOffset());
            System.out.println("readerIndex : " + byteBuf.readerIndex());
            System.out.println("writerIndex : " + byteBuf.writerIndex());
            // utf编码下，每个字符最多占据3个字节。capacity=张helloworld的字符长度（11）*3=33
            System.out.println("capacity : " + byteBuf.capacity());

            int length = byteBuf.readableBytes();
            System.out.println(length);

            // 因为是单个字节单个字节打印，而中文占据3个字节，所以张会打印3个乱码
            for (int i = 0; i < byteBuf.readableBytes(); i++){
                System.out.println((char)byteBuf.getByte(i));
            }

            // 打印张h
            System.out.println(byteBuf.getCharSequence(0, 4, CharsetUtil.UTF_8));
        }else {
            // 操作系统管理的内存
        }
    }
}
