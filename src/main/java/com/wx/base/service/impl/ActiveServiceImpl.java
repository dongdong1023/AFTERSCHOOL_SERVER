package com.wx.base.service.impl;

import com.wx.base.dao.IActiveDao;
import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.after.Active;
import com.wx.base.entity.after.ActivePrize;
import com.wx.base.service.IActivePrizeService;
import com.wx.base.service.IActiveService;
import com.wx.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 东东
 * @date 2021/2/3 11:53
 */
@Service
public class ActiveServiceImpl extends BaseServiceImpl<Active, Integer> implements IActiveService {

    @Autowired
    private IActiveDao activeDao;

    @Override
    public IBaseDao<Active, Integer> getBaseDao() {
        return this.activeDao;
    }

    public Active getActiveByCode(String code) {
        return activeDao.findActiveByCode(code);
    }
}
