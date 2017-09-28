package com.binge.smallmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description Action方法注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    /**
     * 请求类型与路径
     * 
     * @return
     * @throws exception
     */
    String value();
}
