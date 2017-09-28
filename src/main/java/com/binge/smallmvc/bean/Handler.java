package com.binge.smallmvc.bean;

import java.lang.reflect.Method;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 封装 Action 信息
 */

public class Handler {

    private final Class<?> controllerClass;

    /**
     * Action 方法
     */
    private final Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

}
