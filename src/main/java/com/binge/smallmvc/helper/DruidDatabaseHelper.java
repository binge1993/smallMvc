package com.binge.smallmvc.helper;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.binge.smallmvc.constant.ConfigConstant;
import com.binge.smallmvc.util.PropsUtil;

/**
 * @author binge
 * @datetime 2017年9月30日
 * @version
 * @encoding UTF8
 * @Description Druid 数据库助手类
 */

public class DruidDatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidDataSource.class);

    private static final Properties DATASOURCE_PROPS = PropsUtil.loadProps(ConfigConstant.DATASOURCE_FILE);

    private static final DruidDataSource druidDataSource;

    static {
        try {
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(DATASOURCE_PROPS);
        } catch (Exception e) {
            LOGGER.error("DruidDataSource init failure");
            throw new RuntimeException("DruidDataSource init failure", e);
        }
    }

    public static final DruidDataSource getDataSource() {
        return druidDataSource;
    }

}
