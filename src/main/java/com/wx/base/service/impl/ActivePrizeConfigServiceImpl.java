package com.wx.base.service.impl;

import com.wx.base.dao.IActivePrizeConfigDao;
import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.after.ActivePrize;
import com.wx.base.entity.after.ActivePrizeConfig;
import com.wx.base.service.IActivePrizeConfigService;
import com.wx.base.service.IActivePrizeService;
import com.wx.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 东东
 * @date 2021/2/3 16:54
 */
@Service
public class ActivePrizeConfigServiceImpl extends BaseServiceImpl<ActivePrizeConfig, Integer> implements IActivePrizeConfigService {

    @Autowired
    private IActivePrizeConfigDao activePrizeConfigDao;

    @Override
    public IBaseDao<ActivePrizeConfig, Integer> getBaseDao() {
        return this.activePrizeConfigDao;
    }

    public ActivePrizeConfig getActivePrizeConfigByActivityCodeAndPrizeCode(String activityCode, String prizeCode) {
        return activePrizeConfigDao.findActivePrizeConfigByActivityCodeAndPrizeCode(activityCode, prizeCode);
    }
}
