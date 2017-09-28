package com.binge.smallmvc.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 集合工具类
 */

public class CollectionUtil {

    /**
     * 判断map是否非空
     * 
     * @param map
     * @return
     * @throws exception
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断map是否为空
     * 
     * @param map
     * @return
     * @throws exception
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断集合是否为空
     * 
     * @param collection
     * @return
     * @throws exception
     */
    public static boolean isEmpty(Collection<?> collection) {
        // TODO Auto-generated method stub
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断集合是否非空
     * 
     * @param collection
     * @return
     * @throws exception
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        // TODO Auto-generated method stub
        return CollectionUtils.isEmpty(collection);
    }

}
