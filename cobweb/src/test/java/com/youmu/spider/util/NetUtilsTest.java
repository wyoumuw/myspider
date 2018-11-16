package com.youmu.spider.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import static org.junit.Assert.fail;

/**
 * @Author: YLBG-LDH-1506
 * @Description: NetUtilsTest test
 * @Date: 11/15/2018
 */
public class NetUtilsTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: ping(SocketAddress address, int time)
	 */
	@Test
	public void testPing() throws Exception {
		Assert.assertTrue(NetUtils.ping(new InetSocketAddress("115.239.210.27", 80), 1000));
	}


} 
