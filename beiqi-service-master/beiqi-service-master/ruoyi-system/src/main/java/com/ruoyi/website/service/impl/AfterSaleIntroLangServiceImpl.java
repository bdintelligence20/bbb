package com.ruoyi.website.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.AfterSaleIntroLang;
import com.ruoyi.website.mapper.AfterSaleIntroLangMapper;
import com.ruoyi.website.service.IAfterSaleIntroLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 售后简介列多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-10
 */
@Service
public class AfterSaleIntroLangServiceImpl implements IAfterSaleIntroLangService
{
    @Autowired
    private AfterSaleIntroLangMapper afterSaleIntroLangMapper;

    /**
     * 查询售后简介列多语言
     * 
     * @param id 售后简介列多语言主键
     * @return 售后简介列多语言
     */
    @Override
    public AfterSaleIntroLang selectAfterSaleIntroLangById(Long id)
    {
        return afterSaleIntroLangMapper.selectAfterSaleIntroLangById(id);
    }

    /**
     * 查询售后简介列多语言列表
     * 
     * @param afterSaleIntroLang 售后简介列多语言
     * @return 售后简介列多语言
     */
    @Override
    public List<AfterSaleIntroLang> selectAfterSaleIntroLangList(AfterSaleIntroLang afterSaleIntroLang)
    {
        return afterSaleIntroLangMapper.selectAfterSaleIntroLangList(afterSaleIntroLang);
    }

    /**
     * 新增售后简介列多语言
     * 
     * @param afterSaleIntroLang 售后简介列多语言
     * @return 结果
     */
    @Override
    public int insertAfterSaleIntroLang(AfterSaleIntroLang afterSaleIntroLang)
    {
        afterSaleIntroLang.setCreateTime(DateUtils.getNowDate());
        return afterSaleIntroLangMapper.insertAfterSaleIntroLang(afterSaleIntroLang);
    }

    /**
     * 修改售后简介列多语言
     * 
     * @param afterSaleIntroLang 售后简介列多语言
     * @return 结果
     */
    @Override
    public int updateAfterSaleIntroLang(AfterSaleIntroLang afterSaleIntroLang)
    {
        afterSaleIntroLang.setUpdateTime(DateUtils.getNowDate());
        return afterSaleIntroLangMapper.updateAfterSaleIntroLang(afterSaleIntroLang);
    }

    /**
     * 批量删除售后简介列多语言
     * 
     * @param ids 需要删除的售后简介列多语言主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleIntroLangByIds(Long[] ids)
    {
        return afterSaleIntroLangMapper.deleteAfterSaleIntroLangByIds(Arrays.asList(ids));
    }

    /**
     * 删除售后简介列多语言信息
     * 
     * @param id 售后简介列多语言主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleIntroLangById(Long id)
    {
        return afterSaleIntroLangMapper.deleteAfterSaleIntroLangById(id);
    }
}
