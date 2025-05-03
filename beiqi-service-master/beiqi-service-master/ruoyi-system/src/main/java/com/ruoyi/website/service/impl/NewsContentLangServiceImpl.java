package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.NewsContentLang;
import com.ruoyi.website.mapper.NewsContentLangMapper;
import com.ruoyi.website.service.INewsContentLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 新闻内容多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class NewsContentLangServiceImpl implements INewsContentLangService
{
    @Autowired
    private NewsContentLangMapper newsContentLangMapper;

    /**
     * 查询新闻内容多语言
     * 
     * @param id 新闻内容多语言主键
     * @return 新闻内容多语言
     */
    @Override
    public NewsContentLang selectNewsContentLangById(Long id)
    {
        return newsContentLangMapper.selectNewsContentLangById(id);
    }

    /**
     * 查询新闻内容多语言列表
     * 
     * @param newsContentLang 新闻内容多语言
     * @return 新闻内容多语言
     */
    @Override
    public List<NewsContentLang> selectNewsContentLangList(NewsContentLang newsContentLang)
    {
        return newsContentLangMapper.selectNewsContentLangList(newsContentLang);
    }

    /**
     * 新增新闻内容多语言
     * 
     * @param newsContentLang 新闻内容多语言
     * @return 结果
     */
    @Override
    public int insertNewsContentLang(NewsContentLang newsContentLang)
    {
        newsContentLang.setCreateTime(DateUtils.getNowDate());
        return newsContentLangMapper.insertNewsContentLang(newsContentLang);
    }

    /**
     * 修改新闻内容多语言
     * 
     * @param newsContentLang 新闻内容多语言
     * @return 结果
     */
    @Override
    public int updateNewsContentLang(NewsContentLang newsContentLang)
    {
        newsContentLang.setUpdateTime(DateUtils.getNowDate());
        return newsContentLangMapper.updateNewsContentLang(newsContentLang);
    }

    /**
     * 批量删除新闻内容多语言
     * 
     * @param ids 需要删除的新闻内容多语言主键
     * @return 结果
     */
    @Override
    public int deleteNewsContentLangByIds(Long[] ids)
    {
        return newsContentLangMapper.deleteNewsContentLangByIds(ids);
    }

    /**
     * 删除新闻内容多语言信息
     * 
     * @param id 新闻内容多语言主键
     * @return 结果
     */
    @Override
    public int deleteNewsContentLangById(Long id)
    {
        return newsContentLangMapper.deleteNewsContentLangById(id);
    }
}
