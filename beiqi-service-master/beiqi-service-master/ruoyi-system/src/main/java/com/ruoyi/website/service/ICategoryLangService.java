package com.ruoyi.website.service;

import com.ruoyi.website.domain.CategoryLang;

import java.util.List;

/**
 * 导航多语言Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface ICategoryLangService 
{
    /**
     * 查询导航多语言
     * 
     * @param id 导航多语言主键
     * @return 导航多语言
     */
    public CategoryLang selectCategoryLangById(Long id);

    /**
     * 查询导航多语言列表
     * 
     * @param categoryLang 导航多语言
     * @return 导航多语言集合
     */
    public List<CategoryLang> selectCategoryLangList(CategoryLang categoryLang);

    /**
     * 新增导航多语言
     * 
     * @param categoryLang 导航多语言
     * @return 结果
     */
    public int insertCategoryLang(CategoryLang categoryLang);

    /**
     * 修改导航多语言
     * 
     * @param categoryLang 导航多语言
     * @return 结果
     */
    public int updateCategoryLang(CategoryLang categoryLang);

    /**
     * 批量删除导航多语言
     * 
     * @param ids 需要删除的导航多语言主键集合
     * @return 结果
     */
    public int deleteCategoryLangByIds(Long[] ids);

    /**
     * 删除导航多语言信息
     * 
     * @param id 导航多语言主键
     * @return 结果
     */
    public int deleteCategoryLangById(Long id);
}
