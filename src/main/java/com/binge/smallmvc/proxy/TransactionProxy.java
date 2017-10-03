package com.binge.smallmvc.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.binge.smallmvc.annotation.Transaction;
import com.binge.smallmvc.helper.DruidDatabaseHelper;

/**
 *
 * @author binge
 * @date 2017年10月1日
 */

public class TransactionProxy implements Proxy {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TransactionProxy.class);

	/**
	 * 用于标志该该线程内是否执行过事务
	 */
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};

	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result = null;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if (flag || !method.isAnnotationPresent(Transaction.class)) {
			return proxyChain.doProxyChain();
		}

		FLAG_HOLDER.set(true);
		try {
			DruidDatabaseHelper.beginTransaction();
			LOGGER.debug("begin transaction");
			result = proxyChain.doProxyChain();
			DruidDatabaseHelper.commitTransaction();
			LOGGER.debug("commit transaction");
		} catch (Exception e) {
			DruidDatabaseHelper.rollbackTransaction();
			LOGGER.debug("rollback transaction");
		} finally {
			FLAG_HOLDER.remove();
		}

		return result;
	}

}
