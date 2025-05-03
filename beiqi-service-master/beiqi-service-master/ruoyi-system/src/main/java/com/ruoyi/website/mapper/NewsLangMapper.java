package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.NewsLang;
import com.ruoyi.website.domain.vo.NewsSearchResultVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 新闻多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface NewsLangMapper 
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
     * 删除新闻多语言
     * 
     * @param id 新闻多语言主键
     * @return 结果
     */
    public int deleteNewsLangById(Long id);

    /**
     * 批量删除新闻多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsLangByIds(Long[] ids);

    void batchInsert(@Param("list") List<NewsLang> list);

    List<NewsLang> selectByNewsId(@Param("newsId") Long newsId);

    void batchUpdate(@Param("list") List<NewsLang> list);

    void deleteByNewsIds(@Param("list") List<Long> list);

    List<NewsSearchResultVO> searchNewsList(@Param("lang") String lang, @Param("keyword") String keyword,
                                            @Param("categoryId") Long categoryId, @Param("recommend") Integer recommend);

    NewsSearchResultVO selectNewsDetail(@Param("lang") String lang, @Param("id") Long id);

    NewsSearchResultVO selectNewsDetailByCategoryId(@Param("lang") String lang, @Param("categoryId") Long categoryId);
}
