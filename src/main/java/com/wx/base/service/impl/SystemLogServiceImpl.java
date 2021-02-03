package com.wx.base.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.wx.base.common.utils.CharUtils;
import com.wx.base.dao.ISystemLogDao;
import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.system.QSystemLog;
import com.wx.base.entity.system.SystemLog;
import com.wx.base.service.ISystemLogService;
import com.wx.base.service.support.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog, Long> implements ISystemLogService {

    private static final String LOG_CONTENT = "[类名]:%s,[方法]:%s,[参数]:%s,[IP]:%s";

    @Autowired
    private ISystemLogDao systemLogDao;

    @Override
    public IBaseDao<SystemLog, Long> getBaseDao() {
        return this.systemLogDao;
    }

    @Override
    public Page<SystemLog> getSystemLogByProperties(String parame, Pageable pageable) {
        QSystemLog systemLog = QSystemLog.systemLog;
        //初始化组装条件(类似where 1=1)
        Predicate predicate = systemLog.isNotNull().or(systemLog.isNull());
        predicate = parame == null ? predicate
                : ExpressionUtils.and(predicate,
                systemLog.userAction.like(CharUtils.returnLikeCondition(parame))
                        .or(systemLog.module.like(CharUtils.returnLikeCondition(parame))).
                        or(systemLog.ip.like(CharUtils.returnLikeCondition(parame))));
        Page<SystemLog> info = systemLogDao.findAll(predicate, pageable);
        return info;
    }

    @Override
    public void saveSystemLog(SystemLog systemLog) {
        systemLogDao.save(systemLog);
    }

}
