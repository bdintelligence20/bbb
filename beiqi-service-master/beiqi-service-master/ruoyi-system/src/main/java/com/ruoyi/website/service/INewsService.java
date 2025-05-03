package com.ruoyi.website.service;

import com.ruoyi.website.domain.News;
import com.ruoyi.website.domain.vo.NewsLangVO;
import com.ruoyi.website.domain.vo.NewsSearchResultVO;
import com.ruoyi.website.domain.vo.NewsVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 新闻Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface INewsService 
{
    /**
     * 查询新闻
     * 
     * @param id 新闻主键
     * @return 新闻
     */
    public NewsVO selectNewsById(Long id);

    /**
     * 查询新闻列表
     * 
     * @param news 新闻
     * @return 新闻集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 新增新闻
     * 
     * @param newsVO 新闻
     * @return 结果
     */
    public News insertNews(NewsVO newsVO);

    /**
     * 修改新闻
     * 
     * @param newsVO 新闻
     * @return 结果
     */
    public int updateNews(NewsVO newsVO);

    /**
     * 批量删除新闻
     * 
     * @param ids 需要删除的新闻主键集合
     * @return 结果
     */
    public int deleteNewsByIds(List<Long> ids);

    /**
     * 删除新闻信息
     * 
     * @param id 新闻主键
     * @return 结果
     */
    public int deleteNewsById(Long id);

    List<NewsSearchResultVO> search(String l, String keyword);

    List<NewsSearchResultVO> getNewsList(String l, Long categoryId, Integer recommend);

    NewsSearchResultVO getNewsDetail(String l, Long id);

    NewsSearchResultVO getOODetail(String l, Long categoryId);

}
