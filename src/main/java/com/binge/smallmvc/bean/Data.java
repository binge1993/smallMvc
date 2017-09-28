package com.binge.smallmvc.bean;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 返回数据对象
 */

public class Data {

    /**
     * 模型数据
     */
    private final Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }

}
