package com.binge.smallmvc.cglib;

import com.binge.smallmvc.proxy.Hello;
import com.binge.smallmvc.proxy.HelloImpl;

/**
 * @author binge
 * @datetime 2017年9月29日
 * @version
 * @encoding UTF8
 * @Description
 */

public class CGLibTest {
    public static void main(String[] args) {
        CGLibProxy cgLibProxy = CGLibProxy.getInstance();
        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say();
    }
}
