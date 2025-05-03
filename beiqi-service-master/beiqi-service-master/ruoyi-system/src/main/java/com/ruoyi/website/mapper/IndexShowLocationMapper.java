package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.IndexShowLocation;
import com.ruoyi.website.domain.vo.IndexShowLocationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页配置Mapper接口
 * 
 * @author thh
 * @date 2022-06-21
 */
public interface IndexShowLocationMapper 
{
    /**
     * 查询首页配置
     * 
     * @param id 首页配置主键
     * @return 首页配置
     */
    public List<IndexShowLocationVO> selectIndexShowLocationById(Long id);

    /**
     * 查询首页配置列表
     * @return 首页配置集合
     */
    public List<IndexShowLocationVO> selectIndexShowLocationList(@Param("lang") String lang);

    /**
     * 新增首页配置
     * 
     * @param indexShowLocation 首页配置
     * @return 结果
     */
    public int insertIndexShowLocation(IndexShowLocation indexShowLocation);

    /**
     * 修改首页配置
     * 
     * @param indexShowLocation 首页配置
     * @return 结果
     */
    public int updateIndexShowLocation(IndexShowLocation indexShowLocation);

    /**
     * 删除首页配置
     * 
     * @param id 首页配置主键
     * @return 结果
     */
    public int deleteIndexShowLocationById(Long id);

    /**
     * 批量删除首页配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIndexShowLocationByIds(@Param("list") List<Long> ids);
}
