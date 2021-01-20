package com.wx.base.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 个性化Util工具类
 * @author CQ Major
 *
 */
public class CsgoUtils {

	/**
	 * SteamID遵循一个相当简单的格式：“STEAM_X：Y：Z”，其中X，Y和Z是整数
	 * X :Y :Z
	 * 例如：STEAM_0 :1 :15
	 * X代表了蒸汽帐户类型 0 个人/不明 1 上市2 Beta版3 内部4 开发 5 RC
	 * Y是该帐户的ID号码的一部分。Y是0或1
	 * Z是你帐户的ID号
	 * @param steamId32
	 * @return
	 */
	public static String getSteamId32(String steamId32) throws RuntimeException {
		if (StringUtils.isNotBlank(steamId32)) {
			try {
				steamId32 = steamId32.substring(steamId32.lastIndexOf(CharUtils.COLON) + 1);
				return steamId32;
			} catch (RuntimeException e) {
				throw new RuntimeException("解析截取steamId32出现异常:" + steamId32);
			}
		} else {
			throw new RuntimeException("steamId32为空");
		}
	}
	
	/**
	 * List不为空
	 * @param clazz
	 */
	public static boolean isList(List<?> clazz) {
		if (clazz != null && clazz.size() > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getSteamId32("STEAM_1:1:9989565"));
	}
}
