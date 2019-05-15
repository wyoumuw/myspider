package com.youmu.spider.proxy.impl.xicidaili;

import com.youmu.spider.proxy.ProxyPool;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: YOUMU
 */
public class XiciProxyPool implements ProxyPool<XiciHttpProxy> {

	private static Logger logger = LoggerFactory.getLogger(XiciProxyPool.class);

	private static final String URL_XICIDAILI = "";
	private List<XiciHttpProxy> pool = Collections.synchronizedList(new ArrayList<>());

	@Override
	public XiciHttpProxy get() {
		if (0 == pool.size()) {
			return null;
		}
		Random random = new Random();
		int index = random.nextInt(pool.size());
		return pool.get(index);
	}

	@Override
	public int evictDead() {
		List<XiciHttpProxy> newPool = new ArrayList<>();
		for (XiciHttpProxy httpProxy : pool) {
			if (!httpProxy.isDead()) {
				newPool.add(httpProxy);
			}
		}
		int evictNum = pool.size() - newPool.size();
		pool = Collections.synchronizedList(newPool);
		return evictNum;
	}

	public int refreshPool() {
		List<XiciHttpProxy> newPool = new ArrayList<>();

		String html = pullXiciHtml();
		Document document = Jsoup.parse(html);
		Element tableEle = document.getElementById("ip_list");
		Elements trs = tableEle.getElementsByTag("tr");
		trs = trs.next();
		for (Element tr : trs) {
			try {
				XiciProxyModel xiciProxyModel = new XiciProxyModel();
				Elements td = tr.getElementsByTag("td");
				Elements countryEle = td.eq(0);
				xiciProxyModel.setCountry(countryEle.select("img").eq(0).attr("alt"));
				xiciProxyModel.setIp(td.eq(1).text());
				xiciProxyModel.setPort(Integer.valueOf(td.eq(2).text()));
				xiciProxyModel.setLocation(td.eq(3).eq(0).text());
				xiciProxyModel.setHighAnonymous(XiciProxyModel.isHighAnonymous(td.eq(4).text()));
				xiciProxyModel.setType(XiciProxyModel.ProxyType.parse(td.eq(5).text()));
				xiciProxyModel.setSpeedMilliSec(XiciProxyModel.parseCnTime(td.eq(6).select("div[title]").eq(0).attr("title")));
				xiciProxyModel.setConMilliSec(XiciProxyModel.parseCnTime(td.eq(7).select("div[title]").eq(0).attr("title")));
				xiciProxyModel.setAliveTimeSec(TimeUnit.MILLISECONDS.toSeconds(XiciProxyModel.parseCnTime(td.eq(8).text())));
				xiciProxyModel.setValidateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(td.eq(9).text()));
				newPool.add(new XiciHttpProxy(xiciProxyModel));
				logger.info("success parse a proxy:\n{}", xiciProxyModel);

			} catch (Exception e) {
				logger.warn("抛弃一个无法解析的代理:{}", tr.html(), e);
			}
		}
		pool = Collections.synchronizedList(newPool);
		return newPool.size();
	}

	protected String pullXiciHtml() {
		if (null == null) {
			return "";
		}
		HttpGet httpGet = new HttpGet(URL_XICIDAILI);
		try (CloseableHttpClient client = HttpClients.createDefault();
			 CloseableHttpResponse closeableHttpResponse = client.execute(httpGet)) {
			return EntityUtils.toString(closeableHttpResponse.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void put(XiciHttpProxy proxy) {
		pool.add(proxy);
	}

	@Override
	public void putAll(List<XiciHttpProxy> proxies) {
		pool.addAll(proxies);
	}

	@Override
	public List<XiciHttpProxy> getAll() {
		List<XiciHttpProxy> xiciHttpProxies = new ArrayList<>(pool.size() << 1);
		xiciHttpProxies.addAll(pool);
		return xiciHttpProxies;
	}
}
