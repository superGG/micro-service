package com.kellan.demo.utils.commom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * 字符串工具类
 * 
 * @author Kellan_Song
 * @createTime 2019年1月10日
 */
public class StringUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(StringUtils.class);
	
	/**
	 * 检测空字符串 
	 * StringUtils.isBank(null) = true 
	 * StringUtils.isBank("") = true
	 * StringUtils.isBank(" ") = true 
	 * StringUtils.isBank("test") = false
	 * StringUtils.isBank(" test ") = false
	 * 
	 * @author Kellan_Song
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 判断字符串是否 不为空
	 * 
	 * @author Kellan_Song
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	 /**
	 * 合并字符串
	 * @author Kellan_Song
	 * @param list
	 * @return
	 */
	 public static String concat(List<String> list) {
		 if (list == null || list.isEmpty())
				return "";
		 StringBuffer buffer = new StringBuffer("");
		 Iterator<String> iterator = list.iterator();
		 while(iterator.hasNext()) {
			 buffer.append(iterator.next());
		 }
		 return buffer.toString();
	 }

	/**
	 * 使用分隔符 合并字符串
	 * 
	 * @author Kellan_Song
	 * @param list
	 * @param separator 分隔符
	 * @return
	 */
	public static String concat(List<? extends Object> list, String separator) {
		if (list == null || list.isEmpty())
			return "";
		int len = list.size() * 2;
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < len - 1; i++) {
			if (i % 2 == 0) {
				buffer.append(list.get(i / 2) == null ? "" : list.get(i / 2));
			} else {
				buffer.append(separator);
			}
		}
		return buffer.toString();
	}

	/**
	 * 去除字符串中的所有空格键
	 * 
	 * @author Kellan_Song
	 * @param str
	 * @return
	 */
	public static String deleteWhitespace(String str) {
		StringBuffer buffer = new StringBuffer();
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				buffer.append(str.charAt(i));
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 将对讲转换成json字符串
	 * @author Kellan_Song
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.info("----对象" + obj + "转json字符串失败----");
//			e.printStackTrace();
		}
		return jsonString;
	}

}
