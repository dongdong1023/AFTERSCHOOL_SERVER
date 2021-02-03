package com.wx.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wx.base.common.Constats;
import com.wx.base.common.utils.AssertUtils;
import com.wx.base.common.utils.HttpUtils;
import com.wx.base.config.RedisUtil;
import com.wx.base.controller.afterSchool.WxIndexController;
import com.wx.base.dao.IWxUserInfoDao;
import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.admin.Resource;
import com.wx.base.entity.after.AfterUserInfo;
import com.wx.base.service.IResourceService;
import com.wx.base.service.IWxUserInfoService;
import com.wx.base.service.support.impl.BaseServiceImpl;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 东东
 * @date 2021/1/28 13:40
 */

@Service
public class WxUserInfoServiceImpl extends BaseServiceImpl<AfterUserInfo, Integer> implements IWxUserInfoService {

    @Autowired
    private IWxUserInfoDao wxUserInfoDao;

    @Autowired
    private WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage;

    @Autowired
    private RedisUtil redis;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AfterUserInfo userInfo(String code) {
        AssertUtils.isNull(code, Constats.REQUEST_PARAM_ERROR);
        String appId = wxMpInMemoryConfigStorage.getAppId();
        String secret = wxMpInMemoryConfigStorage.getSecret();
        AssertUtils.isTrue(StringUtils.isBlank(appId) || StringUtils.isBlank(secret), Constats.REQUEST_PARAM_ERROR);
        String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        WxMpOAuth2AccessToken authInfo = JSONObject.parseObject(HttpUtils.sendGet(openIdUrl, "appid=" + appId + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code"), WxMpOAuth2AccessToken.class);
        AssertUtils.isNull(authInfo, "鉴权消息为空");
        AfterUserInfo userInfo = redis.getEntity(Constats.REDIS_USER_INFO_OPENID + authInfo.getOpenId(), AfterUserInfo.class);
        if (userInfo == null) {
            //创建用户信息
            userInfo = this.getAfterUserInfoByOpenid(authInfo.getOpenId());
            if (userInfo == null) {
                String getInfoUrl = " https://api.weixin.qq.com/sns/userinfo";
                userInfo = JSONObject.parseObject(HttpUtils.sendGet(getInfoUrl, "access_token=" + authInfo.getAccessToken() + "&openid=" + authInfo.getOpenId() + "&lang=zh_CN"), AfterUserInfo.class);
                AssertUtils.isNull(userInfo, "用户信息为空");
                Date creatTime = new Date();
                userInfo.setCreatTime(creatTime);
                userInfo.setUpdateTime(creatTime);
                //redis.set(Constats.REDIS_USER_INFO_TOKEN + userInfo.getOpenid(),authInfo.getAccessToken(),authInfo.getExpiresIn()-55);
                //redis.set(Constats.REDIS_USER_INFO_REFRESH + userInfo.getOpenid(),authInfo.getRefreshToken(),2590000);
                this.save(userInfo);
            } else {
                //DB用户信息存在更新Redis
            }
            redis.set(Constats.REDIS_USER_INFO_OPENID + userInfo.getOpenid(), userInfo);
        } else {
            //直接返回用户信息
        }
        return userInfo;
    }

    public AfterUserInfo getUserInfoByOpenId(String openId) {
        AfterUserInfo userInfo = redis.getEntity(Constats.REDIS_USER_INFO_OPENID + openId, AfterUserInfo.class);
        if (userInfo == null) {
            userInfo = this.getAfterUserInfoByOpenid(openId);
        } else {
            //直接返回用户信息
        }
        AssertUtils.isNull(userInfo, Constats.REQUEST_AUTH_ERROR);
        return userInfo;
    }

    @Override
    public IBaseDao<AfterUserInfo, Integer> getBaseDao() {
        return this.wxUserInfoDao;
    }

    public AfterUserInfo getAfterUserInfoByOpenid(String openId) {
        return wxUserInfoDao.findAfterUserInfoByOpenid(openId);
    }
}
