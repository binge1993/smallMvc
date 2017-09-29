package com.binge.smallmvc.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author binge
 * @datetime 2017年9月29日
 * @version
 * @encoding UTF8
 * @Description
 */

public class CGLibProxy implements MethodInterceptor {

    private static final CGLibProxy instance = new CGLibProxy();

    private CGLibProxy() {
    }

    public static CGLibProxy getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object targetObject, Method method, Object[] methodParams, MethodProxy methodProxy)
            throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(targetObject, methodParams);
        after();

        return result;
    }

    private void after() {
        System.out.println("after");
    }

    private void before() {
        System.out.println("before");
    }

}
