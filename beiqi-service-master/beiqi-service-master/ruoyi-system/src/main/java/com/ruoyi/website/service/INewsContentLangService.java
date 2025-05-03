package com.ruoyi.website.service;

import com.ruoyi.website.domain.NewsContentLang;

import java.util.List;

/**
 * 新闻内容多语言Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface INewsContentLangService 
{
    /**
     * 查询新闻内容多语言
     * 
     * @param id 新闻内容多语言主键
     * @return 新闻内容多语言
     */
    public NewsContentLang selectNewsContentLangById(Long id);

    /**
     * 查询新闻内容多语言列表
     * 
     * @param newsContentLang 新闻内容多语言
     * @return 新闻内容多语言集合
     */
    public List<NewsContentLang> selectNewsContentLangList(NewsContentLang newsContentLang);

    /**
     * 新增新闻内容多语言
     * 
     * @param newsContentLang 新闻内容多语言
     * @return 结果
     */
    public int insertNewsContentLang(NewsContentLang newsContentLang);

    /**
     * 修改新闻内容多语言
     * 
     * @param newsContentLang 新闻内容多语言
     * @return 结果
     */
    public int updateNewsContentLang(NewsContentLang newsContentLang);

    /**
     * 批量删除新闻内容多语言
     * 
     * @param ids 需要删除的新闻内容多语言主键集合
     * @return 结果
     */
    public int deleteNewsContentLangByIds(Long[] ids);

    /**
     * 删除新闻内容多语言信息
     * 
     * @param id 新闻内容多语言主键
     * @return 结果
     */
    public int deleteNewsContentLangById(Long id);
}
