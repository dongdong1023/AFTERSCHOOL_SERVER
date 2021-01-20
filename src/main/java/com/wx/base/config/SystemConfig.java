package com.wx.base.config;

import imba.game.base.common.utils.CharUtils;
import imba.game.base.common.utils.FileNameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Mr_dong
 * @ClassName: SystemConfig
 * @Description:(这里用一句话描述这个类的作用)
 * @date 2019年3月11日
 */
@Configuration
public class SystemConfig {

    @Value("${picture.dir}")
    private String picPath;

    private String patternPath;

    @Value("${resource.file.path}")
    private String resourceFilePath;

    @Value("${spring.datasource.url}")
    private String dbHost;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    private String dbDatabase;

    @Value("${backup.dir}")
    private String backupFilePath;

    @Value("${game.token}")
    private String token;

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }


    public String getResourceFilePath() {
        return resourceFilePath;
    }

    public void setResourceFilePath(String resourceFilePath) {
        this.resourceFilePath = resourceFilePath;
    }

    public String getPicFullPath(String relativePath) throws FileNotFoundException {
        String path = ResourceUtils.getURL("file:").getPath();
        relativePath = FileNameUtils.getAbsolutePath(resourceFilePath + CharUtils.BACKSLASH + picPath, relativePath);
        return FileNameUtils.getAbsolutePath(path, relativePath);
    }

    public String getPatternPath() throws IOException {
        try {
            return imba.game.base.common.utils.FileUtils.getInstance().getFileByPath(resourceFilePath + CharUtils.BACKSLASH + "patterns").getPath();
        } catch (Exception e) {
            return null;
        }

    }

    public void setPatternPath(String patternPath) {
        this.patternPath = patternPath;
    }


    public String getDbHost() {
        return StringUtils.isBlank(dbHost) ? "" : dbHost.substring(dbHost.indexOf("//") + 2, dbHost.lastIndexOf(":"));
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }


    public String getDbDatabase() {
        return StringUtils.isBlank(dbHost) ? "" : dbHost.substring(dbHost.lastIndexOf("/") + 1, dbHost.indexOf("?"));
    }

    /**
     * SQL文件备份还原文件夹
     *
     * @return
     */
    public String getBackupBase() {
        return backupFilePath;
    }

    /**
     * SQL文件备份还原目录
     *
     * @return
     */
    public String getBackupFilePath() {
        return resourceFilePath + CharUtils.BACKSLASH + backupFilePath + CharUtils.BACKSLASH;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
