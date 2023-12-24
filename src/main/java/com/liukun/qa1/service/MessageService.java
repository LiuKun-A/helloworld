package com.liukun.qa1.service;

import io.netty.channel.Channel;

/**
 * 数据处理服务
 */
public final class MessageService {
    private static MessageService messageService = new MessageService();
    private MessageService() {}

    public static MessageService getInstance() {
        return messageService;
    }

    // 模拟数据处理
    public void mock(String user, String msg) {
        String retMsg = "服务端收到: " + msg;
        getChannel(user).writeAndFlush(retMsg);
    }

    private Channel getChannel(String user) {
        return MyChannelService.getInstance().getChannel(user);
    }

}
