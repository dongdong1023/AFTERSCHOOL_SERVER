package com.wx.base.entity.after;

import com.wx.base.entity.support.BaseEntity;

import javax.persistence.*;

/**
 * 奖品配置表
 *
 * @author 东东
 * @date 2021/2/3 16:36
 */
@Entity
@Table(name = "after_active_prize_config")
public class ActivePrizeConfig extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * 活动CODE --> ACTIVITY_CODE
     */
    @Column(name = "activityCode", length = 128)
    private String activityCode;

    /**
     * 奖品CODE --> PRIZE_CODE
     */
    @Column(name = "prizeCode", length = 128)
    private String prizeCode;

    /**
     * 奖品名称 --> PRIZE_NAME
     */
    @Column(name = "prizeName", length = 256)
    private String prizeName;

    @Column(name = "prizeImg", length = 256)
    private String prizeImg;

    /**
     * 奖品排列序号 --> PRIZE_INDEX
     */
    @Column(name = "prizeIndex")
    private Integer prizeIndex;

    /**
     * 中奖概率 --> PRIZE_PROB
     */
    @Column(name = "prizeProb", columnDefinition = "double(10,2) default '0.00'")
    private Double prizeProb;

    /**
     * 会员中奖概率 --> PRIZE_PROB_VIP
     */
    @Column(name = "prizeProbVip", columnDefinition = "double(10,2) default '0.00'")
    private Double prizeProbVip;

    /**
     * 库存数量 --> REPERTORY
     */
    @Column(name = "repertory")
    private Integer repertory;

    /**
     * 0 没中奖    1 实体奖品   2 积分   3.票券--> TYPE
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 1可用    0不可用 --> STATUS
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 描述 --> DESCRIBE
     */
    @Column(name = "describe", length = 521)
    private String describe;

    /**
     * 奖品数量 --> AMOUNT
     */
    @Column(name = "amount")
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
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

    public String getPrizeImg() {
        return prizeImg;
    }

    public void setPrizeImg(String prizeImg) {
        this.prizeImg = prizeImg;
    }

    public Integer getPrizeIndex() {
        return prizeIndex;
    }

    public void setPrizeIndex(Integer prizeIndex) {
        this.prizeIndex = prizeIndex;
    }

    public Double getPrizeProb() {
        return prizeProb;
    }

    public void setPrizeProb(Double prizeProb) {
        this.prizeProb = prizeProb;
    }

    public Double getPrizeProbVip() {
        return prizeProbVip;
    }

    public void setPrizeProbVip(Double prizeProbVip) {
        this.prizeProbVip = prizeProbVip;
    }

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
