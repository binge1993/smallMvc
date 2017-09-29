package com.binge.smallmvc.helper;

import java.util.Properties;

import com.binge.smallmvc.constant.ConfigConstant;
import com.binge.smallmvc.util.PropsUtil;

/**
 * @author binge
 * @datetime 2017年9月27日
 * @version
 * @encoding UTF8
 * @Description 属性文件组手类
 */

public final class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取JDBC驱动
     * 
     * @return
     * @throws exception
     */
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取JDBC 用户名
     * 
     * @return
     * @throws exception
     */
    public static String getJdbcUserName() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取JDBC 密码
     * 
     * @return
     * @throws exception
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     * 
     * @return
     * @throws exception
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用 JSP 路径
     * 
     * @return
     * @throws exception
     */
    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, ConfigConstant.DEFAULT_APP_JSP_PATH);
    }

    /**
     * 获取应用静态资源路径
     * 
     * @return
     * @throws exception
     */
    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, ConfigConstant.DEFAULT_APP_ASSET_PATH);
    }

    /**
     * 获取应用文件上传限制
     */
    public static int getAppUploadLimit() {
        return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT, 10);
    }

    /**
     * 根据属性名获取 String 类型的属性值
     */
    public static String getString(String key) {
        return PropsUtil.getString(CONFIG_PROPS, key);
    }

    /**
     * 根据属性名获取 int 类型的属性值
     */
    public static int getInt(String key) {
        return PropsUtil.getInt(CONFIG_PROPS, key);
    }

    /**
     * 根据属性名获取 boolean 类型的属性值
     */
    public static boolean getBoolean(String key) {
        return PropsUtil.getBoolean(CONFIG_PROPS, key);
    }

}
