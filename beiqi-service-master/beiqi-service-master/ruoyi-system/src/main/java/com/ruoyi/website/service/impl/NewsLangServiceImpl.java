package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.NewsLang;
import com.ruoyi.website.mapper.NewsLangMapper;
import com.ruoyi.website.service.INewsLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 新闻多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class NewsLangServiceImpl implements INewsLangService
{
    @Autowired
    private NewsLangMapper newsLangMapper;

    /**
     * 查询新闻多语言
     * 
     * @param id 新闻多语言主键
     * @return 新闻多语言
     */
    @Override
    public NewsLang selectNewsLangById(Long id)
    {
        return newsLangMapper.selectNewsLangById(id);
    }

    /**
     * 查询新闻多语言列表
     * 
     * @param newsLang 新闻多语言
     * @return 新闻多语言
     */
    @Override
    public List<NewsLang> selectNewsLangList(NewsLang newsLang)
    {
        return newsLangMapper.selectNewsLangList(newsLang);
    }

    /**
     * 新增新闻多语言
     * 
     * @param newsLang 新闻多语言
     * @return 结果
     */
    @Override
    public int insertNewsLang(NewsLang newsLang)
    {
        newsLang.setCreateTime(DateUtils.getNowDate());
        return newsLangMapper.insertNewsLang(newsLang);
    }

    /**
     * 修改新闻多语言
     * 
     * @param newsLang 新闻多语言
     * @return 结果
     */
    @Override
    public int updateNewsLang(NewsLang newsLang)
    {
        newsLang.setUpdateTime(DateUtils.getNowDate());
        return newsLangMapper.updateNewsLang(newsLang);
    }

    /**
     * 批量删除新闻多语言
     * 
     * @param ids 需要删除的新闻多语言主键
     * @return 结果
     */
    @Override
    public int deleteNewsLangByIds(Long[] ids)
    {
        return newsLangMapper.deleteNewsLangByIds(ids);
    }

    /**
     * 删除新闻多语言信息
     * 
     * @param id 新闻多语言主键
     * @return 结果
     */
    @Override
    public int deleteNewsLangById(Long id)
    {
        return newsLangMapper.deleteNewsLangById(id);
    }
}
