package com.binge.smallmvc.helper;

import java.lang.reflect.Field;
import java.util.Map;

import com.binge.smallmvc.annotation.Inject;
import com.binge.smallmvc.util.ArrayUtil;
import com.binge.smallmvc.util.CollectionUtil;
import com.binge.smallmvc.util.ReflectionUtil;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 依赖注入 助手
 */

public class IocHelper {

    static {
        init();
    }

    /**
     * 初始化环境
     * 
     * @throws exception
     */
    private static void init() {
        // 获取所有的 Bean 类与 Bean 实例之间的映射关系（简称 Bean Map）
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isEmpty(beanMap)) {
            return;
        }

        for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
            // 从 BeanMap 中获取 Bean 类与 Bean 实例
            Class<?> beanClass = beanEntry.getKey();
            Object beanInstance = beanEntry.getValue();

            // 获取 Bean 类定义的所有成员变量（简称 Bean Field）
            Field[] beanFields = beanClass.getDeclaredFields();
            if (ArrayUtil.isEmpty(beanFields)) {
                continue;
            }

            // 遍历 Bean Field
            for (Field beanField : beanFields) {
                if (!beanField.isAnnotationPresent(Inject.class)) {
                    continue;
                }

                // 在 Bean Map 中获取 Bean Field 对应的实例
                Class<?> beanFieldClass = beanField.getType();
                Object beanFieldInstance = beanMap.get(beanFieldClass);
                if (beanFieldInstance == null) {
                    continue;
                }

                // 通过反射初始化 BeanField 的值
                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);

            }

        }
    }
}
