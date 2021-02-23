package com.wx.base.controller.afterSchool;

import com.wx.base.config.annotation.Log;
import com.wx.base.controller.BaseController;
import com.wx.base.entity.after.AfterUserInfo;
import com.wx.base.entity.system.SystemLog;
import com.wx.base.service.IActivePrizeService;
import com.wx.base.service.IWxUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 东东
 * @date 2021/2/2 13:27
 */

@Api(tags = "公众号活动进入首页")
@Controller
@RequestMapping("/active")
public class ActiveIndexController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(ActiveIndexController.class);
    @Autowired
    private IWxUserInfoService userInfoService;
    @Autowired
    private IActivePrizeService activePrizeService;

    @Log(value = "进入赛事座位预约首页", action = SystemLog.LOG_ACTION_SELECT)
    @ApiOperation(value = "进入赛事座位预约首页")
    @RequestMapping(value = {"appointment"})
    public String index(ModelMap map, @RequestParam("code") String code, @RequestParam("activeCode") String activeCode) {
        try {
            AfterUserInfo userInfo = userInfoService.userInfo(code);
            String checkUrl = activePrizeService.checkUrl(activeCode, userInfo.getOpenid());
            map.put("user", userInfo);
            map.put("activeCode", activeCode);
            return checkUrl;
        } catch (Exception e) {
            map.put("error_text", "");
            log.error("跳转首页出现异常" + e.toString(), e);
            return "/afterschool/error";
        }
    }

    @RequestMapping(value = {"success"})
    public String success(ModelMap map) {
        return "/afterschool/appointment_success";
    }

}
