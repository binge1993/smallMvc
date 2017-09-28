package com.binge.smallmvc.util;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 数组工具类
 */

public class ArrayUtil {

    /**
     * 判断数组是否为空
     * 
     * @param array
     * @return
     * @throws exception
     */
    public static boolean isEmpty(final Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否非空
     * 
     * @param array
     * @return
     * @throws exception
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

}
