package com.wx.base;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wx.base.common.utils.HttpUtils;
import com.wx.base.config.RedisUtil;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Controller
@RequestMapping("/test")
public class Test {

    private static final String appId = "wx3f3ff01f4027c9a8";

    private static final String appsecret = "4727f12936824112e425d4524f7e6553";

    private static Logger log = LoggerFactory.getLogger(Test.class);

    @Autowired
    private RedisUtil redis;

    @RequestMapping(value = {"/index"})
    public String goIndex(ModelMap map) {
        return "/index";
    }

    @RequestMapping(value = {"/authorize"}, method = RequestMethod.GET)
    public void getUser(HttpServletResponse response) throws Exception {
        String returnUrl = URLEncoder.encode("http://127.0.0.1:8080/test/userInfo", "UTF-8");
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId + "&redirect_uri=" + returnUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        log.info("redirectUrl={}", url);
        response.sendRedirect(url);
    }

    @RequestMapping(value = {"userInfo"})
    public String index(ModelMap map, @RequestParam("code") String code, HttpServletResponse response) throws Exception {
        log.info("获取微信openId接口 code={}", code);
        //1.通过code换取
        String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        WxMpOAuth2AccessToken authInfo = JSONObject.parseObject(HttpUtils.sendGet(openIdUrl, "appid=" + appId + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code"), WxMpOAuth2AccessToken.class);
        //{"access_token":"41_CZLUJommZlkVX2IaKiZlQGDBep8W3SxjEqK_hRFkFFDrOaBOjbl6UzFYc2pvYAmwzUnSA3D6hUnUdft6GVLwZw","expires_in":7200,"refresh_token":"41_RLqSrmaA1MwHC3kTE_pmilSfLk1UuJNQHIp-eI66W6BlQAzgwwz2TJsN5FYd3xi_XNml6JIcGBR7qSqwc7F6TQ","openid":"ovnrL6hcUNNJSRcvdkojgeuJxpzQ","scope":"snsapi_userinfo"}
        //2.通过拉取用户信息
        String getInfoUrl = " https://api.weixin.qq.com/sns/userinfo";
        WxMpUser userInfo = JSONObject.parseObject(HttpUtils.sendGet(getInfoUrl, "access_token=" + authInfo.getAccessToken() + "&openid=" + authInfo.getOpenId() + "&lang=zh_CN"), WxMpUser.class);
        log.info("userInfo:", userInfo.toString());
        map.put("userInfo", userInfo);
        return "index";
    }

    @RequestMapping(value = {"/data"}, method = RequestMethod.GET)
    @ResponseBody
    public String getToken() throws Exception {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId("wx3f3ff01f4027c9a8"); // 设置微信公众号的appid
        config.setSecret("4727f12936824112e425d4524f7e6553"); // 设置微信公众号的app corpSecret
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        String token = wxMpService.getAccessToken();
        return token;
    }
}
