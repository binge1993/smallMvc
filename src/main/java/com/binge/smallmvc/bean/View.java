package com.binge.smallmvc.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 返回视图对象
 */

public class View {

    private final String path;

    private final Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

}
