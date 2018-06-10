package com.eli.netty.netty_in_action;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by elizhou on 2018/5/25.
 */
@ChannelHandler.Sharable
public class DiscardOutboundHandler extends ChannelOutboundHandlerAdapter{

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ReferenceCountUtil.release(msg); // 释放资源
        promise.setSuccess(); // 通知ChannelPromise数据已经被处理了
    }
}
