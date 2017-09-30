package com.binge.smallmvc.util;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.binge.smallmvc.helper.DruidDatabaseHelper;

/**
 * @author binge
 * @datetime 2017年9月30日
 * @version
 * @encoding UTF8
 * @Description
 */

public class DbUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbUtil.class);

    // 存放当前线程的connection
    private static ThreadLocal<Connection> connectionContainer = new ThreadLocal<>();

    /**
     * 获取连接
     * 
     * @return
     * @throws exception
     */
    public static Connection getConnection() {
        Connection conn = connectionContainer.get();
        try {
            if (conn == null) {
                conn = DruidDatabaseHelper.getDataSource().getConnection();
            }
        } catch (Exception e) {
            LOGGER.error("get druid connection failure", e);
        } finally {
            connectionContainer.set(conn);
        }

        return conn;
    }

    /**
     * 关闭连接
     * 
     * @return
     * @throws exception
     */
    public static Connection closeConnection() {
        Connection conn = connectionContainer.get();
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            LOGGER.error("close druid connection failure", e);
        } finally {
            connectionContainer.set(conn);
        }

        return conn;
    }

}
