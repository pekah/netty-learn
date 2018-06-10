package com.eli.netty.chat;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Created by zhouyilin on 2018/6/2.
 */
public class MyChannelFutureListener implements GenericFutureListener {

    @Override
    public void operationComplete(Future future) throws Exception {
        if (future.isSuccess()) {
            System.out.println("thread name : " + Thread.currentThread().getName());
        }

    }
}
