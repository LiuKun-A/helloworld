package com.liukun.qa1.handler;

import com.liukun.qa1.service.MessageService;
import com.liukun.qa1.service.MyChannelService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理器，根据需要override相应方法
 * 此处只简单处理如下几种情况
 * 1. 连接建立
 * 2. 连接断开
 * 3. 数据读取
 * 4. 异常处理
 */
public class MyHandler extends SimpleChannelInboundHandler<String> {
    // 读取客户端请求
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String userName = getChannelInfo(ctx);
        System.out.println("收到客户端[" + userName + "]的消息: " + msg);
        // 在此处模拟数据的处理，直接把数据扔给具体服务处理
        MessageService.getInstance().mock(userName, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        MyChannelService.getInstance().removeChannel(getChannelInfo(ctx));
        System.out.println("客户端[" + getChannelInfo(ctx) + "] 断开了连接");
    }

    // 客户端连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyChannelService.getInstance().addChannel(getChannelInfo(ctx), ctx.channel());
        System.out.println("客户端[" + getChannelInfo(ctx) + "]连接通道建立完成");
    }


    private String getChannelInfo(ChannelHandlerContext ctx) {
        return ctx.channel().remoteAddress().toString();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端[" + getChannelInfo(ctx) + "] 通道异常，原因:" + cause.getMessage());
    }
}
