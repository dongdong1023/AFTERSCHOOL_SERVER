package com.wx.base.service.task;

import com.wx.base.common.JsonResult;
import com.wx.base.controller.admin.system.MySqlBackupController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 自动备份Mysql数据库
 */
@Component
@EnableScheduling
public class AutoBackUpTask {

    private static Logger log = LoggerFactory.getLogger(AutoBackUpTask.class);

    @Autowired
    private MySqlBackupController mySqlBackup;

    @Scheduled(cron = "0 15 10 ? * MON")
    public void run() {
        try {
            log.info("开始自动备份Mysql...");
            JsonResult result =  mySqlBackup.backup();
            log.info("自动备份Mysql操作完成" + result.getMessage() );
        } catch (Exception e) {
            log.error("自动备份Mysql异常:" + e.getMessage());
        }

    }
}
