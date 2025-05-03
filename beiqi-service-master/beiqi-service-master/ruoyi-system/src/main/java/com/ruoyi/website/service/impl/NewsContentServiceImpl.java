package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.NewsContent;
import com.ruoyi.website.mapper.NewsContentMapper;
import com.ruoyi.website.service.INewsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 新闻内容Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class NewsContentServiceImpl implements INewsContentService
{
    @Autowired
    private NewsContentMapper newsContentMapper;

    /**
     * 查询新闻内容
     * 
     * @param id 新闻内容主键
     * @return 新闻内容
     */
    @Override
    public NewsContent selectNewsContentById(Long id)
    {
        return newsContentMapper.selectNewsContentById(id);
    }

    /**
     * 查询新闻内容列表
     * 
     * @param newsContent 新闻内容
     * @return 新闻内容
     */
    @Override
    public List<NewsContent> selectNewsContentList(NewsContent newsContent)
    {
        return newsContentMapper.selectNewsContentList(newsContent);
    }

    /**
     * 新增新闻内容
     * 
     * @param newsContent 新闻内容
     * @return 结果
     */
    @Override
    public int insertNewsContent(NewsContent newsContent)
    {
        newsContent.setCreateTime(DateUtils.getNowDate());
        return newsContentMapper.insertNewsContent(newsContent);
    }

    /**
     * 修改新闻内容
     * 
     * @param newsContent 新闻内容
     * @return 结果
     */
    @Override
    public int updateNewsContent(NewsContent newsContent)
    {
        newsContent.setUpdateTime(DateUtils.getNowDate());
        return newsContentMapper.updateNewsContent(newsContent);
    }

    /**
     * 批量删除新闻内容
     * 
     * @param ids 需要删除的新闻内容主键
     * @return 结果
     */
    @Override
    public int deleteNewsContentByIds(Long[] ids)
    {
        return newsContentMapper.deleteNewsContentByIds(ids);
    }

    /**
     * 删除新闻内容信息
     * 
     * @param id 新闻内容主键
     * @return 结果
     */
    @Override
    public int deleteNewsContentById(Long id)
    {
        return newsContentMapper.deleteNewsContentById(id);
    }
}
