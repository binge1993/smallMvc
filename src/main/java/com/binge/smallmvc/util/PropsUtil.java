package com.binge.smallmvc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author binge
 * @datetime 2017年9月27日
 * @version
 * @encoding UTF8
 * @Description properties文件工具类
 */

public class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载配置文件
     * 
     * @param configFilePath
     * @return
     * @throws exception
     */
    public static Properties loadProps(String configFilePath) {

        Reader reader = null;
        InputStream is = null;
        Properties property = new Properties();
        try {
            is = ClassUtil.getClassLoader().getResourceAsStream(configFilePath);
            if (is == null) {
                throw new FileNotFoundException("File not found :" + configFilePath);
            }
            reader = new InputStreamReader(is, "UTF-8");
            property.load(reader);
        } catch (Throwable ex) {
            LOGGER.error("load config failure, file:" + configFilePath, ex);
            throw new RuntimeException("load config failure");

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    LOGGER.error("close stream failure", ex);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    LOGGER.error("close stream failure", ex);
                }
            }

        }
        return property;
    }

    /**
     * 获取 String 类型的属性值（默认值为空字符串）
     */
    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }

    /**
     * 获取 String 类型的属性值（可指定默认值）
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取 int 类型的属性值（默认值为 0）
     */
    public static Integer getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    /**
     * 获取 int 类型的属性值（可指定默认值）
     */
    public static Integer getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取 boolean 类型属性（默认值为 false）
     */
    public static Boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取 boolean 类型属性（可指定默认值）
     */
    public static Boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
