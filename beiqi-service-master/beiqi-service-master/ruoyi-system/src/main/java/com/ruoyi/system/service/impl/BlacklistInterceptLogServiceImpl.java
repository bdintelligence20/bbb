package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BlacklistInterceptLog;
import com.ruoyi.system.mapper.BlacklistInterceptLogMapper;
import com.ruoyi.system.service.IBlacklistInterceptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.ruoyi.common.constant.Constants.RATE_LIMIT_KEY;


/**
 * 黑名单拦截日志Service业务层处理
 * 
 * @author thh
 * @date 2023-09-06
 */
@Service
public class BlacklistInterceptLogServiceImpl implements IBlacklistInterceptLogService
{
    @Autowired
    private BlacklistInterceptLogMapper blacklistInterceptLogMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 查询黑名单拦截日志
     * 
     * @param id 黑名单拦截日志主键
     * @return 黑名单拦截日志
     */
    @Override
    public BlacklistInterceptLog selectBlacklistInterceptLogById(Long id)
    {
        return blacklistInterceptLogMapper.selectBlacklistInterceptLogById(id);
    }

    /**
     * 查询黑名单拦截日志列表
     * 
     * @param blacklistInterceptLog 黑名单拦截日志
     * @return 黑名单拦截日志
     */
    @Override
    public List<BlacklistInterceptLog> selectBlacklistInterceptLogList(BlacklistInterceptLog blacklistInterceptLog)
    {
        return blacklistInterceptLogMapper.selectBlacklistInterceptLogList(blacklistInterceptLog);
    }

    @EventListener
    @Async
    @Override
    public void listener(BlacklistInterceptLog blacklistInterceptLog) {
        blacklistInterceptLog.setCreateTime(DateUtils.getNowDate());
        blacklistInterceptLogMapper.insertBlacklistInterceptLog(blacklistInterceptLog);
    }


}
