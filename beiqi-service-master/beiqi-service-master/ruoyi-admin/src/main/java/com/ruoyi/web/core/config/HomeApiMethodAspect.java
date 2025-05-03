/*
package com.ruoyi.web.core.config;

import com.ruoyi.common.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * 方法调用统一切面处理
 *//*

@Aspect
@Component
@Slf4j
public class HomeApiMethodAspect {

    @Pointcut("execution(public * com.ruoyi.web.controller.*.*.*(..))")
    public void pkg() {
    }

    @Around("pkg()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature sign = pjp.getSignature();
        MethodSignature ms = (MethodSignature) sign;
        Map<String, Object> params = new HashMap<>();
        Object[] args = pjp.getArgs();
        String[] paramNames = ms.getParameterNames();
        for (int i = 0; i < args.length; i++) {
            params.put(paramNames[i], JsonUtil.toJSONString(args[i]));
        }
        Object result = pjp.proceed();
        log.info("\n>>>>>>{} {}\n>>>>>>Request Ip: {}\n>>>>>>Request: {}\n>>>>>>Response: {}",
                request.getMethod(), request.getRequestURL(), request.getRemoteAddr(), JsonUtil.toJSONString(params), JsonUtil.toJSONString(result));
        return result;
    }
}
*/
