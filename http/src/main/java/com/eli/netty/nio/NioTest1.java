package com.eli.netty.nio;

import java.nio.IntBuffer;

/**
 * Created by zhouyilin on 2017/7/22.
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("capacity" + intBuffer.capacity());

        for (int i = 0; i < intBuffer.capacity(); i++){
            intBuffer.put(i);
        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
