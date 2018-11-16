package com.youmu.spider.proxy.impl.xicidaili;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

/**
 * @Author: YLBG-LDH-1506
 * @Description: XiciProxyPoolTest test
 * @Date: 11/16/2018
 */
public class XiciProxyPoolTest {
	private XiciProxyPool xiciProxyPool = new XiciProxyPool() {

		@Override
		protected String pullXiciHtml() {
			try {
				return FileUtils.readFileToString(new File("C:\\Users\\ucmed\\Desktop\\ゴミ\\国内高匿免费HTTP代理IP__第1页国内高匿(1)\\国内高匿免费HTTP代理IP__第1页国内高匿.html"), "utf-8");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	};

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	@Test
	public void mockPullTest() throws IOException {
		System.out.println(xiciProxyPool.refreshPool());
	}
}
