package com.ruoyi.website.service;

import com.ruoyi.website.domain.CategoryLang;
import com.ruoyi.website.domain.WebSiteCategory;
import com.ruoyi.website.domain.vo.CategoryVO;
import com.ruoyi.website.domain.vo.WebSiteCategoryPO;

import java.util.List;

/**
 * 导航Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface IWebSiteCategoryService
{
    /**
     * 查询导航
     * 
     * @param id 导航主键
     * @return 导航
     */
    public CategoryVO selectCategoryById(Long id);

    /**
     * 查询导航列表
     * 
     * @param webSiteCategory 导航
     * @return 导航集合
     */
    public List<WebSiteCategory> selectCategoryList(WebSiteCategory webSiteCategory);

    /**
     * 新增导航
     * 
     * @param categoryVO 导航
     * @return 结果
     */
    public int insertCategory(CategoryVO categoryVO);

    /**
     * 修改导航
     * 
     * @param categoryVO 导航
     * @return 结果
     */
    public int updateCategory(CategoryVO categoryVO);

    /**
     * 批量删除导航
     * 
     * @param ids 需要删除的导航主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(List<Long> ids);

    /**
     * 删除导航信息
     * 
     * @param id 导航主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    List<WebSiteCategoryPO> buildCategoryTree(List<WebSiteCategory> categories);

    List<WebSiteCategory> getIndexCategoryList(String lang, Integer topOrBottom);
}
