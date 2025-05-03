package com.ruoyi.website.service;

import com.ruoyi.website.domain.vo.NewsIndexShowLocationVO;

import java.util.List;


/**
 * 首页新闻配置Service接口
 * 
 * @author thh
 * @date 2022-06-21
 */
public interface INewsIndexShowLocationService
{
    /**
     * 查询首页新闻配置
     * 
     * @param id 首页新闻配置主键
     * @return 首页新闻配置
     */
    public List<NewsIndexShowLocationVO> selectIndexShowLocationById(Long id);

    /**
     * 查询首页新闻配置列表
     * @return 首页新闻配置集合
     */
    public List<NewsIndexShowLocationVO> selectIndexShowLocationList();

    /**
     * 新增首页新闻配置
     * @return 结果
     */
    public int insertIndexShowLocation(List<NewsIndexShowLocationVO> locationVOS);

    /**
     * 修改首页新闻配置
     * 
     * @return 结果
     */
    public int updateIndexShowLocation(List<NewsIndexShowLocationVO> locationVOS);

    /**
     * 批量删除首页新闻配置
     * 
     * @param ids 需要删除的首页新闻配置主键集合
     * @return 结果
     */
    public int deleteIndexShowLocationByIds(List<Long> ids);

    /**
     * 删除首页新闻配置信息
     * 
     * @param id 首页新闻配置主键
     * @return 结果
     */
    public int deleteIndexShowLocationById(Long id);

    List<NewsIndexShowLocationVO> getIndexShowLocationList(String lang);

}
