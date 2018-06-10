package com.eli.netty.nio;

import java.nio.IntBuffer;

/**
 * Created by zhouyilin on 2017/7/22.
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(6);
        System.out.println("capacity" + intBuffer.capacity());
        System.out.println("position" + intBuffer.position());
        System.out.println("limit" + intBuffer.limit());

        for (int i = 0; i <= 3; i++){
            intBuffer.put(i);
        }

        System.out.println("-------");
        System.out.println("capacity" + intBuffer.capacity());
        System.out.println("position" + intBuffer.position());
        System.out.println("limit" + intBuffer.limit());


        intBuffer.flip();

        System.out.println("-------");
        System.out.println("capacity" + intBuffer.capacity());
        System.out.println("position" + intBuffer.position());
        System.out.println("limit" + intBuffer.limit());

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

        System.out.println("-------");
        System.out.println("capacity" + intBuffer.capacity());
        System.out.println("position" + intBuffer.position());
        System.out.println("limit" + intBuffer.limit());

        intBuffer.flip();

        System.out.println("-------");
        System.out.println("capacity" + intBuffer.capacity());
        System.out.println("position" + intBuffer.position());
        System.out.println("limit" + intBuffer.limit());

        intBuffer.limit(intBuffer.capacity());

        System.out.println("-------");
        System.out.println("capacity" + intBuffer.capacity());
        System.out.println("position" + intBuffer.position());
        System.out.println("limit" + intBuffer.limit());

        for (int i = 0; i < intBuffer.capacity(); i++){
            intBuffer.put(i);
        }

    }
}
