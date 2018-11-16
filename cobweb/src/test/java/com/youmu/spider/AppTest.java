package com.youmu.spider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.youmu.spider.proxy.impl.xicidaili.XiciProxyModel;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void parseTimeTest() {
		String example1 = "0.231秒";
		String example2 = "1.33秒";
		String example3 = "98天";
		String example4 = "5小时0.231秒";
		assertEquals(231L, XiciProxyModel.parseCnTime(example1));
		assertEquals(1330L, XiciProxyModel.parseCnTime(example2));
		assertEquals(TimeUnit.DAYS.toMillis(98), XiciProxyModel.parseCnTime(example3));
		assertEquals(TimeUnit.HOURS.toMillis(5)+231L, XiciProxyModel.parseCnTime(example4));
	}
}
