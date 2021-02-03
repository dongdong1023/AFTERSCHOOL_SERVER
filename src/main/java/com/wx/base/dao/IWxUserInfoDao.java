package com.wx.base.dao;

import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.after.AfterUserInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IWxUserInfoDao extends IBaseDao<AfterUserInfo, Integer> {

    AfterUserInfo findAfterUserInfoByOpenid(@Param("openId") String openId);
}
