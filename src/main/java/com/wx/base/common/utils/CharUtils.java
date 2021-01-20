package com.wx.base.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 东东
 */
public class CharUtils {

	public static final String COMMA = ","; /* 逗号 */

	public static final String SLASH = "\\";/* 斜杠 */

	public static final String BACKSLASH = "/";/* 反斜杠 */

	public static final String SPOT = ".";/* 点 */

	public static final String WELL = "#";/* 井 */
	
	public static final String COLON = ":";	/*冒号*/
	
	public static final String PERCENT = "%";	/*百分号*/
	
	public static final String SINGLEQUOTES = "'";	/*单引号*/

	private static CharUtils instance;

	public static CharUtils getInstance() {
		if (instance == null) {
			instance = new CharUtils();
		}
		return instance;
	}
	
	/**
	 * hql/dsl模糊查询快捷拼接方法
	 * @param parame
	 * @return
	 */
	public static String returnLikeCondition(String parame) {
		if(StringUtils.isNoneBlank(parame)) {
			return PERCENT + parame + PERCENT;
		}else {
			return "";
		}
	}
}
