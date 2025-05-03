package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.AfterSaleCountryLangMapper;
import com.ruoyi.website.domain.AfterSaleCountryLang;
import com.ruoyi.website.domain.vo.AfterSaleCountryLangVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AfterSaleCountryMapper;
import com.ruoyi.website.domain.AfterSaleCountry;
import com.ruoyi.system.service.IAfterSaleCountryService;

/**
 * 问卷星国家配置Service业务层处理
 * @date 2022-07-05
 */
@Service
public class AfterSaleCountryServiceImpl implements IAfterSaleCountryService {
    @Autowired
    private AfterSaleCountryMapper afterSaleCountryMapper;

    @Autowired
    private AfterSaleCountryLangMapper afterSaleCountryLangMapper;


    @Override
    public List<AfterSaleCountryLangVO> selectAfterSaleCountryById(Long id) {
        return afterSaleCountryMapper.selectAfterSaleCountryById(id);
    }


    @Override
    public List<AfterSaleCountryLangVO> selectAfterSaleCountryList(String name) {
        return afterSaleCountryMapper.selectAfterSaleCountryList(name);
    }


    @Override
    public int insertAfterSaleCountry(List<AfterSaleCountryLangVO> langVOS) {
        if (CollUtil.isEmpty(langVOS)){
            throw new ServiceException("参数不能为空");
        }
        AfterSaleCountry afterSaleCountry = new AfterSaleCountry();
        AfterSaleCountryLangVO afterSaleCountryLangVO = langVOS.get(0);
        BeanUtils.copyProperties(afterSaleCountryLangVO, afterSaleCountry);
        afterSaleCountry.setCreateTime(DateUtils.getNowDate());
        int i = afterSaleCountryMapper.insertAfterSaleCountry(afterSaleCountry);

        List<AfterSaleCountryLang> list = langVOS.stream().map(item -> {
            AfterSaleCountryLang afterSaleCountryLang = new AfterSaleCountryLang();
            BeanUtils.copyProperties(item, afterSaleCountryLang);
            afterSaleCountryLang.setCountryId(afterSaleCountry.getId());
            afterSaleCountryLang.setCreateTime(DateUtils.getNowDate());
            return afterSaleCountryLang;
        }).collect(Collectors.toList());
        afterSaleCountryLangMapper.batchInsert(list);
        return i;
    }


    @Override
    public int updateAfterSaleCountry(List<AfterSaleCountryLangVO> langVOS) {
        if (CollUtil.isEmpty(langVOS)){
            throw new ServiceException("参数不能为空");
        }
        //删除后在添加
        afterSaleCountryMapper.deleteAfterSaleCountryById(langVOS.get(0).getId());
        afterSaleCountryLangMapper.deleteAfterSaleCountryLangByIds(langVOS.stream().map(item -> item.getCountryLangId()).collect(Collectors.toList()));
        return insertAfterSaleCountry(langVOS);
    }


    @Override
    public int deleteAfterSaleCountryByIds(List<Long> ids) {
        afterSaleCountryLangMapper.deleteByCountryIds(ids);
        return afterSaleCountryMapper.deleteAfterSaleCountryByIds(ids);
    }

}
