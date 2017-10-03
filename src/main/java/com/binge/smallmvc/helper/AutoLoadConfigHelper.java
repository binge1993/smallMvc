package com.binge.smallmvc.helper;

import java.util.Properties;

import com.binge.smallmvc.constant.ConfigConstant;
import com.binge.smallmvc.util.PropsUtil;
import com.jarvis.cache.to.AutoLoadConfig;

/**
 *
 * @author binge
 * @date 2017年10月3日
 */

public class AutoLoadConfigHelper extends AutoLoadConfig {

	private static final String NAMESPACE = "namespace";
	private static final String THREADCNT = "threadCnt";
	private static final String MAXELEMENT = "maxElement";
	private static final String PRINTSLOWLOG = "printSlowLog";
	private static final String SLOWLOADTIME = "slowLoadTime";
	private static final String SORTTYPE = "sortType";
	private static final String CHECKFROMCACHEBEFORELOAD = "checkFromCacheBeforeLoad";
	private static final String AUTOLOADPERIOD = "autoLoadPeriod";
	private static final String FUNCTIONS = "functions";
	private static final String REFRESHTHREADPOOLSIZE = "refreshThreadPoolSize";
	private static final String REFRESHTHREADPOOLMAXSIZE = "refreshThreadPoolMaxSize";
	private static final String REFRESHTHREADPOOLKEEPALIVETIME = "refreshThreadPoolkeepAliveTime";
	private static final String REFRESHQUEUECAPACITY = "refreshQueueCapacity";
	private static final String LOADDATATRYCNT = "loadDataTryCnt";

	private static final Properties AUTO_LOAD_PROPS = PropsUtil
			.loadProps(ConfigConstant.DATASOURCE_FILE);

	static {
		getInstance();
	}

	private final static class AutoLoadIntance {
		public static final AutoLoadConfigHelper INSTANCE = new AutoLoadConfigHelper();
	}

	public static AutoLoadConfigHelper getInstance() {
		return AutoLoadIntance.INSTANCE;
	}

	private AutoLoadConfigHelper() {
		init();
	}

	private void init() {
		setNamespace(AUTO_LOAD_PROPS.getProperty(NAMESPACE));
		setCheckFromCacheBeforeLoad(Boolean.valueOf(
				AUTO_LOAD_PROPS.getProperty(CHECKFROMCACHEBEFORELOAD)));
		setAutoLoadPeriod(
				Integer.valueOf(AUTO_LOAD_PROPS.getProperty(AUTOLOADPERIOD)));
		setFunctions(null);
		setLoadDataTryCnt(
				Integer.valueOf(AUTO_LOAD_PROPS.getProperty(LOADDATATRYCNT)));
		setMaxElement(Integer.valueOf(AUTO_LOAD_PROPS.getProperty(MAXELEMENT)));
		setPrintSlowLog(
				Boolean.valueOf(AUTO_LOAD_PROPS.getProperty(PRINTSLOWLOG)));
		setRefreshQueueCapacity(Integer
				.valueOf(AUTO_LOAD_PROPS.getProperty(REFRESHQUEUECAPACITY)));
		setRefreshThreadPoolkeepAliveTime(Integer.valueOf(
				AUTO_LOAD_PROPS.getProperty(REFRESHTHREADPOOLKEEPALIVETIME)));
		setRefreshThreadPoolMaxSize(Integer.valueOf(
				AUTO_LOAD_PROPS.getProperty(REFRESHTHREADPOOLMAXSIZE)));
		setRefreshThreadPoolSize(Integer
				.valueOf(AUTO_LOAD_PROPS.getProperty(REFRESHTHREADPOOLSIZE)));
		setSlowLoadTime(
				Integer.valueOf(AUTO_LOAD_PROPS.getProperty(SLOWLOADTIME)));
		setSortType(Integer.valueOf(AUTO_LOAD_PROPS.getProperty(SORTTYPE)));
		setThreadCnt(Integer.valueOf(AUTO_LOAD_PROPS.getProperty(THREADCNT)));
	}

}
