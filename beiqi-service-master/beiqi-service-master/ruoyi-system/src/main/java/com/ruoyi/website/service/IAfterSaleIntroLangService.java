package com.ruoyi.website.service;

import com.ruoyi.website.domain.AfterSaleIntroLang;

import java.util.List;

/**
 * 售后简介列多语言Service接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface IAfterSaleIntroLangService 
{
    /**
     * 查询售后简介列多语言
     * 
     * @param id 售后简介列多语言主键
     * @return 售后简介列多语言
     */
    public AfterSaleIntroLang selectAfterSaleIntroLangById(Long id);

    /**
     * 查询售后简介列多语言列表
     * 
     * @param afterSaleIntroLang 售后简介列多语言
     * @return 售后简介列多语言集合
     */
    public List<AfterSaleIntroLang> selectAfterSaleIntroLangList(AfterSaleIntroLang afterSaleIntroLang);

    /**
     * 新增售后简介列多语言
     * 
     * @param afterSaleIntroLang 售后简介列多语言
     * @return 结果
     */
    public int insertAfterSaleIntroLang(AfterSaleIntroLang afterSaleIntroLang);

    /**
     * 修改售后简介列多语言
     * 
     * @param afterSaleIntroLang 售后简介列多语言
     * @return 结果
     */
    public int updateAfterSaleIntroLang(AfterSaleIntroLang afterSaleIntroLang);

    /**
     * 批量删除售后简介列多语言
     * 
     * @param ids 需要删除的售后简介列多语言主键集合
     * @return 结果
     */
    public int deleteAfterSaleIntroLangByIds(Long[] ids);

    /**
     * 删除售后简介列多语言信息
     * 
     * @param id 售后简介列多语言主键
     * @return 结果
     */
    public int deleteAfterSaleIntroLangById(Long id);
}
