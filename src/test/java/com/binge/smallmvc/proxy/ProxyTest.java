package com.binge.smallmvc.proxy;

/**
 * @author binge
 * @datetime 2017年9月29日
 * @version
 * @encoding UTF8
 * @Description
 */

public class ProxyTest {

    public static void main(String[] args) {
        Hello target = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(target);
        Hello helloProxy = dynamicProxy.getProxy();
        helloProxy.say();
    }
}
