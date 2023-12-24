package com.liukun.qa1.service;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接管理服务
 */
public final class MyChannelService {
    private static MyChannelService myChannelService = new MyChannelService();
    private Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

    private MyChannelService() {
    }

    public Channel getChannel(String name) {
        return channelMap.get(name);
    }

    public void addChannel(String name, Channel channel) {
        channelMap.put(name, channel);
    }

    public void removeChannel(String name) {
        channelMap.remove(name);
    }

    public static MyChannelService getInstance() {
        return myChannelService;
    }
}
