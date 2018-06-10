package com.eli.netty.test;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

/**
 * Created by zhouyilin on 2017/9/9.
 */
public class MyTest {
    public static void main(String[] args) {
        int i = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        System.out.println(i);
    }
}
