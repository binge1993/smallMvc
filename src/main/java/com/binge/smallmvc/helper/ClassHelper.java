package com.binge.smallmvc.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import com.binge.smallmvc.annotation.Controller;
import com.binge.smallmvc.annotation.Service;
import com.binge.smallmvc.util.ClassUtil;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 类操作助手类
 */

public class ClassHelper {

    /**
     * 定义类集合（用于存放所加载的类）
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取应用包名下的所有类
     * 
     * @return
     * @throws exception
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取应用包名下所有 Service 类
     * 
     * @return
     * @throws exception
     */
    public static Set<Class<?>> getServiceClassSet() {
        return getClassSetByAnnotation(Service.class);
    }

    /**
     * 获取应用包名下所有 Controller 类
     * 
     * @return
     * @throws exception
     */
    public static Set<Class<?>> getControllerClassSet() {
        return getClassSetByAnnotation(Controller.class);
    }

    /**
     * 获取应用包名下所有 Bean 类（包括：Service、Controller等）
     * 
     * @return
     * @throws exception
     */
    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> beanClassSet = new HashSet<>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有子类（或实现类）
     * 
     * @param supserClass
     * @return
     * @throws exception
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (!superClass.isAssignableFrom(cls) || superClass.equals(cls)) {
                continue;
            }

            classSet.add(cls);
        }
        return classSet;
    }

    /**
     * 获取应用包名下所有 带有某注解的所有类
     * 
     * @return
     * @throws exception
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }

        return classSet;
    }

}
