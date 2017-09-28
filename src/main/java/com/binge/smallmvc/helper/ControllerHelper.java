package com.binge.smallmvc.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.binge.smallmvc.annotation.Action;
import com.binge.smallmvc.bean.Handler;
import com.binge.smallmvc.bean.Request;
import com.binge.smallmvc.util.ArrayUtil;
import com.binge.smallmvc.util.CollectionUtil;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description Controller 类助手
 */

public class ControllerHelper {

    /**
     * 用于存放请求与处理器的映射关系（简称 Action Map）
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        init();
    }

    /**
     * 初始化类信息
     * 
     * @throws exception
     */
    private static void init() {
        // 获取所有的 Controller 类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isEmpty(controllerClassSet)) {
            return;
        }

        // 遍历这些 Controller 类
        for (Class<?> controllerClass : controllerClassSet) {
            // 获取 Controller 类中定义的方法
            Method[] methods = controllerClass.getDeclaredMethods();
            if (ArrayUtil.isEmpty(methods)) {
                continue;
            }

            // 遍历这些 Controller 类中的方法
            for (Method method : methods) {
                if (!method.isAnnotationPresent(Action.class)) {
                    continue;
                }

                // 从 Action 注解中获取 URL 映射规则
                Action action = method.getAnnotation(Action.class);
                String mapping = action.value();

                // 验证 URL 映射规则
                if (mapping.matches("\\w+:/\\w*")) {
                    String[] array = mapping.split(":");
                    if (ArrayUtil.isEmpty(array) || array.length != 2) {
                        continue;
                    }

                    // 获取请求方法与请求路径
                    String requestMethod = array[0];
                    String requestPath = array[1];
                    Request request = new Request(requestMethod, requestPath);
                    Handler handler = new Handler(controllerClass, method);

                    // 初始化 Action Map
                    ACTION_MAP.put(request, handler);

                }

            }
        }

    }

    /**
     * 获取 Handler
     * 
     * @param requestMethod
     * @param requestPath
     * @return
     * @throws exception
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}
