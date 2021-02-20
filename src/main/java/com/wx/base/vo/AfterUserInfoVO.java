package com.wx.base.vo;

import com.wx.base.entity.after.AfterUserInfo;

/**
 * @author 东东
 * @date 2021/2/20 16:28
 */
public class AfterUserInfoVO {

    private AfterUserInfo userInfo;

    private String activeCode;

    private String prizeCode;

    public AfterUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(AfterUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public String getPrizeCode() {
        return prizeCode;
    }

    public void setPrizeCode(String prizeCode) {
        this.prizeCode = prizeCode;
    }

    public String getOpenid() {
        return userInfo.getOpenid();
    }

    public void setOpenid(String openid) {
        this.userInfo.setOpenid(openid);
    }

    public String getNickname() {
        return userInfo.getNickname();
    }

    public void setNickname(String nickname) {
        this.userInfo.setNickname(nickname);
    }

}
