package com.wx.base.controller.afterSchool;

import com.wx.base.Test;
import com.wx.base.config.annotation.Log;
import com.wx.base.controller.BaseController;
import com.wx.base.entity.after.AfterUserInfo;
import com.wx.base.entity.system.SystemLog;
import com.wx.base.service.IWxUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@Api(tags = "公众号进入首页")
@Controller
@RequestMapping("/after")
public class WxIndexController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WxIndexController.class);
    @Autowired
    private WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage;
    @Autowired
    private IWxUserInfoService userInfoService;

    @Log(value = "开始鉴权", action = SystemLog.LOG_ACTION_SELECT)
    @ApiOperation(value = "开始鉴权")
    @RequestMapping(value = {"/authorize"}, method = RequestMethod.GET)
    public void Authorize(HttpServletResponse response, @RequestParam("returnUrl") String returnUrl) throws Exception {
        returnUrl = URLEncoder.encode(returnUrl, "UTF-8");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wxMpInMemoryConfigStorage.getAppId() + "&redirect_uri=" + returnUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        response.sendRedirect(url);
    }

    @Log(value = "获取用户信息", action = SystemLog.LOG_ACTION_UPDATE)
    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = {"userInfo"})
    public String index(ModelMap map, @RequestParam("code") String code) {
        try {
            AfterUserInfo userInfo = userInfoService.userInfo(code);
            map.put("user", userInfo);
        } catch (Exception e) {
            log.error("跳转首页出现异常" + e.toString(), e);
        }
        return "index";
    }
}
