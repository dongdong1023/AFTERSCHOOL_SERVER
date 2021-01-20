package com.wx.base.common.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	/**
	 * 判断是否为JSON
	 * @param text
	 * @return
	 */
    public final static boolean isJSONValid(String text) {
        try {
            JSONObject.parseObject(text);
        } catch (JSONException ex) {
            try {
                JSONObject.parseArray(text);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
