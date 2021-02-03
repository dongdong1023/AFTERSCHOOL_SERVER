package com.wx.base.dao;

import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.after.ActivePrizeConfig;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivePrizeConfigDao extends IBaseDao<ActivePrizeConfig, Integer> {

    ActivePrizeConfig findActivePrizeConfigByActivityCodeAndPrizeCode(@Param("activityCode") String activityCode, @Param("prizeCode") String prizeCode);
}
