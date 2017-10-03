package com.binge.smallmvc.bean;

/**
 * 封装普通参数
 * 
 * @author binge
 * @date 2017年10月3日
 */

public class CommonParam {
	private final String fieldName;
	private final Object fieldValue;

	public CommonParam(String fieldName, Object fieldValue) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

}
