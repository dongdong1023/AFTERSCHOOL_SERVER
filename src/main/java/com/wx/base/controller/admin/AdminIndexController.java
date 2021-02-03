package com.wx.base.controller.admin;

import com.wx.base.config.annotation.Log;
import com.wx.base.controller.BaseController;
import com.wx.base.entity.system.SystemLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "进入首页接口")
@Controller
public class AdminIndexController extends BaseController {
	

    @Log(value = "进入首页",action = SystemLog.LOG_ACTION_SELECT)
	@ApiOperation(value = "进入首页")
    @RequestMapping(value = {"/admin/", "/admin/index"})
    public String index(ModelMap map,String parame) {
        //Page<Dream> page = dreamService.getDreamsByProperties(parame,getPageRequest());
        map.put("parame", parame);
        //map.put("pageInfo", page);
        return "admin/team/index";
    }
}
