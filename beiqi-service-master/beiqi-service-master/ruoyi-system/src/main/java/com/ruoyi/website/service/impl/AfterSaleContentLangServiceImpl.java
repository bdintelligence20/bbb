package com.ruoyi.website.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.AfterSaleContentLang;
import com.ruoyi.website.mapper.AfterSaleContentLangMapper;
import com.ruoyi.website.service.IAfterSaleContentLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 售后文章内容多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-10
 */
@Service
public class AfterSaleContentLangServiceImpl implements IAfterSaleContentLangService
{
    @Autowired
    private AfterSaleContentLangMapper afterSaleContentLangMapper;

    /**
     * 查询售后文章内容多语言
     * 
     * @param id 售后文章内容多语言主键
     * @return 售后文章内容多语言
     */
    @Override
    public AfterSaleContentLang selectAfterSaleContentLangById(Long id)
    {
        return afterSaleContentLangMapper.selectAfterSaleContentLangById(id);
    }

    /**
     * 查询售后文章内容多语言列表
     * 
     * @param afterSaleContentLang 售后文章内容多语言
     * @return 售后文章内容多语言
     */
    @Override
    public List<AfterSaleContentLang> selectAfterSaleContentLangList(AfterSaleContentLang afterSaleContentLang)
    {
        return afterSaleContentLangMapper.selectAfterSaleContentLangList(afterSaleContentLang);
    }

    /**
     * 新增售后文章内容多语言
     * 
     * @param afterSaleContentLang 售后文章内容多语言
     * @return 结果
     */
    @Override
    public int insertAfterSaleContentLang(AfterSaleContentLang afterSaleContentLang)
    {
        afterSaleContentLang.setCreateTime(DateUtils.getNowDate());
        return afterSaleContentLangMapper.insertAfterSaleContentLang(afterSaleContentLang);
    }

    /**
     * 修改售后文章内容多语言
     * 
     * @param afterSaleContentLang 售后文章内容多语言
     * @return 结果
     */
    @Override
    public int updateAfterSaleContentLang(AfterSaleContentLang afterSaleContentLang)
    {
        afterSaleContentLang.setUpdateTime(DateUtils.getNowDate());
        return afterSaleContentLangMapper.updateAfterSaleContentLang(afterSaleContentLang);
    }

    /**
     * 批量删除售后文章内容多语言
     * 
     * @param ids 需要删除的售后文章内容多语言主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleContentLangByIds(Long[] ids)
    {
        return afterSaleContentLangMapper.deleteAfterSaleContentLangByIds(Arrays.asList(ids));
    }

    /**
     * 删除售后文章内容多语言信息
     * 
     * @param id 售后文章内容多语言主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleContentLangById(Long id)
    {
        return afterSaleContentLangMapper.deleteAfterSaleContentLangById(id);
    }
}
