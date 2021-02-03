package com.wx.base.controller.admin.system;

import com.wx.base.config.annotation.Log;
import com.wx.base.entity.system.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wx.base.common.JsonResult;
import com.wx.base.controller.BaseController;
import com.wx.base.entity.admin.Role;
import com.wx.base.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "角色管理接口")
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @Log(value = "进入角色管理",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = {"/index"})
    public String index(ModelMap modelMap) {
        Page<Role> page = roleService.findAll(getPageRequest());
        modelMap.put("pageInfo", page);
        return "admin/role/index";
    }

    @Log(value = "添加角色页面",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        return "admin/role/form";
    }


    @Log(value = "修改角色页面",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        Role role = roleService.find(id);
        map.put("role", role);
        return "admin/role/form";
    }

    @Log(value = "添加/修改角色信息",action = SystemLog.LOG_ACTION_UPDATE)
    @ApiOperation(value = "添加/修改角色信息")
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(Role role, ModelMap map) {
        try {
            roleService.saveOrUpdate(role);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @Log(value = "删除角色信息",action = SystemLog.LOG_ACTION_DELETE)
    @ApiOperation(value = "删除角色信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id, ModelMap map) {
        try {
            roleService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @Log(value = "角色分配页",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(@PathVariable Integer id, ModelMap map) {
        Role role = roleService.find(id);
        map.put("role", role);
        return "admin/role/grant";
    }

    @Log(value = "角色分配资源",action = SystemLog.LOG_ACTION_UPDATE)
    @ApiOperation(value = "角色分配资源")
    @RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult grant(@PathVariable Integer id,
                            @RequestParam(required = false) String[] resourceIds, ModelMap map) {
        try {
            roleService.grant(id, resourceIds);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
