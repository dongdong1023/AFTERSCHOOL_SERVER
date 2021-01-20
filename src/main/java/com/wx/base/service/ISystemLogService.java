package com.wx.base.service;

import imba.game.base.entity.system.SystemLog;
import imba.game.base.service.support.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ISystemLogService extends IBaseService<SystemLog,Long> {

    /**
     * 查询队员数据
     * @param parame
     * @param pageable
     * @return
     */
    Page<SystemLog> getSystemLogByProperties(String parame, Pageable pageable);

    /**
     * 保存系统日志
     * @param systemLog
     */
    void saveSystemLog(SystemLog systemLog);

}
