package com.binge.smallmvc.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author binge
 * @datetime 2017年9月30日
 * @version
 * @encoding UTF8
 * @Description
 */

public class MyThreadLocal<T> {

    private final Map<Thread, T> container = new ConcurrentHashMap<>();

    public void set(T value) {
        container.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = container.get(thread);
        if (value == null || !container.containsKey(thread)) {
            value = initailize();
            container.put(thread, value);
        }

        return value;
    }

    public void remove() {
        container.remove(Thread.currentThread());
    }

    protected T initailize() {
        return null;
    }

}
