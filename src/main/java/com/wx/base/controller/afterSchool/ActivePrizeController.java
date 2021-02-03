package com.wx.base.controller.afterSchool;

import com.alibaba.fastjson.JSONObject;
import com.wx.base.common.JsonResult;
import com.wx.base.config.annotation.Log;
import com.wx.base.controller.BaseController;
import com.wx.base.entity.after.AfterUserInfo;
import com.wx.base.entity.system.SystemLog;
import com.wx.base.service.IActivePrizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 预约、抽奖记录
 *
 * @author 东东
 * @date 2021/2/3 11:03
 */
@Api(tags = "公众号活动预约、抽奖登记")
@Controller
@RequestMapping("/prize")
public class ActivePrizeController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(ActivePrizeController.class);

    @Autowired
    private IActivePrizeService activePrizeService;

    /**
     * 登记座位预约用户信息
     *
     * @return
     */
    @Log(value = "登记座位预约用户信息", action = SystemLog.LOG_ACTION_INSERT)
    @ApiOperation(value = "登记座位预约用户信息", notes = "获取Redis缓存库比分信息")
    @RequestMapping(value = {"/register/${openId}"}, method = RequestMethod.POST)
    public JsonResult register(AfterUserInfo userInfo, String activeCode, String prizeCode) {
        JsonResult jsonResult = JsonResult.success();
        try {
            activePrizeService.registerInfo(userInfo, activeCode, prizeCode);
            return jsonResult;
        } catch (Exception e) {
            log.error("登记座位预约用户信息出现异常:" + e.toString(), e);
            jsonResult = JsonResult.failure(e.getMessage());
            return jsonResult;
        }
    }
}
