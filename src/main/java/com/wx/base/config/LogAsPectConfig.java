package com.wx.base.config;


import com.alibaba.fastjson.JSONObject;
import imba.game.base.common.utils.IpUtils;
import imba.game.base.config.annotation.Log;
import imba.game.base.entity.admin.User;
import imba.game.base.entity.system.SystemLog;
import imba.game.base.service.ISystemLogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @description 切面日志配置
 */
@Aspect
@Component
public class LogAsPectConfig {

    @Autowired
    private ISystemLogService systemLogService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(imba.game.base.config.annotation.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        try {
            result = point.proceed();
            insertLog(point);
        } catch (Throwable e) {
        }
        return result;
    }


    private void insertLog(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取shiro用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //添加描述
        JSONObject json = new JSONObject();
        //请求IP
        String ip = IpUtils.getIpAddr(request);
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());
        json.put("类名",className);
        json.put("方法名",methodName);
        json.put("参数",args);
        SystemLog sysLog = new SystemLog();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 注解上的描述
            String logValue = log.value();
            //TODO 后续判断
            if(StringUtils.isNotBlank(logValue) && request.getAttribute(logValue) != null){
                sysLog.setResult(SystemLog.RESULT_SUCCESS);
            }else{
                sysLog.setResult(SystemLog.RESULT_SUCCESS);
            }
            sysLog.setModule(logValue);
            sysLog.setUserAction(log.action());
        }
        sysLog.setIp(ip);
        sysLog.setCreateTime(new Date());
        sysLog.setUserId(user==null?-1:user.getId());
        sysLog.setDescription(json.toJSONString());
        systemLogService.saveSystemLog(sysLog);
    }
}
