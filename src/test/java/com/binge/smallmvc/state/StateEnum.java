package com.binge.smallmvc.state;

/**
 * @author binge
 * @datetime 2017年10月17日
 * @version
 * @encoding UTF8
 * @Description
 */

public enum StateEnum {

    // 未连接
    UNCONNECT(1, "UNCONNECT"),

    // 已连接
    CONNECT(2, "CONNECT"),

    // 注册中
    REGISTING(3, "REGISTING"),

    // 已注册
    REGISTED(4, "REGISTED");

    private int key;

    private String value;

    StateEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
