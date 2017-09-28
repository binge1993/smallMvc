package com.binge.smallmvc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
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
        InputStream is = ClassUtil.getClassLoader().getResourceAsStream(configFilePath);
        Properties property = new Properties();
        try {
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
     * 获取配置属性
     * 
     * @param configProps
     * @param key
     * @return
     * @throws exception
     */
    public static String getString(Properties configProps, String key) {
        if (configProps == null || StringUtils.isBlank(key)) {
            return null;
        }

        return configProps.getProperty(key);
    }

    /**
     * 获取配置属性，获取不到使用默认值
     * 
     * @param configProps
     * @param appJspPath
     * @param string
     * @return
     * @throws exception
     */
    public static String getString(Properties configProps, String key, String defaultValue) {
        if (configProps == null || StringUtils.isBlank(key)) {
            return defaultValue;
        }

        return configProps.getProperty(key, defaultValue);
    }

}
