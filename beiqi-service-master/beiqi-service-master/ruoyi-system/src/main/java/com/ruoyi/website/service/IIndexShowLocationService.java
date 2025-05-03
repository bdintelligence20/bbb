package com.ruoyi.website.service;

import com.ruoyi.website.domain.IndexShowLocation;
import com.ruoyi.website.domain.vo.IndexShowLocationVO;

import java.util.List;
import java.util.Map;

/**
 * 首页配置Service接口
 * 
 * @author thh
 * @date 2022-06-21
 */
public interface IIndexShowLocationService 
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
    public List<IndexShowLocationVO> selectIndexShowLocationList();

    /**
     * 新增首页配置
     * @return 结果
     */
    public int insertIndexShowLocation(List<IndexShowLocationVO> locationVOS);

    /**
     * 修改首页配置
     * 
     * @return 结果
     */
    public int updateIndexShowLocation(List<IndexShowLocationVO> locationVOS);

    /**
     * 批量删除首页配置
     * 
     * @param ids 需要删除的首页配置主键集合
     * @return 结果
     */
    public int deleteIndexShowLocationByIds(List<Long> ids);

    /**
     * 删除首页配置信息
     * 
     * @param id 首页配置主键
     * @return 结果
     */
    public int deleteIndexShowLocationById(Long id);

    Map<Integer, String> selectIndexShowLocationType();

    List<IndexShowLocationVO> getIndexShowLocationList(String lang);
}
