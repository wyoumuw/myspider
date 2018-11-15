package com.youmu.spider.proxy;

import java.util.List;

/**
 * @Author: YLBG-LDH-1506
 * @Description:
 * @Date: 2018/11/15
 */
public interface ProxyPool<T extends Proxy> {

    /**
     * get one Proxy from pool
     * @return proxy
     */
    T get();

    /**
     * get num Proxy from pool
     * @param num how many proxies will be return
     * @return proxy
     */
    T get(int num);

    /**
     * evict all proxy that dead from this pool
     * @return evict proxy number
     */
    int evictDead();

    void put(T proxy);

    void putAll(List<T> proxies);
}
