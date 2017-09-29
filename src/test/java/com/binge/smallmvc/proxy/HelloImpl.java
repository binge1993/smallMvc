package com.binge.smallmvc.proxy;

/**
 * @author binge
 * @datetime 2017年9月29日
 * @version
 * @encoding UTF8
 * @Description
 */

public class HelloImpl implements Hello {

    @Override
    public void say() {
        System.out.println("hello world");
    }

}
