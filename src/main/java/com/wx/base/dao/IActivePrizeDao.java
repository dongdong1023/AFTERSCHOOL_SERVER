package com.wx.base.dao;

import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.after.ActivePrize;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivePrizeDao extends IBaseDao<ActivePrize, Integer> {

    ActivePrize findActivePrizeByActivityCodeAndPrizeCodeAndOpenid(@Param("activityCode") String activityCode, @Param("prizeCode") String prizeCode, @Param("openid") String openid);

    ActivePrize findActivePrizeByActivityCodeAndOpenid(@Param("activityCode") String activityCode, @Param("openid") String openid);
}
