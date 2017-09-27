package com.binge.smallmvc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * @author binge
 * @datetime 2017年9月27日
 * @version
 * @encoding UTF8
 * @Description properties文件工具类
 */

public class PropsUtil {

    /**
     * 加载配置文件
     * 
     * @param configFilePath
     * @return
     * @throws exception
     */
    public static Properties loadProps(String configFilePath) {
        File configFile = new File(configFilePath);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(configFilePath + "--配置文件不存在", e);
        } catch (IOException e) {
            throw new RuntimeException(configFilePath + "--读取配置失败", e);
        } catch (Exception e) {
            throw new RuntimeException(configFilePath + "--读取配置文件失败", e);
        }

        return properties;
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
