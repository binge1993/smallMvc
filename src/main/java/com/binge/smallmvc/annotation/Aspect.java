package com.binge.smallmvc.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author binge
 * @datetime 2017年9月29日
 * @version
 * @encoding UTF8
 * @Description 切面注解
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     * 
     * @return
     * @throws exception
     */
    Class<? extends Annotation> value();
}
