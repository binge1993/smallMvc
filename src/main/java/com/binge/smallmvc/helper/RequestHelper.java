package com.binge.smallmvc.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.binge.smallmvc.bean.CommonParam;
import com.binge.smallmvc.bean.Param;
import com.binge.smallmvc.util.CodecUtil;
import com.binge.smallmvc.util.CollectionUtil;
import com.binge.smallmvc.util.StreamUtil;

/**
 *
 * @author binge
 * @date 2017年10月3日
 */

public class RequestHelper {

	/**
	 * 创建请求参数
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Param createParam(HttpServletRequest request)
			throws IOException {
		List<CommonParam> commonParamList = new ArrayList<>();
		commonParamList.addAll(parseInputStream(request));
		return new Param(commonParamList);
	}

	/**
	 * 处理请求参数
	 * 
	 * @param request
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private static List<CommonParam> parseInputStream(
			HttpServletRequest request) throws IOException {
		List<CommonParam> commonParamList = new ArrayList<>();
		String body = CodecUtil
				.decodeURL(StreamUtil.getString(request.getInputStream()));
		if (StringUtils.isEmpty(body)) {
			return commonParamList;
		}

		Map<String, String> params = JSON.parseObject(body, Map.class);
		if (CollectionUtil.isEmpty(params)) {
			return commonParamList;
		}

		for (Entry<String, String> entry : params.entrySet()) {
			String fieldName = entry.getKey();
			String fieldValue = entry.getValue();
			commonParamList.add(new CommonParam(fieldName, fieldValue));
		}

		return commonParamList;
	}
}
