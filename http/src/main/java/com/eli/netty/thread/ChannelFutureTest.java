package com.eli.netty.thread;

import io.netty.channel.ChannelFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by zhouyilin on 2018/6/2.
 */
public class ChannelFutureTest {

    private volatile static boolean flag = false;

    static class Consumer implements Callable {
        @Override
        public Object call() throws Exception {
            return null;
        }
    }

    class Provider implements Runnable {
        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {


    }
}


