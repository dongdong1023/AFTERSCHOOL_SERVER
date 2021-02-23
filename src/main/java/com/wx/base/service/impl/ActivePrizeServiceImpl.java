package com.wx.base.service.impl;

import com.wx.base.common.Constats;
import com.wx.base.common.utils.AssertUtils;
import com.wx.base.common.utils.RegexUtil;
import com.wx.base.config.RedisUtil;
import com.wx.base.dao.IActivePrizeDao;
import com.wx.base.dao.support.IBaseDao;
import com.wx.base.entity.after.Active;
import com.wx.base.entity.after.ActivePrize;
import com.wx.base.entity.after.ActivePrizeConfig;
import com.wx.base.entity.after.AfterUserInfo;
import com.wx.base.service.IActivePrizeService;
import com.wx.base.service.support.impl.BaseServiceImpl;
import com.wx.base.vo.AfterUserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 东东
 * @date 2021/2/3 11:19
 */

@Service
public class ActivePrizeServiceImpl extends BaseServiceImpl<ActivePrize, Integer> implements IActivePrizeService {


    @Autowired
    private IActivePrizeDao activePrizeDao;

    @Autowired
    private ActiveServiceImpl activeServiceImpl;

    @Autowired
    private WxUserInfoServiceImpl userInfoServiceImpl;

    @Autowired
    private ActivePrizeConfigServiceImpl activePrizeConfigServiceImpl;

    @Autowired
    private RedisUtil redis;

    @Override
    public String checkUrl(String activeCode, String openid) {
        if (StringUtils.isBlank(activeCode) || StringUtils.isBlank(openid)) {
            return "/afterschool/error";
        } else {
            ActivePrize prize = this.getActivePrizeByActivityCodeAndOpenid(activeCode, openid);
            if (prize != null) {
                //已经参加活动
                return "/afterschool/appointment_success";
            } else {
                return "/afterschool/appointment";
            }
        }
    }

    @Override
    public void registerInfo(AfterUserInfoVO userInfoVo) {
        //1.信息校验
        AssertUtils.isNull(userInfoVo, Constats.RESULE_USER_ISNULL_ERROR);
        String activeCode = userInfoVo.getActiveCode();
        String prizeCode = userInfoVo.getPrizeCode();
        AssertUtils.isTrue(StringUtils.isBlank(activeCode) || StringUtils.isBlank(prizeCode), "请求繁忙,请重新进入预约");
        Date nowDate = new Date();
        //身份证、手机号、姓名验证
        AssertUtils.isTrue(!RegexUtil.matcher(Constats.ID_CARD, userInfoVo.getIdCard()), "身份证输入有误");
        AssertUtils.isTrue(!RegexUtil.matcher(Constats.MOBIL_PHONE, userInfoVo.getTelNum()), "手机号输入有误");
        AfterUserInfo userInfo = userInfoServiceImpl.getAfterUserInfoByOpenid(userInfoVo.getOpenId());
        //参与人员验证
        AssertUtils.isTrue((userInfo == null || userInfo.getSubscribe() == null || userInfo.getSubscribe() == 0), "预约失败,请关注<a href='https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=Mzg4NTU2NDM0MQ=='>【AfterSchool都市学园】</a>后继续预约");
        //2.活动校验
        Active active = activeServiceImpl.getActiveByCode(activeCode);
        AssertUtils.isNull(active, "活动正在策划中,敬请期待");
        //校验活动日期
        AssertUtils.isTrue(!(nowDate.after(active.getStartTime()) && nowDate.before(active.getEndTime())), "请在规定时间内参与预约");
        //3.奖品校验
        ActivePrizeConfig activePrizeConfig = activePrizeConfigServiceImpl.getActivePrizeConfigByActivityCodeAndPrizeCode(activeCode, prizeCode);
        AssertUtils.isNull(activePrizeConfig, "当前座位已预约完,请后续关注公众号【AfterSchool都市学园】最新消息");
        //更新用户信息
        userInfo.setRealname(userInfoVo.getRealname());
        userInfo.setTelNum(userInfoVo.getTelNum());
        userInfo.setIdCard(userInfoVo.getIdCard());
        ActivePrize prize = this.getActivePrizeByActivityCodeAndOpenid(activeCode, userInfoVo.getOpenId());
        AssertUtils.isTrue(prize != null, "您已提交信息,正在等待系统审核");
        prize = new ActivePrize();
        prize.setOpenid(userInfo.getOpenid());
        prize.setUnionid(userInfo.getUnionid());
        prize.setPrizeCode(activePrizeConfig.getPrizeCode());
        prize.setPrizeName(activePrizeConfig.getPrizeName());
        prize.setIsDraw(ActivePrize.NOT_DRAW);
        prize.setCreatTime(nowDate);
        userInfoServiceImpl.save(userInfo);
        redis.set(Constats.REDIS_USER_INFO_OPENID + userInfo.getOpenid(), userInfo);
        this.save(prize);
    }

    @Override
    public IBaseDao<ActivePrize, Integer> getBaseDao() {
        return this.activePrizeDao;
    }

    public ActivePrize getActivePrizeByActivityCodeAndPrizeCodeAndOpenid(String activityCode, String prizeCode, String openid) {
        return activePrizeDao.findActivePrizeByActivityCodeAndPrizeCodeAndOpenid(activityCode, prizeCode, openid);
    }

    public ActivePrize getActivePrizeByActivityCodeAndOpenid(String activityCode, String openid) {
        return activePrizeDao.findActivePrizeByActivityCodeAndOpenid(activityCode, openid);
    }

}
