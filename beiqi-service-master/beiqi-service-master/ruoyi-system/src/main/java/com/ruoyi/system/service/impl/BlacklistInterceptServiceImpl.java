package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.BlacklistIntercept;
import com.ruoyi.system.domain.RateLimiters;
import com.ruoyi.system.mapper.RateLimiterMapper;
import com.ruoyi.system.service.IBlacklistInterceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static com.ruoyi.common.constant.Constants.RATE_LIMIT_KEY;

@Service
public class BlacklistInterceptServiceImpl implements IBlacklistInterceptService {


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RateLimiterMapper rateLimiterMapper;
    /**
     * 新增黑名单
     *
     * @param blacklistIntercept 黑名单
     * @return 结果
     */
    @Override
    public boolean insertBlacklistIntercept(BlacklistIntercept blacklistIntercept)
    {
        String key =  RATE_LIMIT_KEY + blacklistIntercept.getIp() + "-" + blacklistIntercept.getMethod();
        redisTemplate.opsForValue().set(key, blacklistIntercept.getValue());
        return redisTemplate.expire(key, blacklistIntercept.getTime(), TimeUnit.HOURS);
    }

    @Override
    public boolean delectBlacklistIntercept(BlacklistIntercept blacklistIntercept) {
        String key =  RATE_LIMIT_KEY + blacklistIntercept.getIp() + "-" + blacklistIntercept.getMethod();
        return redisTemplate.delete(key);
    }

    @Override
    public List<BlacklistIntercept> selectBlacklistInterceptList(BlacklistIntercept blacklistIntercept) {

        List<BlacklistIntercept> blacklistInterceptList = new ArrayList<>();
        Set<Object> keys = redisTemplate.keys(RATE_LIMIT_KEY + "*" + blacklistIntercept.getMethod());
        RateLimiters rateLimiter = rateLimiterMapper.getRateLimiter(blacklistIntercept.getMethod());

        for (Object key : keys) {
            Long expire = redisTemplate.getExpire(key);
            Object o = redisTemplate.opsForValue().get(key);
            int value = (int)o;
            // 根据键的类型和内容创建BlacklistIntercept对象
            String[] keyParts = key.toString().split("\\-|:");
            BlacklistIntercept blacklistIntercept1 = new BlacklistIntercept();
            blacklistIntercept1.setIp(keyParts[1]);
            blacklistIntercept1.setValue(value);
            blacklistIntercept1.setTime(expire);
            // 将创建的BlacklistIntercept对象添加到列表中
            if (value > rateLimiter.getCount()){
                blacklistInterceptList.add(blacklistIntercept1);
            }
        }
        return blacklistInterceptList;

    }
}
