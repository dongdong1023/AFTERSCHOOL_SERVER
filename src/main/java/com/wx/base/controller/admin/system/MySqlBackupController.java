package com.wx.base.controller.admin.system;

import com.wx.base.common.Constats;
import com.wx.base.common.DateEditor;
import com.wx.base.common.JsonResult;
import com.wx.base.common.utils.AssertUtils;
import com.wx.base.config.SystemConfig;
import com.wx.base.config.annotation.Log;
import com.wx.base.controller.BaseController;
import com.wx.base.entity.system.SystemLog;
import com.wx.base.service.IMysqlBackupService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "系统数据库备份还原")
@Controller
@RequestMapping("/admin/backup")
public class MySqlBackupController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(MySqlBackupController.class);

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private IMysqlBackupService mysqlBackupService;

    @Log(value = "查看数据备份", action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = {"/index"})
    public String index(ModelMap modelMap) {
        if (!new File(systemConfig.getBackupFilePath()).exists()) {
            // 初始默认备份文件SQL文件&&无法删除
            backup(Constats.BACKUP_DEFAULT_FILE_NAME_ALL);
        }
        List<Map<String, String>> backupRecords=mysqlBackupService.findAll();
        int start = (int) getPageRequest().getOffset();
        int end = (start + getPageRequest().getPageSize()) > backupRecords.size() ? backupRecords.size() : (start + getPageRequest().getPageSize());
        Page<Map<String, String>> page = new PageImpl<Map<String, String>>(backupRecords.subList(start, end), getPageRequest(), backupRecords.size());
        modelMap.put("pageInfo", page);
        return "system/backup";
    }

    @Log(value = "备份数据库", action = SystemLog.LOG_ACTION_UPDATE)
    @RequestMapping(value = {"/backup"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult backup() {
        String backupName = Constats.BACKUP_DEFAULT_FILE_NAME +  "_" + (new SimpleDateFormat(DateEditor.DATE_PATTERNS[8])).format(new Date()) + Constats.BACKUP_SQL_EXT;
        return backup(backupName);
    }


    @Log(value = "还原数据库", action = SystemLog.LOG_ACTION_UPDATE)
    @RequestMapping(value = {"/restore/{name:.+}"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult restore(@PathVariable String name) {
        try {
            AssertUtils.isTrue(StringUtils.isBlank(name), Constats.REQUEST_PARAM_ERROR);
            String host = systemConfig.getDbHost();
            String userName = systemConfig.getDbUserName();
            String password = systemConfig.getDbPassword();
            String database = systemConfig.getDbDatabase();
            String restoreFilePath = systemConfig.getBackupFilePath() + name;
            boolean restore = mysqlBackupService.restore(restoreFilePath, host, userName, password, database);
            return restore ? JsonResult.success() : JsonResult.failure("还原失败");
        } catch (Exception e) {
            log.error("还原数据出现异常:" + e.toString(),e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @Log(value = "删除备份文件", action = SystemLog.LOG_ACTION_DELETE)
    @RequestMapping(value = {"/delete/{name:.+}"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteBackupRecord(@PathVariable String name) {
        try {
            mysqlBackupService.delete(name);
            return JsonResult.success();
        } catch (Exception e) {
            log.error("删除备份文件出现异常:" + e.toString(),e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @Log(value = "设置为系统默认备份", action = SystemLog.LOG_ACTION_UPDATE)
    @RequestMapping(value = {"/setting/{name:.+}"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult setting(@PathVariable String name) {
        try {
            //将原始备份文件搁置为新文件名,设置备份的文件设置为系统备份名
            boolean bool = mysqlBackupService.setting(name);
            return bool?JsonResult.success():JsonResult.failure("设置失败");
        } catch (Exception e) {
            log.error("删除备份文件出现异常:" + e.toString(),e);
            return JsonResult.failure(e.getMessage());
        }
    }


    public JsonResult backup(String backupName) {
        try {
            String host = systemConfig.getDbHost();
            String userName = systemConfig.getDbUserName();
            String password = systemConfig.getDbPassword();
            String database = systemConfig.getDbDatabase();
            String backupFolderPath = systemConfig.getBackupFilePath();
            boolean backup = mysqlBackupService.backup(host, userName, password, backupFolderPath, backupName, database);
            return backup ? JsonResult.success() : JsonResult.failure("备份失败");
        } catch (Exception e) {
            log.error("备份数据出现异常:" + e.toString(),e);
            return JsonResult.failure(e.getMessage());
        }
    }
}
