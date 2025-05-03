package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.BlacklistInterceptLog;

import java.util.List;

/**
 * 黑名单拦截日志Mapper接口
 * 
 * @author thh
 * @date 2023-09-06
 */
public interface BlacklistInterceptLogMapper 
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

    /**
     * 新增黑名单拦截日志
     * 
     * @param blacklistInterceptLog 黑名单拦截日志
     * @return 结果
     */
    public int insertBlacklistInterceptLog(BlacklistInterceptLog blacklistInterceptLog);

}
