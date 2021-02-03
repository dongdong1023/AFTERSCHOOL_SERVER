package com.wx.base.common.utils;

import com.wx.base.common.Constats;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 东东
 * @date 2021/2/3 11:27
 */
public class RegexUtil {

    //正则表达式的字符串
    private String regexStr = new String();

    public RegexUtil(String regexStr) {
        this.regexStr = regexStr;
    }

    //正则表达式通用匹配
    private static boolean genericMatcher(String regexExpre, String testStr) {
        Pattern pattern = Pattern.compile(regexExpre);
        Matcher matcher = pattern.matcher(testStr);
        return matcher.matches();
    }

    //匹配器
    public static boolean matcher(int RegexConstant_NAME, String testStr) {
        if (StringUtils.isBlank(testStr)) {
            return false;
        }
        boolean flag = false;
        switch (RegexConstant_NAME) {

            case Constats.MOBIL_PHONE:
                flag = genericMatcher("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$", testStr);
                break;
            case Constats.E_MAIL:
                flag = genericMatcher("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", testStr);
                break;
            case Constats.HEX:
                flag = genericMatcher("/^#?([a-f0-9]{6}|[a-f0-9]{3})$/", testStr);
                break;
            case Constats.IP:
                flag = genericMatcher("/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/", testStr);
                break;
            case Constats.HTML_TAG:
                flag = genericMatcher("/^<([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$/", testStr);
                break;
            case Constats.ZIP_CODE:
                flag = genericMatcher("/^[u4e00-u9fa5],{0,}$/", testStr);
                break;
            case Constats.QQ:
                flag = genericMatcher("[1-9][0-9]{4,}", testStr);
                break;
            case Constats.ID_CARD:
                flag = genericMatcher("^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])|(1[0-2]))(0[1-9]|([1|2][0-9])|3[0-1])((\\d{4})|\\d{3}X)$", testStr);
                break;
        }
        return flag;
    }

    //正则校验，如果创建对象但是不传入正则表达式而调用该方法就会报错
    public boolean customMatcher(String testStr) {//传入待检测的字符串
        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(testStr);
        return matcher.matches();
    }

    public String getRegexStr() {
        return regexStr;
    }

    public void setRegexStr(String regexStr) {
        this.regexStr = regexStr;
    }
}
