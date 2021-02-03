package com.wx.base.dao;

import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.system.SystemLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ISystemLogDao extends IBaseDao<SystemLog, Long> {


}
