package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.NewsContentLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 新闻内容多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface NewsContentLangMapper 
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
     * 删除新闻内容多语言
     * 
     * @param id 新闻内容多语言主键
     * @return 结果
     */
    public int deleteNewsContentLangById(Long id);

    /**
     * 批量删除新闻内容多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsContentLangByIds(Long[] ids);

    void batchInsert(@Param("list") List<NewsContentLang> list);

    List<NewsContentLang> selectByContentId(@Param("newsContentId") Long newsContentId);

    void batchUpdate(@Param("list") List<NewsContentLang> list);

    void deleteByNewsContentIds(@Param("list") List<Long> list);

    void updateByNewsContentId(NewsContentLang newsContentLang);
}
