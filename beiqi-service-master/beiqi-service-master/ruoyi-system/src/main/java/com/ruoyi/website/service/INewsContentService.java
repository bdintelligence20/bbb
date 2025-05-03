package com.ruoyi.website.service;

import com.ruoyi.website.domain.NewsContent;

import java.util.List;

/**
 * 新闻内容Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface INewsContentService 
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
     * 批量删除新闻内容
     * 
     * @param ids 需要删除的新闻内容主键集合
     * @return 结果
     */
    public int deleteNewsContentByIds(Long[] ids);

    /**
     * 删除新闻内容信息
     * 
     * @param id 新闻内容主键
     * @return 结果
     */
    public int deleteNewsContentById(Long id);
}
