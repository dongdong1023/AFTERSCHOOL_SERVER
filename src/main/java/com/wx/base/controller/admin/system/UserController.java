package com.wx.base.controller.admin.system;

import com.wx.base.common.JsonResult;
import com.wx.base.common.utils.CharUtils;
import com.wx.base.config.annotation.Log;
import com.wx.base.controller.BaseController;
import com.wx.base.entity.admin.Role;
import com.wx.base.entity.admin.User;
import com.wx.base.entity.system.SystemLog;
import com.wx.base.service.IRoleService;
import com.wx.base.service.IUserService;
import com.wx.base.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Api(tags = "用户管理接口")
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Log(value = "用户管理页",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = {"/index"})
    public String index(ModelMap modelMap) {
        Page<User> page = userService.findAll(getPageRequest());
        modelMap.put("pageInfo", page);
        return "admin/user/index";
    }

    @Log(value = "添加用户页",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap map) {
        return "admin/user/form";
    }

    @Log(value = "修改用户页",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap map) {
        User user = userService.find(id);
        map.put("user", user);
        return "admin/user/form";
    }

    @Log(value = "添加/修改用户信息",action = SystemLog.LOG_ACTION_UPDATE)
    @ApiOperation(value = "添加/修改用户信息")
    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    @ResponseBody
    public JsonResult edit(UserVO userVO, ModelMap map) {
        try {
            User user = userVO.getUser();
            String iconPath = upLoadPictureFile(user.ICON_FOLDER, userVO.getPoster());
            if (StringUtils.isNotBlank(iconPath)) {
                //加“/”shrio中默认缓存
                user.setAvatar(CharUtils.BACKSLASH + iconPath);
            }
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @Log(value = "删除用户信息",action = SystemLog.LOG_ACTION_DELETE)
    @ApiOperation(value = "删除用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable Integer id, ModelMap map) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @Log(value = "分配角色",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(@PathVariable Integer id, ModelMap map) {
        User user = userService.find(id);
        map.put("user", user);

        Set<Role> set = user.getRoles();
        List<Integer> roleIds = new ArrayList<Integer>();
        for (Role role : set) {
            roleIds.add(role.getId());
        }
        map.put("roleIds", roleIds);

        List<Role> roles = roleService.findAll();
        map.put("roles", roles);
        return "admin/user/grant";
    }


    @Log(value = "分配角色",action = SystemLog.LOG_ACTION_UPDATE)
    @ApiOperation(value = "给用户分配角色")
    @ResponseBody
    @RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
    public JsonResult grant(@PathVariable Integer id, String[] roleIds, ModelMap map) {
        try {
            userService.grant(id, roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }

    @Log(value = "修改密码",action = SystemLog.LOG_ACTION_SELECT)
    @RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
    public String updatePwd() {
        return "admin/user/updatePwd";
    }

    @Log(value = "修改用户密码",action = SystemLog.LOG_ACTION_UPDATE)
    @ApiOperation(value = "修改用户密码")
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updatePwd(String oldPassword, String password1, String password2) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject.getPrincipal();
            if (principal == null) {
                return JsonResult.failure("您尚未登录");
            }
            userService.updatePwd((User) principal, oldPassword, password1, password2);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.failure(e.getMessage());
        }
        return JsonResult.success();
    }
}
