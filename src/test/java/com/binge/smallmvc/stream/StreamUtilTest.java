package com.binge.smallmvc.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.binge.smallmvc.util.StreamUtil;

/**
 * 流工具测试
 * 
 * @author binge
 * @date 2017年10月3日
 */

public class StreamUtilTest {

	public static void main(String[] args) throws FileNotFoundException {
		InputStream input = new FileInputStream(
				new File("C:\\Users\\Administrator\\Desktop\\a.txt"));
		System.out.println(StreamUtil.getString(input));
		System.out.println(StreamUtil.getString(input));
	}
}
