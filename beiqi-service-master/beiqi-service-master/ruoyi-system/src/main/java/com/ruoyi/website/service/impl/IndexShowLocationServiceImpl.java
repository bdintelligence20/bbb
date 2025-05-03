package com.ruoyi.website.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.website.domain.IndexShowLocation;
import com.ruoyi.website.domain.IndexShowLocationLang;
import com.ruoyi.website.domain.vo.IndexShowLocationVO;
import com.ruoyi.website.mapper.IndexShowLocationLangMapper;
import com.ruoyi.website.mapper.IndexShowLocationMapper;
import com.ruoyi.website.service.IIndexShowLocationService;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.lang.model.element.VariableElement;

/**
 * 首页配置Service业务层处理
 * 
 * @author thh
 * @date 2022-06-21
 */
@Service
public class IndexShowLocationServiceImpl implements IIndexShowLocationService
{
    @Autowired
    private IndexShowLocationMapper indexShowLocationMapper;

    @Autowired
    private IndexShowLocationLangMapper indexShowLocationLangMapper;

    static final Map<Integer, String> map = new HashMap<>();

    //0: 视频 1: 社会责任 2: 企业文化 3: 多媒体
    static {
        map.put(0,"视频位");
        map.put(1,"社会责任位");
        map.put(2,"企业文化位");
        map.put(3,"多媒体位");
    }

    /**
     * 查询首页配置
     * 
     * @param id 首页配置主键
     * @return 首页配置
     */
    @Override
    public List<IndexShowLocationVO> selectIndexShowLocationById(Long id) {
        return indexShowLocationMapper.selectIndexShowLocationById(id);
    }


    @Override
    public List<IndexShowLocationVO> selectIndexShowLocationList() {
        return indexShowLocationMapper.selectIndexShowLocationList("en");
    }

    /**
     * 新增首页配置
     * @return 结果
     */
    @Transactional
    @Override
    public int insertIndexShowLocation(List<IndexShowLocationVO> locationVOS) {
        Assert.notEmpty(locationVOS,"参数不能为空");
        Integer type = locationVOS.get(0).getType();
        IndexShowLocationLang lang = new IndexShowLocationLang();
        lang.setType(type);
        List<IndexShowLocationLang> langs = indexShowLocationLangMapper.selectIndexShowLocationLangList(lang);
        if (CollUtil.isNotEmpty(langs)){
            throw new ServiceException(map.get(type) + "已经存在请勿重复添加");
        }
        IndexShowLocation indexShowLocation = new IndexShowLocation();
        indexShowLocation.setCreateTime(DateUtils.getNowDate());
        int i = indexShowLocationMapper.insertIndexShowLocation(indexShowLocation);

        List<IndexShowLocationLang> list = locationVOS.stream().map(item ->{
            IndexShowLocationLang indexShowLocationLang = new IndexShowLocationLang();
            BeanUtils.copyProperties(item, indexShowLocationLang);
            indexShowLocationLang.setCreateTime(DateUtils.getNowDate());
            indexShowLocationLang.setIndexId(indexShowLocation.getId());
            return indexShowLocationLang;
        }).collect(Collectors.toList());
        indexShowLocationLangMapper.batchInsert(list);
        return i;
    }

    /**
     * 修改首页配置
     * @return 结果
     */
    @Transactional
    @Override
    public int updateIndexShowLocation(List<IndexShowLocationVO> locationVOS) {
        Assert.notEmpty(locationVOS,"参数不能为空");
        indexShowLocationMapper.deleteIndexShowLocationById(locationVOS.get(0).getId());
        List<Long> ids = locationVOS.stream().map(item -> item.getIndexLangId()).collect(Collectors.toList());
        indexShowLocationLangMapper.deleteIndexShowLocationLangByIds(ids);
        return insertIndexShowLocation(locationVOS);
    }

    /**
     * 批量删除首页配置
     * 
     * @param ids 需要删除的首页配置主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteIndexShowLocationByIds(List<Long> ids)
    {
        return indexShowLocationMapper.deleteIndexShowLocationByIds(ids);
    }

    /**
     * 删除首页配置信息
     * 
     * @param id 首页配置主键
     * @return 结果
     */
    @Override
    public int deleteIndexShowLocationById(Long id)
    {
        return indexShowLocationMapper.deleteIndexShowLocationById(id);
    }

    @Override
    public Map<Integer, String> selectIndexShowLocationType() {
        return map;
    }

    @Override
    public List<IndexShowLocationVO> getIndexShowLocationList(String lang) {
        return indexShowLocationMapper.selectIndexShowLocationList(lang);
    }
}
