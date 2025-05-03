package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.NewsIndexShowLocation;
import com.ruoyi.website.domain.vo.NewsIndexShowLocationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页新闻配置Mapper接口
 * 
 * @author thh
 * @date 2022-06-21
 */
public interface NewsIndexShowLocationMapper
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
    public List<NewsIndexShowLocationVO> selectIndexShowLocationList(@Param("lang") String lang);

    public List<NewsIndexShowLocationVO> selectIndexShowLocationVoList(@Param("lang") String lang);

    /**
     * 新增首页新闻配置
     * 
     * @param indexShowLocation 首页新闻配置
     * @return 结果
     */
    public int insertIndexShowLocation(NewsIndexShowLocation indexShowLocation);

    /**
     * 修改首页新闻配置
     * 
     * @param indexShowLocation 首页新闻配置
     * @return 结果
     */
    public int updateIndexShowLocation(NewsIndexShowLocation indexShowLocation);

    /**
     * 删除首页新闻配置
     * 
     * @param id 首页新闻配置主键
     * @return 结果
     */
    public int deleteIndexShowLocationById(Long id);

    /**
     * 批量删除首页新闻配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIndexShowLocationByIds(@Param("list") List<Long> ids);
}
