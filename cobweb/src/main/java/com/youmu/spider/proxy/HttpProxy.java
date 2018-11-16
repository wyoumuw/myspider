package com.youmu.spider.proxy;

import com.youmu.spider.util.NetUtils;

import java.net.InetSocketAddress;

/**
 * @Author: YOUMU
 */
public class HttpProxy extends AbstractHttpProxy {
	public HttpProxy(String host, int port) {
		super(host, port);
	}

	@Override
	public boolean isDead() {
		return NetUtils.ping(new InetSocketAddress(getHost(), getPort()), 20000);
	}
}
