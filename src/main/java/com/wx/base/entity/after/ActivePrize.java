package com.wx.base.entity.after;

import com.wx.base.entity.support.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 中奖记录表
 *
 * @author 东东
 * @date 2021/2/3 10:04
 */
@Entity
@Table(name = "after_active_prize")
public class ActivePrize extends BaseEntity {

    public static final int NOT_DRAW = 0;    //未领取奖品
    public static final int IS_DRAW = 1;    //已领取奖品

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "openid", length = 128, nullable = false)
    private String openid;          //用户的标识，对当前公众号唯一

    @Column(name = "unionid", length = 128)
    private String unionid;          //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。


    /**
     * 活动CODE --> ACTIVITY_CODE
     */
    @Column(name = "activityCode", length = 128)
    private String activityCode;

    /**
     * 奖品code --> PRIZE_CODE
     */
    @Column(name = "prizeCode", length = 128)
    private String prizeCode;

    /**
     * 奖品名称 --> PRIZE_NAME
     */
    @Column(name = "prizeName", length = 128)
    private String prizeName;

    /**
     * 是否领取 --> IS_DRAW  0,未领取  1,已领取
     */
    @Column(name = "isDraw", length = 128)
    private Integer isDraw;

    /**
     * 中奖时间 --> CREAT_TIME
     */
    @Column(name = "createTime")
    private Date creatTime;

    /**
     * 领取时间 --> CREAT_TIME
     */
    @Column(name = "drawTime")
    private Date drawTime;

    @Column(name = "reserve1", length = 128)
    private String reserve1;

    @Column(name = "reserve2", length = 128)
    private String reserve2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Integer getIsDraw() {
        return isDraw;
    }

    public void setIsDraw(Integer isDraw) {
        this.isDraw = isDraw;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
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

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }
}
