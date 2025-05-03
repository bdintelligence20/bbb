package com.ruoyi.website.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.AfterSaleItemLang;
import com.ruoyi.website.mapper.AfterSaleItemLangMapper;
import com.ruoyi.website.service.IAfterSaleItemLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 售后服务项多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-10
 */
@Service
public class AfterSaleItemLangServiceImpl implements IAfterSaleItemLangService
{
    @Autowired
    private AfterSaleItemLangMapper afterSaleItemLangMapper;

    /**
     * 查询售后服务项多语言
     * 
     * @param id 售后服务项多语言主键
     * @return 售后服务项多语言
     */
    @Override
    public AfterSaleItemLang selectAfterSaleItemLangById(Long id)
    {
        return afterSaleItemLangMapper.selectAfterSaleItemLangById(id);
    }

    /**
     * 查询售后服务项多语言列表
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 售后服务项多语言
     */
    @Override
    public List<AfterSaleItemLang> selectAfterSaleItemLangList(AfterSaleItemLang afterSaleItemLang)
    {
        return afterSaleItemLangMapper.selectAfterSaleItemLangList(afterSaleItemLang);
    }

    /**
     * 新增售后服务项多语言
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 结果
     */
    @Override
    public int insertAfterSaleItemLang(AfterSaleItemLang afterSaleItemLang)
    {
        afterSaleItemLang.setCreateTime(DateUtils.getNowDate());
        return afterSaleItemLangMapper.insertAfterSaleItemLang(afterSaleItemLang);
    }

    /**
     * 修改售后服务项多语言
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 结果
     */
    @Override
    public int updateAfterSaleItemLang(AfterSaleItemLang afterSaleItemLang)
    {
        afterSaleItemLang.setUpdateTime(DateUtils.getNowDate());
        return afterSaleItemLangMapper.updateAfterSaleItemLang(afterSaleItemLang);
    }

    /**
     * 批量删除售后服务项多语言
     * 
     * @param ids 需要删除的售后服务项多语言主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleItemLangByIds(Long[] ids)
    {
        return afterSaleItemLangMapper.deleteAfterSaleItemLangByIds(Arrays.asList(ids));
    }

    /**
     * 删除售后服务项多语言信息
     * 
     * @param id 售后服务项多语言主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleItemLangById(Long id)
    {
        return afterSaleItemLangMapper.deleteAfterSaleItemLangById(id);
    }
}
