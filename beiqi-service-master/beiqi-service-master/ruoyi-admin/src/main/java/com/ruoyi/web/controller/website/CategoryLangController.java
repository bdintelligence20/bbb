package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.CategoryLang;
import com.ruoyi.website.service.ICategoryLangService;
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
 * 导航多语言Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/system/lang")
public class CategoryLangController extends BaseController
{
    @Autowired
    private ICategoryLangService categoryLangService;

    /**
     * 查询导航多语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:lang:list')")
    @GetMapping("/list")
    public TableDataInfo list(CategoryLang categoryLang)
    {
        startPage();
        List<CategoryLang> list = categoryLangService.selectCategoryLangList(categoryLang);
        return getDataTable(list);
    }

    /**
     * 导出导航多语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:lang:export')")
    @Log(title = "导航多语言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CategoryLang categoryLang)
    {
        List<CategoryLang> list = categoryLangService.selectCategoryLangList(categoryLang);
        ExcelUtil<CategoryLang> util = new ExcelUtil<CategoryLang>(CategoryLang.class);
        util.exportExcel(response, list, "导航多语言数据");
    }

    /**
     * 获取导航多语言详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:lang:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(categoryLangService.selectCategoryLangById(id));
    }

    /**
     * 新增导航多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:add')")
    @Log(title = "导航多语言", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CategoryLang categoryLang)
    {
        return toAjax(categoryLangService.insertCategoryLang(categoryLang));
    }

    /**
     * 修改导航多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:edit')")
    @Log(title = "导航多语言", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CategoryLang categoryLang)
    {
        return toAjax(categoryLangService.updateCategoryLang(categoryLang));
    }

    /**
     * 删除导航多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:remove')")
    @Log(title = "导航多语言", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(categoryLangService.deleteCategoryLangByIds(ids));
    }
}
