package com.wx.base.controller.admin;

import imba.game.base.config.annotation.Log;
import imba.game.base.controller.BaseController;
import imba.game.base.entity.system.SystemLog;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "登录接口")
@Controller
public class LoginController extends BaseController {
    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    @RequestMapping(value = {"/admin/login"}, method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        ModelMap model) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            //这两种方式设置session的超时时间单位均是毫秒：ms，shiro会将它转化为秒：s，并且省略掉小数部分；如果想让session永远不过期，就设置小于-1000的数值
            //设置shiro登录session永远不过期
            subject.getSession().setTimeout(-1001);
            return redirect("/admin/index");
        } catch (AuthenticationException e) {
            model.put("message", e.getMessage());
        }
        return "admin/login";
    }

    @Log(value = "退出登录",action = SystemLog.LOG_ACTION_LOGOUT)
    @RequestMapping(value = {"/admin/logout"}, method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return redirect("admin/login");
    }

}
