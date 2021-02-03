package com.wx.base.service;

import com.wx.base.entity.after.AfterUserInfo;
import com.wx.base.service.support.IBaseService;

public interface IWxUserInfoService extends IBaseService<AfterUserInfo, Integer> {

    /**
     * 鉴权微信用户信息-->新增、更新
     *
     * @param code
     * @return
     */
    AfterUserInfo userInfo(String code) throws Exception;

    AfterUserInfo getUserInfoByOpenId(String openId) throws Exception;
}
