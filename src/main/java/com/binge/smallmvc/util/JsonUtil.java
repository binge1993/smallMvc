package com.binge.smallmvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description JSON 工具类
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 将 POJO 转为 JSON
     */
    public static <T> String toJson(T obj) {
        String json;
        try {
            json = JSON.toJSONString(obj);
        } catch (Exception e) {
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 转为 POJO
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = JSON.parseObject(json, type);
        } catch (Exception e) {
            LOGGER.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
