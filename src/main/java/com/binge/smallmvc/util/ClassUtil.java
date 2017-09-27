package com.binge.smallmvc.util;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author binge
 * @datetime 2017年9月27日
 * @version
 * @encoding UTF8
 * @Description 类操作工具类
 */

public class ClassUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * 
     * @return
     * @throws exception
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * 
     * @param className
     * @param isInitialized
     *            是否初始化
     * @return
     * @throws exception
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;

        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error(arg0);
        }

        return null;
    }

    /**
     * 获取指定包名下的所有类
     * 
     * @param packageName
     * @return
     * @throws exception
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        return null;
    }

}
