package com.binge.smallmvc.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.binge.smallmvc.util.CastUtil;
import com.binge.smallmvc.util.CollectionUtil;
import com.binge.smallmvc.util.StringUtil;

/**
 * @author binge
 * @datetime 2017年9月28日
 * @version
 * @encoding UTF8
 * @Description 请求参数封装对象
 */

public class Param {

	private List<CommonParam> commonParamList;
	private List<FileParam> fileParamList;

	public Param(List<CommonParam> commonParamList,
			List<FileParam> fileParamList) {
		this.commonParamList = commonParamList;
		this.fileParamList = fileParamList;
	}

	public Param(List<CommonParam> commonParamList) {
		this.commonParamList = commonParamList;
	}

	/**
	 * 获取普通请求参数映射
	 * 
	 * @return
	 */
	public Map<String, Object> getFieldMap() {
		Map<String, Object> fieldMap = new HashMap<>();
		if (CollectionUtil.isNotEmpty(commonParamList)) {
			return fieldMap;
		}

		for (CommonParam commonParam : commonParamList) {
			String fieldName = commonParam.getFieldName();
			Object fieldValue = commonParam.getFieldValue();
			if (!fieldMap.containsKey(fieldName)) {
				fieldMap.put(fieldName, fieldValue);
				continue;
			}

			fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR
					+ fieldValue;
			fieldMap.put(fieldName, fieldValue);
		}

		return fieldMap;
	}

	/**
	 * 获取上传文件映射
	 * 
	 * @return
	 */
	public Map<String, List<FileParam>> getFileMap() {
		if (CollectionUtil.isEmpty(fileParamList)) {
			return new HashMap<>(0);
		}

		Map<String, List<FileParam>> fileMap = new HashMap<>();
		for (FileParam fileParam : fileParamList) {
			String fieldName = fileParam.getFieldName();
			List<FileParam> fileParamList;
			if (fileMap.containsKey(fieldName)) {
				fileParamList = fileMap.get(fieldName);
			} else {
				fileParamList = new ArrayList<>();
			}

			fileParamList.add(fileParam);
			fileMap.put(fieldName, fileParamList);

		}

		return fileMap;

	}

	/**
	 * 获取所有上传文件
	 * 
	 * @param fieldName
	 * @return
	 */
	public List<FileParam> getFileList(String fieldName) {
		return getFileMap().get(fieldName);
	}

	/**
	 * 获取唯一上传文件
	 * 
	 * @param fieldName
	 * @return
	 */
	public FileParam getFile(String fieldName) {
		List<FileParam> fileParamList = getFileList(fieldName);
		if (CollectionUtil.isEmpty(fileParamList)
				|| fileParamList.size() != 1) {
			return null;
		}

		return fileParamList.get(0);
	}

	/**
	 * 判断参数是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return CollectionUtil.isEmpty(commonParamList)
				&& CollectionUtil.isEmpty(fileParamList);
	}

	/**
	 * 根据参数名获取 String 型参数值
	 * 
	 * @param fieldName
	 * @return
	 */
	public String getString(String fieldName) {
		return CastUtil.castString(getFieldMap().get(fieldName));
	}

	/**
	 * 根据参数名获取 double 型参数值
	 * 
	 * @param fieldName
	 * @return
	 */
	public double getDouble(String fieldName) {
		return CastUtil.castDouble(getFieldMap().get(fieldName));
	}

	/**
	 * 根据参数名获取 long 型参数值
	 * 
	 * @param fieldName
	 * @return
	 */
	public long getLong(String fieldName) {
		return CastUtil.castLong(getFieldMap().get(fieldName));
	}

	/**
	 * 根据参数名获取 int 型参数值
	 * 
	 * @param fieldName
	 * @return
	 */
	public int getInt(String fieldName) {
		return CastUtil.castInt(getFieldMap().get(fieldName));
	}

	/**
	 * 根据参数名获取 boolean 型参数值
	 * 
	 * @param fieldName
	 * @return
	 */
	public boolean getBoolean(String fieldName) {
		return CastUtil.castBoolean(getFieldMap().get(fieldName));
	}
}
