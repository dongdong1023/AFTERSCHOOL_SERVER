package com.wx.base.common.utils;

import org.springframework.util.Assert;

import java.util.List;

/**
 * 
* @ClassName: AssertUtils  
* @Description:断言Assert继承工具类
* @author Mr_dong  
* @date 2019年3月4日  
*
 */
public abstract class AssertUtils extends Assert{


	/**
	 * 判断List
	 *
	 * @param clazz
	 * @param message
	 */
	public static void isNullList(List<?> clazz, String message) {
		if (clazz == null || clazz.size() <= 0) {
			throw new IllegalStateException(message);
		}
	}

	/**
	 * 判断List
	 *
	 * @param clazz
	 */
	public static boolean isNullList(List<?> clazz) {
		if (clazz == null || clazz.size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断List
	 *
	 * @param clazz
	 */
	public static boolean isList(List<?> clazz) {
		if (clazz != null && clazz.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * List不为空
	 *
	 * @param clazz
	 * @param message
	 */
	public static void isList(List<?> clazz, String message) {
		if (clazz != null && clazz.size() > 0) {
			throw new IllegalStateException(message);
		}
	}

	/**
	 * 判断实体/Object
	 * @param object
	 * @param message
	 */
	public static void isNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 判断boolean
	 * @param expression
	 * @param message
	 */
	public static void isTrue(boolean expression, String message) {
		if (expression) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
}
