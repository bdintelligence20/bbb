package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.RateLimiters;

/**
 * 限流注解配置Mapper接口
 * 
 * @author thh
 * @date 2023-09-08
 */
public interface RateLimiterMapper 
{
    /**
     * 查询限流注解配置
     * 
     * @param id 限流注解配置主键
     * @return 限流注解配置
     */
    public RateLimiters selectRateLimiterById(Long id);

    /**
     * 查询限流注解配置列表
     * 
     * @param rateLimiters 限流注解配置
     * @return 限流注解配置集合
     */
    public List<RateLimiters> selectRateLimiterList(RateLimiters rateLimiters);

    /**
     * 新增限流注解配置
     * 
     * @param rateLimiters 限流注解配置
     * @return 结果
     */
    public int insertRateLimiter(RateLimiters rateLimiters);

    /**
     * 修改限流注解配置
     * 
     * @param rateLimiters 限流注解配置
     * @return 结果
     */
    public int updateRateLimiter(RateLimiters rateLimiters);

    /**
     * 删除限流注解配置
     * 
     * @param id 限流注解配置主键
     * @return 结果
     */
    public int deleteRateLimiterById(Long id);

    /**
     * 批量删除限流注解配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRateLimiterByIds(Long[] ids);

    public RateLimiters getRateLimiter(String method);
}
