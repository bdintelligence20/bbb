package com.ruoyi.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.RateLimiter;
import com.ruoyi.common.enums.LimitType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.domain.BlacklistInterceptLog;
import com.ruoyi.system.domain.RateLimiters;
import com.ruoyi.system.service.IRateLimiterService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 限流处理
 *
 * @author ruoyi
 */
@Aspect
@Component
public class RateLimiterAspect {
    private static final Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);

    private RedisTemplate<Object, Object> redisTemplate;

    private RedisScript<Long> limitScript;

    @Autowired
    private IRateLimiterService rateLimiterService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setLimitScript(RedisScript<Long> limitScript) {
        this.limitScript = limitScript;
    }

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws Throwable {
        //获取方法参数
        String method1 = getMethod(point);
        //查询限流注解配置
        RateLimiters rateLimiter1 = rateLimiterService.getRateLimiter(method1);
        int time = rateLimiter.time();
        int count = rateLimiter.count();

        String combineKey = getCombineKey(rateLimiter, point);
        if (rateLimiter1!=null) {
            time = rateLimiter1.getTime();
            count = rateLimiter1.getCount();
        }
        List<Object> keys = Collections.singletonList(combineKey);
        //获取方法参数
        String method = combineKey.substring(combineKey.indexOf("-") + 1);
        BlacklistInterceptLog blacklistInterceptLog = new BlacklistInterceptLog();
        //获取IP地址
        blacklistInterceptLog.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        //获取请求参数
        final String s = argsArrayToString(point.getArgs());
        blacklistInterceptLog.setParam(s);
        blacklistInterceptLog.setMethod(method);
        try {
            Long number = redisTemplate.execute(limitScript, keys, count, time);
            //判断访问是否查出限制
            if (StringUtils.isNull(number) || number.intValue() > count) {
                applicationEventPublisher.publishEvent(blacklistInterceptLog);
                throw new ServiceException("Request is too frequent, please wait and try again.");
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            applicationEventPublisher.publishEvent(blacklistInterceptLog);
            throw new RuntimeException("Request rate-limit exception, please wait and try again.");
        }
    }

    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuilder stringBuilder = new StringBuilder(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP) {
            stringBuilder.append(IpUtils.getIpAddr(ServletUtils.getRequest())).append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuilder.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuilder.toString();
    }

    public String getMethod(JoinPoint point){
        StringBuilder stringBuilder = new StringBuilder();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuilder.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuilder.toString();
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (Object o : paramsArray)
            {
                if (StringUtils.isNotNull(o) && !isFilterObject(o))
                {
                    try
                    {
                        Object jsonObj = JSON.toJSON(o);
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
