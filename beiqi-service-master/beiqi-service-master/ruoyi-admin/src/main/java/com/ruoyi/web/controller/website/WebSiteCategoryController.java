package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.website.domain.CategoryLang;
import com.ruoyi.website.domain.WebSiteCategory;
import com.ruoyi.website.domain.vo.CategoryVO;
import com.ruoyi.website.service.IWebSiteCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 导航Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@Api(tags = "导航管理")
@RestController
@RequestMapping("/system/category")
public class WebSiteCategoryController extends BaseController
{
    @Autowired
    private IWebSiteCategoryService categoryService;

    /**
     * 查询导航列表
     */
    @ApiOperation("查询导航列表")
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public AjaxResult list(WebSiteCategory webSiteCategory) {
        List<WebSiteCategory> list = categoryService.selectCategoryList(webSiteCategory);
        return AjaxResult.success(list);
    }


    /**
     * 获取菜单下拉树列表
     */
    @ApiOperation("获取菜单下拉树列表")
    @GetMapping("/tree")
    public AjaxResult treeselect(WebSiteCategory webSiteCategory) {
        List<WebSiteCategory> list = categoryService.selectCategoryList(webSiteCategory);
        return AjaxResult.success(categoryService.buildCategoryTree(list));
    }

    /**
     * 获取导航详细信息
     */
    @ApiOperation("获取导航详细信息")
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(categoryService.selectCategoryById(id));
    }


    @ApiOperation("添加导航")
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "导航", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryVO categoryVO) {
        return toAjax(categoryService.insertCategory(categoryVO));
    }

    /**
     * 修改导航
     */
    @ApiOperation("修改导航")
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "导航", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryVO categoryVO) {
        return toAjax(categoryService.updateCategory(categoryVO));
    }

    /**
     * 删除导航
     */
    @ApiOperation("删除导航")
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "导航", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }}
