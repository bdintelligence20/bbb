package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.NewsIndexShowLocationLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NewsIndexShowLocationLangMapper {
    /**
     * 查询首页新闻配置多语言
     * 
     * @param id 首页新闻配置多语言主键
     * @return 首页新闻配置多语言
     */
    public NewsIndexShowLocationLang selectNewsIndexShowLocationLangById(Long id);

    /**
     *
     * @param indexShowLocationLang
     * @return
     */
    public List<NewsIndexShowLocationLang> selectNewsIndexShowLocationLangList(NewsIndexShowLocationLang indexShowLocationLang);

    /**
     * 新增首页新闻配置多语言
     * @param indexShowLocationLang
     * @return 结果
     */
    public int insertNewsIndexShowLocationLang(NewsIndexShowLocationLang indexShowLocationLang);

    /**
     * 修改首页新闻配置多语言
     * @param indexShowLocationLang
     * @return 结果
     */
    public int updateNewsIndexShowLocationLang(NewsIndexShowLocationLang indexShowLocationLang);

    /**
     * 删除首页新闻配置多语言
     * 
     * @param id 首页新闻配置多语言主键
     * @return 结果
     */
    public int deleteNewsIndexShowLocationLangById(Long id);

    /**
     * 批量删除首页新闻配置多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsIndexShowLocationLangByIds(@Param("list") List<Long> ids);

    void batchInsert(@Param("list") List<NewsIndexShowLocationLang> list);
}
