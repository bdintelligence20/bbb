package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.WebSiteCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 导航Mapper接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface WebSiteCategoryMapper
{
    /**
     * 查询导航
     * 
     * @param id 导航主键
     * @return 导航
     */
    public WebSiteCategory selectCategoryById(Long id);

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
     * @param webSiteCategory 导航
     * @return 结果
     */
    public int insertCategory(WebSiteCategory webSiteCategory);

    /**
     * 修改导航
     * 
     * @param webSiteCategory 导航
     * @return 结果
     */
    public int updateCategory(WebSiteCategory webSiteCategory);

    /**
     * 删除导航
     * 
     * @param id 导航主键
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * 批量删除导航
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(List<Long> ids);

    List<WebSiteCategory> selectByParentId(@Param("parentId") Long parentId);

}
