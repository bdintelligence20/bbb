package com.ruoyi.website.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.home.contact.service.SubscribeService;
import com.ruoyi.website.domain.*;
import com.ruoyi.website.domain.vo.NLangVO;
import com.ruoyi.website.domain.vo.NewsLangVO;
import com.ruoyi.website.domain.vo.NewsSearchResultVO;
import com.ruoyi.website.domain.vo.NewsVO;
import com.ruoyi.website.mapper.*;
import com.ruoyi.website.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 新闻Service业务层处理
 *
 * @author thh
 * @date 2022-06-01
 */
@Service
@Slf4j
public class NewsServiceImpl implements INewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsContentMapper newsContentMapper;

    @Autowired
    private NewsLangMapper newsLangMapper;

    @Autowired
    private NewsContentLangMapper newsContentLangMapper;

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private CategoryLangMapper categoryLangMapper;

    @Autowired
    private WebSiteCategoryMapper webSiteCategoryMapper;

    /**
     * 查询新闻
     *
     * @param id 新闻主键
     * @return 新闻
     */
    @Override
    public NewsVO selectNewsById(Long id) {
        if (ObjectUtil.isEmpty(id)) {
            throw new ServiceException("参数不能为空");
        }

        List<NewsLangVO> newsLangVOS = new ArrayList<>();
        News news = newsMapper.selectNewsById(id);
        NewsContent newsContent = newsContentMapper.selectByNewsId(id);
        NewsLangVO newsLangVO = new NewsLangVO();
        BeanUtils.copyProperties(news, newsLangVO);
        newsLangVO.setContent(newsContent.getContent());
        newsLangVO.setLanguage("en");
        newsLangVOS.add(newsLangVO);

        List<NewsLang> newsLangs = newsLangMapper.selectByNewsId(news.getId());
        List<NewsContentLang> newsContentLangs = newsContentLangMapper.selectByContentId(newsContent.getId());
        HashMap<String, NewsLangVO> map = new HashMap<>();
        for (NewsLang newsLang : newsLangs) {
            NewsLangVO nv = new NewsLangVO();
            BeanUtils.copyProperties(newsLang, nv);
            nv.setLanguage(newsLang.getLanguage());
            map.put(newsLang.getLanguage(), nv);
        }
        for (NewsContentLang newsContentLang : newsContentLangs) {
            NewsLangVO vo = map.get(newsContentLang.getLanguage());
            vo.setContent(newsContentLang.getContent());
        }
        newsLangVOS.addAll(map.values());
        return toObject(newsLangVOS);
    }

    /**
     * 查询新闻列表
     *
     * @param news 新闻
     * @return 新闻
     */
    @Override
    public List<News> selectNewsList(News news) {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增新闻
     *
     * @param newsVO 新闻
     * @return 结果
     */
    @Override
    public News insertNews(NewsVO newsVO) {
        if (ObjectUtil.isEmpty(newsVO)) {
            throw new ServiceException("参数不能为空");
        }
        //推荐位置不能大于6
        if (newsVO.getRecommend() == 1) {
            WebSiteCategory webSiteCategory = webSiteCategoryMapper.selectCategoryById(newsVO.getCategoryId());
            Integer count = newsMapper.selectRecommend(newsVO.getCategoryId());
            if (webSiteCategory.getName().equals("BAIC In X")) {
                if (count >= 2) {
                    throw new ServiceException("BAIC In X推荐位广告不能大于2条");
                }
            }else {
                if (count >= 6) {
                    throw new ServiceException("推荐位广告不能大于6条");
                }
            }

        }

        //1 overview 2 our vision限制只能有一篇状态为发布状态
        if (ObjectUtil.isNotNull(newsVO.getPageType()) && (1 == newsVO.getPageType() || 2 == newsVO.getPageType())) {
            List<Long> list = newsMapper.selectByPageType(newsVO.getPageType());
            if (list.size() >= 1 && !list.contains(newsVO.getId())) {
                throw new ServiceException("发布状态的新闻只能有一条");
            }
        }

        List<NewsLangVO> news = toList(newsVO);

        List<NewsLang> newsLangs = new ArrayList<>();
        List<NewsContentLang> newsContentLangs = new ArrayList<>();
        News n = new News();
        NewsContent newsContent = new NewsContent();
        for (NewsLangVO newsLangVO : news) {
            newsLangVO.setCreateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(newsLangVO.getLanguage())) {
                BeanUtils.copyProperties(newsLangVO, n);
                BeanUtils.copyProperties(newsLangVO, newsContent);
            } else {
                NewsLang newsLang = new NewsLang();
                BeanUtils.copyProperties(newsLangVO, newsLang);
                newsLangs.add(newsLang);

                NewsContentLang contentLang = new NewsContentLang();
                BeanUtils.copyProperties(newsLangVO, contentLang);
                newsContentLangs.add(contentLang);
            }
        }

        int i = newsMapper.insertNews(n);
        if (i == 0){
            throw new ServiceException("操作失败");
        }
        newsContent.setNewsId(n.getId());
        newsContentMapper.insertNewsContent(newsContent);

        if (CollUtil.isNotEmpty(newsLangs)) {
            for (NewsLang newsLang : newsLangs) {
                newsLang.setNewsId(n.getId());
            }
            newsLangMapper.batchInsert(newsLangs);

        }

        if (CollUtil.isNotEmpty(newsContentLangs)) {
            for (NewsContentLang newsContentLang : newsContentLangs) {
                newsContentLang.setNewsContentId(newsContent.getId());
            }
            newsContentLangMapper.batchInsert(newsContentLangs);
        }

        //发送新闻到订阅者邮箱
        try {
            //只有已发布的新闻才发送邮件
            if (newsVO.getCategoryId() == 31 && newsVO.getStatus() == 1) {
                newsVO.setId(n.getId());
                subscribeService.sendNewsEmailToAvailableSubscribers(newsVO);
            }
        } catch (Exception e) {
            log.error("发送订阅新闻失败：{}", e.getMessage());
        }
        return n;
    }

    /**
     * 修改新闻
     *
     * @param newsVO 新闻
     * @return 结果
     */
    @Override
    public int updateNews(NewsVO newsVO) {
        if (ObjectUtil.isEmpty(newsVO)) {
            throw new ServiceException("参数不能为空");
        }

        //推荐位置不能大于6
        if (newsVO.getRecommend() == 1) {
            WebSiteCategory webSiteCategory = webSiteCategoryMapper.selectCategoryById(newsVO.getCategoryId());
            Integer count = newsMapper.selectRecommend(newsVO.getCategoryId());
            News newsDb = newsMapper.selectNewsById(newsVO.getId());
            if (webSiteCategory.getName().equals("BAIC In X")){
                if (newsDb.getRecommend() == 1) {
                    if (count > 2) {
                        throw new ServiceException("BAIC In X推荐位广告不能大于2条");
                    }
                }else {
                    if (count >= 2) {
                        throw new ServiceException("BAIC In X推荐位广告不能大于2条");
                    }
                }
            }else {
                if (newsDb.getRecommend() == 1) {
                    if (count > 6) {
                        throw new ServiceException("推荐位广告不能大于2条");
                    }
                }else {
                    if (count >= 6) {
                        throw new ServiceException("推荐位广告不能大于2条");
                    }
                }
            }
        }

        //1 overview 2 our vision限制只能有一篇状态为发布状态
        if (ObjectUtil.isNotNull(newsVO.getPageType()) && (1 == newsVO.getPageType() || 2 == newsVO.getPageType())) {
            List<Long> list = newsMapper.selectByPageType(newsVO.getPageType());
            if (list.size() >= 1 && !list.contains(newsVO.getId())) {
                throw new ServiceException("发布状态的新闻只能有一条");
            }
        }

        List<NewsLangVO> news = toList(newsVO);

        List<NewsLang> newsLangs = new ArrayList<>();
        List<NewsContentLang> newsContentLangs = new ArrayList<>();
        int i = 0;
        News ns = new News();
        NewsContent newsContent = new NewsContent();
        for (NewsLangVO newsLangVO : news) {
            newsLangVO.setUpdateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(newsLangVO.getLanguage())) {
                BeanUtils.copyProperties(newsLangVO, ns);

                BeanUtils.copyProperties(newsLangVO, newsContent);
                newsContent.setNewsId(newsLangVO.getId());
            } else {
                NewsLang newsLang = new NewsLang();
                BeanUtils.copyProperties(newsLangVO, newsLang);
                newsLangs.add(newsLang);

                NewsContentLang newsContentLang = new NewsContentLang();
                BeanUtils.copyProperties(newsLangVO, newsContentLang);
//                newsContentLang.setContent(newsLangVO.getContent());
                newsContentLangs.add(newsContentLang);
            }
        }
        i = newsMapper.updateNews(ns);
        NewsContent content = newsContentMapper.selectByNewsId(ns.getId());
        newsContent.setId(content.getId());
        newsContentMapper.updateNewsContent(newsContent);
        for (NewsLang newsLang : newsLangs) {
            newsLangMapper.updateNewsLang(newsLang);
        }

        for (NewsContentLang newsContentLang : newsContentLangs) {
            newsContentLang.setNewsContentId(content.getId());
            newsContentLangMapper.updateByNewsContentId(newsContentLang);
        }
//        newsContentLangMapper.batchUpdate(newsContentLangs);

        //发送新闻到订阅者邮箱
        try {
            //只有已发布的新闻才发送邮件
            if (newsVO.getCategoryId() == 31 && newsVO.getStatus() == 1) {
                subscribeService.sendNewsEmailToAvailableSubscribers(newsVO);
            }
        } catch (Exception e) {
            log.error("发送订阅新闻失败：{}", e.getMessage());
        }
        return i;
    }

    /**
     * 批量删除新闻
     *
     * @param ids 需要删除的新闻主键
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(List<Long> ids) {
        List<NewsContent> newsContents = newsContentMapper.selectByNewsIds(ids);
        List<Long> contentIds = newsContents.stream().map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        int i = newsMapper.deleteNewsByIds(ids);
        newsContentMapper.deleteByNewsIds(ids);
        newsLangMapper.deleteByNewsIds(ids);
        newsContentLangMapper.deleteByNewsContentIds(contentIds);
        return i;
    }

    /**
     * 删除新闻信息
     *
     * @param id 新闻主键
     * @return 结果
     */
    @Override
    public int deleteNewsById(Long id) {
        return newsMapper.deleteNewsById(id);
    }

    @Override
    public List<NewsSearchResultVO> search(String l, String keyword) {
        List<NewsSearchResultVO> newsList = null;
        if ("en".equalsIgnoreCase(l)) {
            newsList = newsMapper.searchNewsList(keyword, null, null);
        } else {
            newsList = newsLangMapper.searchNewsList(l, keyword, null, null);
        }


//        newsList.forEach(item -> {
//            // 给标题、简介、分类 设置高亮
//            item.setTitle(getHitCode(item.getTitle(), keyword));
//            item.setDescription(getHitCode(item.getDescription(), keyword));
//            item.setCategoryName(getHitCode(item.getCategoryName(), keyword));
//        });

        return newsList;
    }

    @Override
    public List<NewsSearchResultVO> getNewsList(String l, Long categoryId, Integer recommend) {
        List<NewsSearchResultVO> newsList = null;
        if ("en".equalsIgnoreCase(l)) {
            newsList = newsMapper.searchNewsList(null, categoryId, recommend);
        } else {
            newsList = newsLangMapper.searchNewsList(l, null, categoryId, recommend);
        }
        return newsList;
    }

    @Override
    public NewsSearchResultVO getNewsDetail(String l, Long id) {
        NewsSearchResultVO resultVO = null;
        if ("en".equalsIgnoreCase(l)) {
            resultVO = newsMapper.selectNewsDetail(id);
        } else {
            resultVO = newsLangMapper.selectNewsDetail(l, id);
        }
        //前端查找不到回跳到首页
        if (ObjectUtil.isEmpty(resultVO)){
            throw new ServiceException("所查找文章不存在");
        }
        return resultVO;
    }

    @Override
    public NewsSearchResultVO getOODetail(String l, Long categoryId) {
        //根据导航名称获取导航ID
        NewsSearchResultVO resultVO = null;
        if ("en".equalsIgnoreCase(l)) {
//            WebSiteCategory webSiteCategory = new WebSiteCategory();
//            webSiteCategory.setPath(categoryPath);
//            List<WebSiteCategory> categories = webSiteCategoryMapper.selectCategoryList(webSiteCategory);
//            if (CollUtil.isNotEmpty(categories)){
//
//            }
            resultVO = newsMapper.selectNewsDetailByCategoryId(categoryId);
        } else {
//            CategoryLang categoryLang = new CategoryLang();
//            categoryLang.setPath(categoryPath);
//            categoryLang.setLanguage(l);
//            List<CategoryLang> categoryLangs = categoryLangMapper.selectCategoryLangList(categoryLang);
//            if (CollUtil.isNotEmpty(categoryLangs)){
//            }
            resultVO = newsLangMapper.selectNewsDetailByCategoryId(l, categoryId);
        }
        return resultVO;
    }

    public List<NewsLangVO> toList(NewsVO newsVO) {
        List<NewsLangVO> langVOS = new ArrayList<>();
        List<NLangVO> newsLangs = newsVO.getNewsLangs();
        for (NLangVO newsLang : newsLangs) {
            NewsLangVO newsLangVO = new NewsLangVO();
            BeanUtils.copyProperties(newsLang, newsLangVO);
            newsLangVO.setCategoryId(newsVO.getCategoryId());
            newsLangVO.setSort(newsVO.getSort());
            newsLangVO.setStatus(newsVO.getStatus());
            newsLangVO.setRecommend(newsVO.getRecommend());
            newsLangVO.setPublishTime(newsVO.getPublishTime());
            if ("en".equalsIgnoreCase(newsLang.getLanguage())) {
                newsLangVO.setId(newsVO.getId());
                newsLangVO.setPageType(newsVO.getPageType());
            }
            langVOS.add(newsLangVO);
        }
        return langVOS;
    }

    public NewsVO toObject(List<NewsLangVO> newsLangVOS) {
        NewsVO newsVO = new NewsVO();
        ArrayList<NLangVO> nLangVOS = new ArrayList<>();
        for (NewsLangVO newsLangVO : newsLangVOS) {
            NLangVO nLangVO = new NLangVO();
            BeanUtils.copyProperties(newsLangVO, nLangVO);
            if ("en".equalsIgnoreCase(newsLangVO.getLanguage())) {
                newsVO.setId(newsLangVO.getId());
                newsVO.setCategoryId(newsLangVO.getCategoryId());
                newsVO.setSort(newsLangVO.getSort());
                newsVO.setStatus(newsLangVO.getStatus());
                newsVO.setRecommend(newsLangVO.getRecommend());
                newsVO.setPageType(newsLangVO.getPageType());
                newsVO.setPublishTime(newsLangVO.getPublishTime());
            }
            nLangVOS.add(nLangVO);
        }
        newsVO.setNewsLangs(nLangVOS);
        return newsVO;
    }
}
