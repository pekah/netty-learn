package com.eli.netty.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by zhouyilin on 2017/8/8.
 */
public class NioTest5 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i < byteBuffer.capacity(); i++){
            byteBuffer.put((byte)i);
        }

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}
