package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.CategoryLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 导航多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface CategoryLangMapper 
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
     * 删除导航多语言
     * 
     * @param id 导航多语言主键
     * @return 结果
     */
    public int deleteCategoryLangById(Long id);

    /**
     * 批量删除导航多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryLangByIds(Long[] ids);

    void batchInsert(@Param("list") List<CategoryLang> categorys);

    void deleteByCategoryIds(@Param("list") List<Long> list);
}
