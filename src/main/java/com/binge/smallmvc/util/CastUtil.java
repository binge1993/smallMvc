package com.binge.smallmvc.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 类型转换工具类
 */

public class CastUtil {

    /**
     * 转为 String 型
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    /**
     * 转为 String 型（提供默认值）
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为 double 型
     */
    public static Double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }

    /**
     * 转为 double 型（提供默认值）
     */
    public static Double castDouble(Object obj, double defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String strValue = castString(obj);
        if (StringUtils.isBlank(strValue)) {
            return defaultValue;
        }

        double doubleValue = defaultValue;
        try {
            doubleValue = Double.parseDouble(strValue);
        } catch (NumberFormatException e) {
            doubleValue = defaultValue;
        }
        return doubleValue;
    }

    /**
     * 转为 long 型
     */
    public static Long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 转为 long 型（提供默认值）
     */
    public static Long castLong(Object obj, long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String strValue = castString(obj);
        if (StringUtils.isBlank(strValue)) {
            return defaultValue;
        }

        long longValue = defaultValue;
        try {
            longValue = Long.parseLong(strValue);
        } catch (NumberFormatException e) {
            longValue = defaultValue;
        }
        return longValue;

    }

    /**
     * 转为 int 型
     */
    public static Integer castInt(Object obj) {
        return CastUtil.castInt(obj, 0);
    }

    /**
     * 转为 int 型（提供默认值）
     */
    public static Integer castInt(Object obj, int defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String strValue = castString(obj);
        if (StringUtils.isBlank(strValue)) {
            return defaultValue;
        }

        Integer intValue = defaultValue;
        try {
            intValue = Integer.parseInt(strValue);
        } catch (NumberFormatException e) {
            intValue = defaultValue;
        }
        return intValue;
    }

    /**
     * 转为 boolean 型
     */
    public static Boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }

    /**
     * 转为 boolean 型（提供默认值）
     */
    public static Boolean castBoolean(Object obj, boolean defaultValue) {
        if (obj == null) {
            return defaultValue;
        }

        return Boolean.parseBoolean(castString(obj));
    }
}