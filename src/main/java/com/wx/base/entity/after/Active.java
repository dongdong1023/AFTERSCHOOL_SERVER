package com.wx.base.entity.after;

import com.wx.base.entity.support.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动配置
 *
 * @author 东东
 * @date 2021/2/3 9:49
 */
@Entity
@Table(name = "after_active")
public class Active extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    // 活动编码
    @Column(name = "code", length = 128)
    private String code;

    // 活动名称
    @Column(name = "activeName", length = 128)
    private String activeName;

    // 活动类型
    @Column(name = "activeType", length = 128)
    private String activeType;

    // 活动描述
    @Column(name = "description", length = 255)
    private String description;

    // 开始时间
    @Column(name = "startTime")
    private Date startTime;

    // 结束时间
    @Column(name = "endTime")
    private Date endTime;

    // 创建时间
    @Column(name = "createTime")
    private Date createTime;

    // 修改时间
    @Column(name = "updateTime")
    private Date updateTime;

    // 上线状态
    @Column(name = "status")
    private Integer status;

    // 针对用户类型确定:0:全部;1:电竞用户;2:二次元用户;3：其他
    @Column(name = "activeUserType", length = 128)
    private String activeUserType;

    // 活动排序顺序
    @Column(name = "orders", length = 32)
    private Integer orders;

    // 参加活动消耗积分(为0则不允许消耗积分)
    @Column(name = "consumePoints")
    private Integer consumePoints;

    @Column(name = "reserve1", length = 128)
    private String reserve1;

    @Column(name = "reserve2", length = 128)
    private String reserve2;

    @Column(name = "reserve3", length = 256)
    private String reserve3;

    @Column(name = "reserve4", length = 256)
    private String reserve4;

    @Column(name = "reserve5", length = 521)
    private String reserve5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public String getActiveType() {
        return activeType;
    }

    public void setActiveType(String activeType) {
        this.activeType = activeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getActiveUserType() {
        return activeUserType;
    }

    public void setActiveUserType(String activeUserType) {
        this.activeUserType = activeUserType;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getConsumePoints() {
        return consumePoints;
    }

    public void setConsumePoints(Integer consumePoints) {
        this.consumePoints = consumePoints;
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

}
