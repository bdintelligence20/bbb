package com.ruoyi.website.service;

import com.ruoyi.website.domain.AfterSaleContentLang;

import java.util.List;

/**
 * 售后文章内容多语言Service接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface IAfterSaleContentLangService 
{
    /**
     * 查询售后文章内容多语言
     * 
     * @param id 售后文章内容多语言主键
     * @return 售后文章内容多语言
     */
    public AfterSaleContentLang selectAfterSaleContentLangById(Long id);

    /**
     * 查询售后文章内容多语言列表
     * 
     * @param afterSaleContentLang 售后文章内容多语言
     * @return 售后文章内容多语言集合
     */
    public List<AfterSaleContentLang> selectAfterSaleContentLangList(AfterSaleContentLang afterSaleContentLang);

    /**
     * 新增售后文章内容多语言
     * 
     * @param afterSaleContentLang 售后文章内容多语言
     * @return 结果
     */
    public int insertAfterSaleContentLang(AfterSaleContentLang afterSaleContentLang);

    /**
     * 修改售后文章内容多语言
     * 
     * @param afterSaleContentLang 售后文章内容多语言
     * @return 结果
     */
    public int updateAfterSaleContentLang(AfterSaleContentLang afterSaleContentLang);

    /**
     * 批量删除售后文章内容多语言
     * 
     * @param ids 需要删除的售后文章内容多语言主键集合
     * @return 结果
     */
    public int deleteAfterSaleContentLangByIds(Long[] ids);

    /**
     * 删除售后文章内容多语言信息
     * 
     * @param id 售后文章内容多语言主键
     * @return 结果
     */
    public int deleteAfterSaleContentLangById(Long id);
}
