package com.ruoyi.website.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.NewsIndexShowLocation;
import com.ruoyi.website.domain.NewsIndexShowLocationLang;
import com.ruoyi.website.domain.vo.NewsIndexShowLocationVO;
import com.ruoyi.website.mapper.NewsIndexShowLocationLangMapper;
import com.ruoyi.website.mapper.NewsIndexShowLocationMapper;
import com.ruoyi.website.service.INewsIndexShowLocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 首页新闻配置Service业务层处理
 * 
 * @author thh
 * @date 2022-06-21
 */
@Service
public class NewsIndexShowLocationServiceImpl implements INewsIndexShowLocationService
{
    @Autowired
    private NewsIndexShowLocationMapper indexShowLocationMapper;

    @Autowired
    private NewsIndexShowLocationLangMapper indexShowLocationLangMapper;


    /**
     * 查询首页新闻配置
     * 
     * @param id 首页新闻配置主键
     * @return 首页新闻配置
     */
    @Override
    public List<NewsIndexShowLocationVO> selectIndexShowLocationById(Long id) {
        return indexShowLocationMapper.selectIndexShowLocationById(id);
    }


    @Override
    public List<NewsIndexShowLocationVO> selectIndexShowLocationList() {
        return indexShowLocationMapper.selectIndexShowLocationList("en");
    }

    /**
     * 新增首页新闻配置
     * @return 结果
     */
    @Transactional
    @Override
    public int insertIndexShowLocation(List<NewsIndexShowLocationVO> locationVOS) {
        Assert.notEmpty(locationVOS,"参数不能为空");
        NewsIndexShowLocation indexShowLocation = new NewsIndexShowLocation();
        for (NewsIndexShowLocationVO newsIndexShowLocationVO:locationVOS){
            indexShowLocation.setSort(newsIndexShowLocationVO.getSort());
            indexShowLocation.setEnable(newsIndexShowLocationVO.getEnable());
        }
        indexShowLocation.setCreateTime(DateUtils.getNowDate());
        int i = indexShowLocationMapper.insertIndexShowLocation(indexShowLocation);

        List<NewsIndexShowLocationLang> list = locationVOS.stream().map(item ->{
            NewsIndexShowLocationLang indexShowLocationLang = new NewsIndexShowLocationLang();
            BeanUtils.copyProperties(item, indexShowLocationLang);
            indexShowLocationLang.setCreateTime(DateUtils.getNowDate());
            indexShowLocationLang.setIndexId(indexShowLocation.getId());
            return indexShowLocationLang;
        }).collect(Collectors.toList());
        indexShowLocationLangMapper.batchInsert(list);
        return i;
    }

    /**
     * 修改首页新闻配置
     * @return 结果
     */
    @Transactional
    @Override
    public int updateIndexShowLocation(List<NewsIndexShowLocationVO> locationVOS) {
        Assert.notEmpty(locationVOS,"参数不能为空");
        indexShowLocationMapper.deleteIndexShowLocationById(locationVOS.get(0).getId());
        List<Long> ids = locationVOS.stream().map(item -> item.getIndexLangId()).collect(Collectors.toList());
        indexShowLocationLangMapper.deleteNewsIndexShowLocationLangByIds(ids);
        return insertIndexShowLocation(locationVOS);
    }

    /**
     * 批量删除首页新闻配置
     * 
     * @param ids 需要删除的首页新闻配置主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteIndexShowLocationByIds(List<Long> ids)
    {
        return indexShowLocationMapper.deleteIndexShowLocationByIds(ids);
    }

    /**
     * 删除首页新闻配置信息
     * 
     * @param id 首页新闻配置主键
     * @return 结果
     */
    @Override
    public int deleteIndexShowLocationById(Long id)
    {
        return indexShowLocationMapper.deleteIndexShowLocationById(id);
    }

    @Override
    public List<NewsIndexShowLocationVO> getIndexShowLocationList(String lang) {
        return indexShowLocationMapper.selectIndexShowLocationVoList(lang);
    }


}
