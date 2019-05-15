package com.youmu.spider.proxy.impl.xicidaili;

import com.youmu.spider.proxy.HttpProxy;

/**
 * @Author: YOUMU
 */
public class XiciHttpProxy extends HttpProxy {
	private XiciProxyModel data;
	public XiciHttpProxy(XiciProxyModel model) {
		super(model.getIp(), model.getPort());
		data = model;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("XiciHttpProxy{");
		sb.append("data=").append(data);
		sb.append('}');
		return sb.toString();
	}
}
