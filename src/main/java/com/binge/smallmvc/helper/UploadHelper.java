package com.binge.smallmvc.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.binge.smallmvc.bean.CommonParam;
import com.binge.smallmvc.bean.FileParam;
import com.binge.smallmvc.bean.Param;
import com.binge.smallmvc.util.CollectionUtil;
import com.binge.smallmvc.util.FileUtil;
import com.binge.smallmvc.util.StreamUtil;

/**
 * 文件上传助手类
 * 
 * @author binge
 * @date 2017年10月3日
 */

public class UploadHelper {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UploadHelper.class);

	/**
	 * Apache Commons FileUpload 提供的 Servlet 文件上传对象
	 */
	private static ServletFileUpload servletFileUpload;

	/**
	 * 初始化
	 * 
	 * @param servletContext
	 */
	public static void init(ServletContext servletContext) {
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(
				DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		if (uploadLimit != 0) {
			servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024);
		}

	}

	/**
	 * 判断请求是否为 multipart 类型
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMultipart(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}

	/**
	 * 创建请求对象
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Param createParam(HttpServletRequest request)
			throws IOException {
		List<CommonParam> commonParamList = new ArrayList<>();
		List<FileParam> fileParamList = new ArrayList<>();

		try {
			Map<String, List<FileItem>> fileItemListMap = servletFileUpload
					.parseParameterMap(request);
			if (CollectionUtil.isEmpty(fileItemListMap)) {
				return new Param(commonParamList, fileParamList);
			}

			for (Entry<String, List<FileItem>> fileItemListEntry : fileItemListMap
					.entrySet()) {
				String fieldName = fileItemListEntry.getKey();
				List<FileItem> fileItemList = fileItemListEntry.getValue();
				if (CollectionUtil.isEmpty(fileItemList)) {
					continue;
				}

				for (FileItem fileItem : fileItemList) {
					if (fileItem.isFormField()) {
						String fieldValue = fileItem.getString("UTF-8");
						commonParamList
								.add(new CommonParam(fieldName, fieldValue));
					} else {
						String fileName = FileUtil.getRealFileName(new String(
								fileItem.getName().getBytes(), "UTF-8"));
						if (StringUtils.isEmpty(fileName)) {
							continue;
						}

						long fileSize = fileItem.getSize();
						String contentType = fileItem.getContentType();
						InputStream inputStream = fileItem.getInputStream();
						fileParamList.add(new FileParam(fieldName, fileName,
								fileSize, contentType, inputStream));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("create param failure", e);
			throw new RuntimeException(e);
		}

		return new Param(commonParamList, fileParamList);
	}

	/**
	 * 上传文件
	 * 
	 * @param basePath
	 * @param fileParam
	 */
	public static void uploadFile(String basePath, FileParam fileParam) {
		if (fileParam == null) {
			return;
		}

		try {
			String filePath = basePath + fileParam.getFieldName();
			FileUtil.createFile(filePath);
			InputStream inputStream = new BufferedInputStream(
					fileParam.getInputStream());
			OutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(filePath));
			StreamUtil.copyStream(inputStream, outputStream);
		} catch (Exception e) {
			LOGGER.error("upload file failure", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 批量上传文件
	 * 
	 * @param basePath
	 * @param fileParam
	 */
	public static void uploadFile(String basePath,
			List<FileParam> fileParamList) {
		if (CollectionUtil.isEmpty(fileParamList)) {
			return;
		}

		try {
			for (FileParam fileParam : fileParamList) {
				uploadFile(basePath, fileParam);
			}
		} catch (Exception e) {
			LOGGER.error("upload file failure", e);
			throw new RuntimeException(e);
		}
	}
}
