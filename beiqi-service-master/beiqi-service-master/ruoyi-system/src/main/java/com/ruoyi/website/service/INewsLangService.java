package com.ruoyi.website.service;

import com.ruoyi.website.domain.NewsLang;

import java.util.List;

/**
 * 新闻多语言Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface INewsLangService 
{
    /**
     * 查询新闻多语言
     * 
     * @param id 新闻多语言主键
     * @return 新闻多语言
     */
    public NewsLang selectNewsLangById(Long id);

    /**
     * 查询新闻多语言列表
     * 
     * @param newsLang 新闻多语言
     * @return 新闻多语言集合
     */
    public List<NewsLang> selectNewsLangList(NewsLang newsLang);

    /**
     * 新增新闻多语言
     * 
     * @param newsLang 新闻多语言
     * @return 结果
     */
    public int insertNewsLang(NewsLang newsLang);

    /**
     * 修改新闻多语言
     * 
     * @param newsLang 新闻多语言
     * @return 结果
     */
    public int updateNewsLang(NewsLang newsLang);

    /**
     * 批量删除新闻多语言
     * 
     * @param ids 需要删除的新闻多语言主键集合
     * @return 结果
     */
    public int deleteNewsLangByIds(Long[] ids);

    /**
     * 删除新闻多语言信息
     * 
     * @param id 新闻多语言主键
     * @return 结果
     */
    public int deleteNewsLangById(Long id);
}
