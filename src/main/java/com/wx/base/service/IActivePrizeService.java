package com.wx.base.service;

import com.wx.base.entity.after.ActivePrize;
import com.wx.base.entity.after.AfterUserInfo;
import com.wx.base.service.support.IBaseService;

public interface IActivePrizeService extends IBaseService<ActivePrize, Integer> {

    /**
     * 登记赛事座位预约
     *
     * @param userInfo
     */
    void registerInfo(AfterUserInfo userInfo, String activeCode, String prizeCode);
}
