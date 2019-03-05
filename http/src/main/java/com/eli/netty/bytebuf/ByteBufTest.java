package com.eli.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by elizhou on 2017/11/29.
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(128);

        for(int i = 0; i < 10; i++){
            byteBuf.writeByte(i);
        }

        System.out.println("readrIndex:" + byteBuf.readerIndex());
        System.out.println("writerIndex:" + byteBuf.writerIndex());
        System.out.println("capacity:" + byteBuf.capacity());

        System.out.println("writable bytes length:" + byteBuf.writableBytes());
        System.out.println("max capacity:" + byteBuf.maxCapacity());

        while (byteBuf.isReadable()){
            System.out.println(byteBuf.readByte());
        }

        System.out.println("after readrIndex:" + byteBuf.readerIndex());
        System.out.println("after writerIndex:" + byteBuf.writerIndex());
        System.out.println("after capacity:" + byteBuf.capacity());
    }
}
