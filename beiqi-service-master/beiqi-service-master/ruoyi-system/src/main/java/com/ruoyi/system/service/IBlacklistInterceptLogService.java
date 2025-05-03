package com.ruoyi.system.service;

import com.ruoyi.system.domain.BlacklistInterceptLog;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.List;


/**
 * 黑名单拦截日志Service接口
 * 
 * @author thh
 * @date 2023-09-06
 */
public interface IBlacklistInterceptLogService 
{
    /**
     * 查询黑名单拦截日志
     * 
     * @param id 黑名单拦截日志主键
     * @return 黑名单拦截日志
     */
    public BlacklistInterceptLog selectBlacklistInterceptLogById(Long id);

    /**
     * 查询黑名单拦截日志列表
     * 
     * @param blacklistInterceptLog 黑名单拦截日志
     * @return 黑名单拦截日志集合
     */
    public List<BlacklistInterceptLog> selectBlacklistInterceptLogList(BlacklistInterceptLog blacklistInterceptLog);


    @EventListener
    @Async
    void listener(BlacklistInterceptLog blacklistInterceptLog);
}
