package com.fbcms.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 *
 */
public class StringUtil {

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}

	/**
	 * 验证对象是否为空或NULl
	 *
	 * @param str
	 *            验证对象
	 * @return 处理结果 空/Null:true,否则:false
	 */
	public static boolean isEmptyOrNull(Object str) {
		if ("".equals(str) || null == str) {
			return true;
		}
		return false;
	}

	/**
	 * @param target
	 *            需要转化的对象
	 * @param defaultValue
	 *            缺省值
	 * @return 转化结果:如果为null，则返回缺省值，否则，返回toString()
	 * @throws @author
	 *             liyx
	 */
	public static String obj2Str(Object target, String defaultValue) {
		String value = defaultValue;
		if (target != null) {
			value = String.valueOf(target);
		}
		return value;

	}

	/**
	 * @param target
	 *            需要转化的对象
	 * @return 转化结果 :如果为null，空字符串，不为空则toString()
	 * @throws @author
	 *             liyx
	 */
	public static String obj2Str(Object target) {
		return obj2Str(target, "");
	}

	/**
	 * @param areaCode
	 * @return
	 */
	public static String validAreaCode(String areaCode) {
		return areaCode;
	}
	
	public static String getUUID(){
	    UUID uuid = UUID.randomUUID();
	    String str = uuid.toString().replace("-", "");
	    return str;
	}
}
