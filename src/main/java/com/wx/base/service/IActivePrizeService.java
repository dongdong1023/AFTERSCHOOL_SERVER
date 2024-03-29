package com.wx.base.service;

import com.wx.base.entity.after.ActivePrize;
import com.wx.base.service.support.IBaseService;
import com.wx.base.vo.AfterUserInfoVO;

public interface IActivePrizeService extends IBaseService<ActivePrize, Integer> {

    /**
     * @param activeCode
     * @param openid
     * @return
     */
    String checkUrl(String activeCode, String openid);

    /**
     * 登记赛事座位预约
     *
     * @param userInfo
     */
    void registerInfo(AfterUserInfoVO userInfo);
}
