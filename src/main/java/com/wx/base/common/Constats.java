package com.wx.base.common;

public interface Constats {

    public static final String CATCH_ERROR_MSG = "[操作出现异常,请联系管理员...]";

    public static final String REQUEST_PARAM_ERROR = "请求参数异常...";

    public static final String REQUEST_AUTH_ERROR = "访问出现异常,请稍后重试...";

    public static final String RESULE_USER_ISNULL_ERROR = "添加用户信息不能为空";

    public static final String RESOURCECACHENAME = "resourceCache";

    /**
     * 微信
     */

    public static final String REDIS_USER_INFO_OPENID = "openId_";

    public static final String REDIS_USER_INFO_TOKEN = "token_user_";

    public static final String REDIS_USER_INFO_REFRESH = "refresh_user_";


    /**
     * Mysql备份还原
     */

    public static final String BACKUP_SQL_EXT = ".sql";                       //SQL拓展名

    public static final String BACKUP_DEFAULT_FILE_NAME = "imba_csgo";   //默认备份文件名

    public static final String BACKUP_DEFAULT_FILE_NAME_ALL = BACKUP_DEFAULT_FILE_NAME + BACKUP_SQL_EXT; //默认备份文件名+类型

    public static final String DELETE_FILE_DEFAULT_ERROR = "系统默认备份无法删除";


    /**
     * 正则匹配
     */

    //匹配Email
    public static final int E_MAIL = 0;
    //匹配手机
    public static final int MOBIL_PHONE = 1;
    //匹配电话座机
    public static final int TEL_PHONE = 2;
    //匹配邮编
    public static final int ZIP_CODE = 3;
    //匹配URL
    public static final int URL = 4;
    //匹配ip格式
    public static final int IP = 5;
    //匹配HTML标签
    public static final int HTML_TAG = 6;
    //匹配十六进制
    public static final int HEX = 7;
    //匹配QQ号
    public static final int QQ = 8;
    //匹配身份证号
    public static final int ID_CARD = 9;
    //匹配正整数
    public static final int POSITIVE_INTEGER=10;
}
