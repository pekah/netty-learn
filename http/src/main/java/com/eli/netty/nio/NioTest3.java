package com.eli.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhouyilin on 2017/7/16.
 */
public class NioTest3 {
    public static void main(String[] args) throws Exception {

        FileOutputStream fileOutputStream = new FileOutputStream("nio.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] messages = "hello world hi ".getBytes();

        for (int i = 0; i < messages.length; i++){
            byteBuffer.put(messages[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fileChannel.close();

    }
}
