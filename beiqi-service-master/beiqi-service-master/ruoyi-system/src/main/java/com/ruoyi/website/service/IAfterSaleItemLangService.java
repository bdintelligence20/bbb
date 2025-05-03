package com.ruoyi.website.service;

import com.ruoyi.website.domain.AfterSaleItemLang;

import java.util.List;

/**
 * 售后服务项多语言Service接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface IAfterSaleItemLangService 
{
    /**
     * 查询售后服务项多语言
     * 
     * @param id 售后服务项多语言主键
     * @return 售后服务项多语言
     */
    public AfterSaleItemLang selectAfterSaleItemLangById(Long id);

    /**
     * 查询售后服务项多语言列表
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 售后服务项多语言集合
     */
    public List<AfterSaleItemLang> selectAfterSaleItemLangList(AfterSaleItemLang afterSaleItemLang);

    /**
     * 新增售后服务项多语言
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 结果
     */
    public int insertAfterSaleItemLang(AfterSaleItemLang afterSaleItemLang);

    /**
     * 修改售后服务项多语言
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 结果
     */
    public int updateAfterSaleItemLang(AfterSaleItemLang afterSaleItemLang);

    /**
     * 批量删除售后服务项多语言
     * 
     * @param ids 需要删除的售后服务项多语言主键集合
     * @return 结果
     */
    public int deleteAfterSaleItemLangByIds(Long[] ids);

    /**
     * 删除售后服务项多语言信息
     * 
     * @param id 售后服务项多语言主键
     * @return 结果
     */
    public int deleteAfterSaleItemLangById(Long id);
}
