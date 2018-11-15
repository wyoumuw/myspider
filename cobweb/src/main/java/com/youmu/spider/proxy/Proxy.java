package com.youmu.spider.proxy;

/**
 * @Author: YOUMU
 */
public interface Proxy {
    String getHost();

    int getPort();

    boolean isDead();
}
