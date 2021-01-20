package com.wx.base.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import imba.game.base.common.utils.CharUtils;
import imba.game.base.dao.ISystemLogDao;
import imba.game.base.dao.support.IBaseDao;
import imba.game.base.entity.system.QSystemLog;
import imba.game.base.entity.system.SystemLog;
import imba.game.base.service.ISystemLogService;
import imba.game.base.service.support.impl.BaseServiceImpl;
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
