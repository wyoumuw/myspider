package com.youmu.spider.proxy.impl.xicidaili;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: YOUMU
 */
public class XiciProxyModel {

	//国家
	private String country;
	// ip
	private String ip;
	// 端口
	private int port;
	// 地址
	private String location;
	// 是否是高匿
	private boolean highAnonymous;
	// 代理协议类型
	private ProxyType type;
	// 响应速度(ms
	private long speedMilliSec;
	// 连接速度(ms
	private long conMilliSec;
	// 生存时间（s
	private long aliveTimeSec;
	// 最后一次验证时间
	private Date validateTime;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isHighAnonymous() {
		return highAnonymous;
	}

	public void setHighAnonymous(boolean highAnonymous) {
		this.highAnonymous = highAnonymous;
	}

	public ProxyType getType() {
		return type;
	}

	public void setType(ProxyType type) {
		this.type = type;
	}

	public long getSpeedMilliSec() {
		return speedMilliSec;
	}

	public void setSpeedMilliSec(long speedMilliSec) {
		this.speedMilliSec = speedMilliSec;
	}

	public long getConMilliSec() {
		return conMilliSec;
	}

	public void setConMilliSec(long conMilliSec) {
		this.conMilliSec = conMilliSec;
	}

	public long getAliveTimeSec() {
		return aliveTimeSec;
	}

	public void setAliveTimeSec(long aliveTimeSec) {
		this.aliveTimeSec = aliveTimeSec;
	}

	public Date getValidateTime() {
		return validateTime;
	}

	public void setValidateTime(Date validateTime) {
		this.validateTime = validateTime;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("XiciProxyModel{");
		sb.append("ip='").append(ip).append('\'');
		sb.append(", port=").append(port);
		sb.append(", location='").append(location).append('\'');
		sb.append(", highAnonymous=").append(highAnonymous);
		sb.append(", type=").append(type);
		sb.append('}');
		return sb.toString();
	}

	//--------------------------------
	public static final boolean isHighAnonymous(String text) {
		return "高匿".equals(text);
	}

	private static final Pattern PATTERN_SEC = Pattern.compile("(([0-9]+\\.*)?[0-9]+)秒");
	private static final Pattern PATTERN_DAY = Pattern.compile("([0-9]+)天");
	private static final Pattern PATTERN_HOUR = Pattern.compile("([0-9]+)小时");


	// 返回毫秒
	public static final long parseCnTime(String time) {
		if (null == time || time.length() == 0) {
			return 0;
		}
		Matcher secMatcher = PATTERN_SEC.matcher(time);
		Matcher dayMatcher = PATTERN_DAY.matcher(time);
		Matcher hourMatcher = PATTERN_HOUR.matcher(time);
		long l = 0;
		if (secMatcher.find()) {
			String secStr = secMatcher.group(1);
			Double sec = Double.valueOf(secStr);
			l += (long) (sec * 1000);
		}
		if (dayMatcher.find()) {
			String dayStr = dayMatcher.group(1);
			Long day = Long.valueOf(dayStr);
			l += day * 24 * 60 * 60 * 1000;
		}
		if (hourMatcher.find()) {
			String hourStr = hourMatcher.group(1);
			Long hour = Long.valueOf(hourStr);
			l += hour * 60 * 60 * 1000;
		}
		return l;
	}

	enum ProxyType {
		HTTP, HTTPS;

		public static ProxyType parse(String typeText) {
			if ("http".equalsIgnoreCase(typeText)) {
				return HTTP;
			} else if ("https".equalsIgnoreCase(typeText)) {
				return HTTPS;
			}
			return null;
		}
	}
}
