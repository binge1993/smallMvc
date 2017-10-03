package com.binge.smallmvc.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数
 * 
 * @author binge
 * @date 2017年10月3日
 */

public class FileParam {

	private final String fieldName;
	private final String fileName;
	private final long fileSize;
	private final String contentType;
	private final InputStream inputStream;

	public FileParam(String fieldName, String fileName, long fileSize,
			String contentType, InputStream inputStream) {
		this.fieldName = fieldName;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFileName() {
		return fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
