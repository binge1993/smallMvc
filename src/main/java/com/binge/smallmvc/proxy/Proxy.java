package com.binge.smallmvc.proxy;

/**
 * @author binge
 * @datetime 2017年9月29日
 * @version
 * @encoding UTF8
 * @Description 代理接口
 */

public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     * @throws exception
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
