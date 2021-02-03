package com.wx.base.entity.system;

import com.wx.base.entity.support.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SYS_LOG")
public class SystemLog extends BaseEntity {

    public static final int RESULT_SUCCESS =  0;       //操作成功

    public static final int RESULT_ERROR =  1;         //操作失败

    public static final String LOG_ACTION_INSERT = "添加";

    public static final String LOG_ACTION_SELECT = "查询";

    public static final String LOG_ACTION_UPDATE = "修改";

    public static final String LOG_ACTION_DELETE = "删除";

    public static final String LOG_ACTION_LOGIN = "登录";

    public static final String LOG_ACTION_LOGOUT = "登出";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    /**
     * 操作人员ID
     */
    @Column(name = "USERID", columnDefinition = "INT default 0")
    private Integer userId;

    /**
     * 操作类型
     */
    @Column(name = "USERACTION", length = 32)
    private String userAction;

    /**
     * 操作模块
     */
    @Column(name = "MODULE", length = 128)
    private String module;

    /**
     * 操作结果:0成功  1失败
     */
    @Column(name = "RESULT", columnDefinition = "INT default 0")
    private Integer result;

    /**
     * 操作IP
     */
    @Column(name = "IP", length = 64)
    private String ip;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION", length = 1024)
    private String description;

    @Transient
    private String nickName;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private Date createTime;

    @Column(name = "RESERVE1", length = 128)
    private String reserve1;

    @Column(name = "RESERVE2", length = 128)
    private String reserve2;

    @Column(name = "RESERVE3", length = 255)
    private String reserve3;

    @Column(name = "RESERVE4", length = 255)
    private String reserve4;

    @Column(name = "RESERVE5", length = 512)
    private String reserve5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
