package com.ruoyi.framework.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.http.HttpHelper;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.material.service.IMaterialLogService;
import com.ruoyi.system.domain.MaterialLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日志
 */
@Component
public class LoggerInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Autowired
    private IMaterialLogService logService;

    @Autowired
    private TokenService tokenService;

    ExecutorService executor = Executors.newFixedThreadPool(2);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }


    //@Async
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        final LoginUser loginUser = tokenService.getLoginUser(request);
        final String clientIpAddress = IpUtils.getIpAddr(ServletUtils.getRequest());
        final String servletPath = request.getServletPath();
        final String method = request.getMethod();
        final Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        MaterialLog log = new MaterialLog();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            for (String value : entry.getValue()) {
                sb.append(entry.getKey()).append('=').append(value).append('&');
                if (entry.getKey().equals("type")){
                    log.setType(value);
                }else if (entry.getKey().equals("model")){
                    log.setModel(value);
                }
            }
        }
        String parameterString = sb.toString();
        final String bodyString = HttpHelper.getBodyString(request);
        final String resStr = response.toString();
        //异步执行记录日志防止阻塞主线程任务
        Callable<String> task = () -> {
            //Thread.sleep(5000);
            try {
                ObjectMapper om = new ObjectMapper();
                logger.info("ip地址：{}", clientIpAddress);
                logger.info("请求参数：{}", parameterString);
                //写入结果

                log.setUserId(loginUser != null ? loginUser.getUserId() : 0L);
                log.setIp(clientIpAddress);
                log.setMethod(method);
                log.setPath(servletPath);
                log.setUrlParameter(parameterString);
                log.setBodyParameter(bodyString);
                log.setResponseData(resStr);
                logService.insertMaterialLog(log);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return "";
        };
        //忽略返回结果
        executor.submit(task);

    }
}

