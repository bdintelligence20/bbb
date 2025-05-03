package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.NewsContent;
import org.apache.ibatis.annotations.Param;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.LinkAttribute;

import java.util.List;

/**
 * 新闻内容Mapper接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface NewsContentMapper 
{
    /**
     * 查询新闻内容
     * 
     * @param id 新闻内容主键
     * @return 新闻内容
     */
    public NewsContent selectNewsContentById(Long id);

    /**
     * 查询新闻内容列表
     * 
     * @param newsContent 新闻内容
     * @return 新闻内容集合
     */
    public List<NewsContent> selectNewsContentList(NewsContent newsContent);

    /**
     * 新增新闻内容
     * 
     * @param newsContent 新闻内容
     * @return 结果
     */
    public int insertNewsContent(NewsContent newsContent);

    /**
     * 修改新闻内容
     * 
     * @param newsContent 新闻内容
     * @return 结果
     */
    public int updateNewsContent(NewsContent newsContent);

    /**
     * 删除新闻内容
     * 
     * @param id 新闻内容主键
     * @return 结果
     */
    public int deleteNewsContentById(Long id);

    /**
     * 批量删除新闻内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsContentByIds(Long[] ids);

    NewsContent selectByNewsId(@Param("newsId") Long newsId);

    void deleteByNewsIds(@Param("list") List<Long> list);

    List<NewsContent> selectByNewsIds(@Param("list") List<Long> list);
}
