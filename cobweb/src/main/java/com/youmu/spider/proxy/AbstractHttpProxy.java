package com.youmu.spider.proxy;

import java.net.HttpURLConnection;
import java.net.URLConnection;

/**
 * @Author: YOUMU
 */
public abstract class AbstractHttpProxy implements Proxy {

    private String host;

    private int port;

    public AbstractHttpProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }
}
