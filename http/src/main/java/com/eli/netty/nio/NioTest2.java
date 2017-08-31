package com.eli.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhouyilin on 2017/7/16.
 */
public class NioTest2 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("nio.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()){
            System.out.println((char)byteBuffer.get());
        }
    }
}
