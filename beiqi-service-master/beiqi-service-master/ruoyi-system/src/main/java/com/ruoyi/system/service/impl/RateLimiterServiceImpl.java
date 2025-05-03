package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RateLimiterMapper;
import com.ruoyi.system.domain.RateLimiters;
import com.ruoyi.system.service.IRateLimiterService;

/**
 * 限流注解配置Service业务层处理
 * 
 * @author thh
 * @date 2023-09-08
 */
@Service
public class RateLimiterServiceImpl implements IRateLimiterService 
{
    @Autowired
    private RateLimiterMapper rateLimiterMapper;

    /**
     * 查询限流注解配置
     * 
     * @param id 限流注解配置主键
     * @return 限流注解配置
     */
    @Override
    public RateLimiters selectRateLimiterById(Long id)
    {
        return rateLimiterMapper.selectRateLimiterById(id);
    }

    /**
     * 查询限流注解配置列表
     * 
     * @param rateLimiters 限流注解配置
     * @return 限流注解配置
     */
    @Override
    public List<RateLimiters> selectRateLimiterList(RateLimiters rateLimiters)
    {
        return rateLimiterMapper.selectRateLimiterList(rateLimiters);
    }

    /**
     * 新增限流注解配置
     * 
     * @param rateLimiters 限流注解配置
     * @return 结果
     */
    @Override
    public int insertRateLimiter(RateLimiters rateLimiters)
    {
        return rateLimiterMapper.insertRateLimiter(rateLimiters);
    }

    /**
     * 修改限流注解配置
     * 
     * @param rateLimiters 限流注解配置
     * @return 结果
     */
    @Override
    @CacheEvict(value = "rateLimiter",key = "#rateLimiters.method")
    public int updateRateLimiter(RateLimiters rateLimiters)
    {
        return rateLimiterMapper.updateRateLimiter(rateLimiters);
    }

    /**
     * 批量删除限流注解配置
     * 
     * @param ids 需要删除的限流注解配置主键
     * @return 结果
     */
    @Override
    public int deleteRateLimiterByIds(Long[] ids)
    {
        return rateLimiterMapper.deleteRateLimiterByIds(ids);
    }

    /**
     * 删除限流注解配置信息
     * 
     * @param id 限流注解配置主键
     * @return 结果
     */
    @Override
    public int deleteRateLimiterById(Long id)
    {
        return rateLimiterMapper.deleteRateLimiterById(id);
    }



    @Cacheable(value = "rateLimiter", key = "#method",unless = "false")
    @Override
    public RateLimiters getRateLimiter(String method){
        RateLimiters rateLimiter = rateLimiterMapper.getRateLimiter(method);
        return rateLimiter;
    }
}
