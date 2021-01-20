package com.wx.base.service.impl;

import imba.game.base.common.Constats;
import imba.game.base.common.DateEditor;
import imba.game.base.common.utils.AssertUtils;
import imba.game.base.common.utils.FileUtils;
import imba.game.base.config.SystemConfig;
import imba.game.base.service.IMysqlBackupService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MysqlBackupServiceImpl implements IMysqlBackupService {

    private static Logger log = LoggerFactory.getLogger(MysqlBackupServiceImpl.class);

    @Autowired
    private SystemConfig systemConfig;

    @Override
    public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName,
                          String database) throws Exception {
        File backupFolderFile = new File(backupFolderPath);
        if (!backupFolderFile.exists()) {
            // 如果目录不存在则创建
            backupFolderFile.mkdirs();
        }
        if (!backupFolderPath.endsWith(File.separator) && !backupFolderPath.endsWith("/")) {
            backupFolderPath = backupFolderPath + File.separator;
        }
        // 拼接命令行的命令
        String backupFilePath = backupFolderPath + fileName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump --opt ").append(" --add-drop-database ").append(" --add-drop-table ");
        stringBuilder.append(" -h").append(host).append(" -u").append(userName).append(" -p").append(password);
        stringBuilder.append(" --result-file=").append(backupFilePath).append(" --default-character-set=utf8 ").append(database);
        // 调用外部执行 exe 文件的 Java API
        Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
        if (process.waitFor() == 0) {
            // 0 表示线程正常终止
            log.info("数据已经备份到 " + backupFilePath + " 文件中");
            return true;
        }
        log.error("============备份失败============");
        return false;
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database)
            throws Exception {
        File restoreFile = new File(restoreFilePath);
        if (restoreFile.isDirectory()) {
            for (File file : restoreFile.listFiles()) {
                if (file.exists() && file.getPath().endsWith(".sql")) {
                    restoreFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysql -h").append(host).append(" -u").append(userName).append(" -p").append(password);
        stringBuilder.append(" ").append(database).append(" < ").append(restoreFilePath);
        try {
            Process process = Runtime.getRuntime().exec(getCommand(stringBuilder.toString()));
            if (process.waitFor() == 0) {
                log.info("数据已从 " + restoreFilePath + " 导入到数据库中");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        log.error("============还原失败============");
        return false;
    }

    @Override
    public void delete(String fileName) throws Exception {
        AssertUtils.isTrue(StringUtils.isBlank(fileName), Constats.REQUEST_PARAM_ERROR);
        AssertUtils.isTrue(Constats.BACKUP_DEFAULT_FILE_NAME_ALL.equals(fileName), Constats.DELETE_FILE_DEFAULT_ERROR);
        String restoreFilePath = systemConfig.getBackupFilePath() + fileName;
        FileUtils.deleteFile(new File(restoreFilePath));
    }

    @Override
    public boolean setting(String fileName) throws Exception {
        //将原始备份文件搁置为新文件名,设置备份的文件设置为系统备份名
        AssertUtils.isTrue(StringUtils.isBlank(fileName), Constats.REQUEST_PARAM_ERROR);
        String settingFilePath = systemConfig.getBackupFilePath();
        boolean bool1 = FileUtils.renameFile(settingFilePath,Constats.BACKUP_DEFAULT_FILE_NAME_ALL,Constats.BACKUP_DEFAULT_FILE_NAME +  "_" + (new SimpleDateFormat(DateEditor.DATE_PATTERNS[8])).format(new Date())+"【原系统】" + Constats.BACKUP_SQL_EXT);
        boolean bool2 = FileUtils.renameFile(settingFilePath,fileName,Constats.BACKUP_DEFAULT_FILE_NAME_ALL);
        return bool1&&bool2;
    }

    @Override
    public List<Map<String, String>> findAll() {
        List<Map<String, String>> backupRecords = new ArrayList<Map<String, String>>();
        File restoreFolderFile = new File(systemConfig.getBackupFilePath());
        if (restoreFolderFile.exists()) {
            for (File file : restoreFolderFile.listFiles()) {
                Map<String, String> backup = new HashMap<>();
                backup.put("name", file.getName());
                backup.put("title", file.getName());
                if (Constats.BACKUP_DEFAULT_FILE_NAME_ALL.equalsIgnoreCase(file.getName())) {
                    backup.put("title", "系统默认备份");
                }
                backupRecords.add(backup);
            }
        }
        // 排序，默认备份最前，然后按时间戳排序，新备份在前面
        backupRecords.sort((o1, o2) -> systemConfig.getBackupBase().equalsIgnoreCase(o1.get("name")) ? -1
                : systemConfig.getBackupBase().equalsIgnoreCase(o2.get("name")) ? 1 : o2.get("name").compareTo(o1.get("name")));
        return backupRecords;
    }

    /**
     * 检测备份到处系统 win/linux
     * @param command
     * @return
     */
    private static String[] getCommand(String command) {
        String os = System.getProperty("os.name");
        String shell = "/bin/bash";
        String c = "-c";
        if(os.toLowerCase().startsWith("win")){
            shell = "cmd";
            c = "/c";
        }
        String[] cmd = { shell, c, command };
        return cmd;
    }

}
