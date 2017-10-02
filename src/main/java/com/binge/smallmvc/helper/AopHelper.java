package com.binge.smallmvc.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.binge.smallmvc.annotation.Aspect;
import com.binge.smallmvc.annotation.Service;
import com.binge.smallmvc.proxy.AspectProxy;
import com.binge.smallmvc.proxy.Proxy;
import com.binge.smallmvc.proxy.ProxyManager;
import com.binge.smallmvc.proxy.TransactionProxy;

/**
 * @author binge
 * @datetime 2017年9月29日
 * @version
 * @encoding UTF8
 * @Description aop 助手类
 */

public final class AopHelper {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AopHelper.class);

	static {
		init();
	}

	// 初始化
	private static void init() {
		try {
			Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
			for (Entry<Class<?>, List<Proxy>> targetEntry : targetMap
					.entrySet()) {
				Class<?> targetClass = targetEntry.getKey();
				List<Proxy> proxyList = targetEntry.getValue();
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBean(targetClass, proxy);
			}
		} catch (Exception e) {
			LOGGER.error("aop failure", e);
		}
	}

	/**
	 * 获取指定的Aspect注解的所有类
	 * 
	 * @param aspect
	 * @return
	 * @throws Exception
	 * @throws exception
	 */
	private static Set<Class<?>> createTargetClassSet(Aspect aspect)
			throws Exception {
		Set<Class<?>> targetClassSet = new HashSet<>();
		Class<? extends Annotation> annotation = aspect.value();
		if (annotation != null && !annotation.equals(Aspect.class)) {
			targetClassSet
					.addAll(ClassHelper.getClassSetByAnnotation(annotation));
		}

		return targetClassSet;
	}

	/**
	 * 获取代理类及其目标类集合之间的映射关系
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Map<Class<?>, Set<Class<?>>> createProxyMap()
			throws Exception {
		Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<>();
		addAspectProxy(proxyMap);
		addTransactionProxy(proxyMap);
		return proxyMap;
	}

	/**
	 * 添加 Aspect 注解的代理
	 * 
	 * @param proxyMap
	 * @throws Exception
	 */
	private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap)
			throws Exception {
		Set<Class<?>> proxyClassSet = ClassHelper
				.getClassSetBySuper(AspectProxy.class);
		for (Class<?> proxyClass : proxyClassSet) {
			if (!proxyClass.isAnnotationPresent(Aspect.class)) {
				continue;
			}

			Aspect aspect = proxyClass.getAnnotation(Aspect.class);
			Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
			proxyMap.put(proxyClass, targetClassSet);
		}

	}

	/**
	 * 添加 Transaction 注解的代理
	 * 
	 * @param proxyMap
	 * @throws Exception
	 */
	private static void addTransactionProxy(
			Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
		Set<Class<?>> serviceClassSet = ClassHelper
				.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, serviceClassSet);
	}

	/**
	 * 获取目标类与代理对象列表之间的映射关系
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Map<Class<?>, List<Proxy>> createTargetMap(
			Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
		Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
		for (Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
			Class<?> proxyClass = proxyEntry.getKey();
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for (Class<?> targetClass : targetClassSet) {
				Proxy proxy = (Proxy) proxyClass.newInstance();
				if (targetMap.containsKey(targetClass)) {
					targetMap.get(targetClass).add(proxy);
				} else {
					List<Proxy> proxyList = new ArrayList<>();
					proxyList.add(proxy);
					targetMap.put(targetClass, proxyList);
				}
			}
		}

		return targetMap;
	}

}
