package com.wx.base.controller.admin.system;

import imba.game.base.config.annotation.Log;
import imba.game.base.controller.BaseController;
import imba.game.base.entity.admin.User;
import imba.game.base.entity.system.SystemLog;
import imba.game.base.service.ISystemLogService;
import imba.game.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/systemLog")
public class SystemLogController extends BaseController {

    @Autowired
    private ISystemLogService systemLogService;

    @Autowired
    private IUserService userService;

    /**
     * 进入查看系统日志
     * @param modelMap
     * @param parame
     * @return
     */
    @Log(value = "查看系统日志",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = {"/index"})
    public String index(ModelMap modelMap,String parame) {
        Page<SystemLog> page = systemLogService.getSystemLogByProperties(parame,getPageRequest());
        List<SystemLog> systemLogs = new ArrayList<SystemLog>();
        for(SystemLog systemLog : page.getContent()){
            User user = userService.getUserById(systemLog.getUserId());
            if(user != null){
                systemLog.setNickName(user.getNickName());
            }else {
                systemLog.setNickName("未知");
            }
            systemLogs.add(systemLog);
        }
        modelMap.put("parame", parame);
        modelMap.put("systemLogs", systemLogs);
        modelMap.put("pageInfo", page);
        return "system/log";
    }
}
