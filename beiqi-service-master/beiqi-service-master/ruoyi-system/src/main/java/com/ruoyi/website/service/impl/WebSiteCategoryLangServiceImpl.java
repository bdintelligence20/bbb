package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.CategoryLang;
import com.ruoyi.website.mapper.CategoryLangMapper;
import com.ruoyi.website.service.ICategoryLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 导航多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class WebSiteCategoryLangServiceImpl implements ICategoryLangService
{
    @Autowired
    private CategoryLangMapper categoryLangMapper;

    /**
     * 查询导航多语言
     * 
     * @param id 导航多语言主键
     * @return 导航多语言
     */
    @Override
    public CategoryLang selectCategoryLangById(Long id)
    {
        return categoryLangMapper.selectCategoryLangById(id);
    }

    /**
     * 查询导航多语言列表
     * 
     * @param categoryLang 导航多语言
     * @return 导航多语言
     */
    @Override
    public List<CategoryLang> selectCategoryLangList(CategoryLang categoryLang)
    {
        return categoryLangMapper.selectCategoryLangList(categoryLang);
    }

    /**
     * 新增导航多语言
     * 
     * @param categoryLang 导航多语言
     * @return 结果
     */
    @Override
    public int insertCategoryLang(CategoryLang categoryLang)
    {
        categoryLang.setCreateTime(DateUtils.getNowDate());
        return categoryLangMapper.insertCategoryLang(categoryLang);
    }

    /**
     * 修改导航多语言
     * 
     * @param categoryLang 导航多语言
     * @return 结果
     */
    @Override
    public int updateCategoryLang(CategoryLang categoryLang)
    {
        categoryLang.setUpdateTime(DateUtils.getNowDate());
        return categoryLangMapper.updateCategoryLang(categoryLang);
    }

    /**
     * 批量删除导航多语言
     * 
     * @param ids 需要删除的导航多语言主键
     * @return 结果
     */
    @Override
    public int deleteCategoryLangByIds(Long[] ids)
    {
        return categoryLangMapper.deleteCategoryLangByIds(ids);
    }

    /**
     * 删除导航多语言信息
     * 
     * @param id 导航多语言主键
     * @return 结果
     */
    @Override
    public int deleteCategoryLangById(Long id)
    {
        return categoryLangMapper.deleteCategoryLangById(id);
    }
}
