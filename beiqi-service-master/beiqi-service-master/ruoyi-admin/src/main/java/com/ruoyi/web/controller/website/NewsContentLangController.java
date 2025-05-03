package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.NewsContentLang;
import com.ruoyi.website.service.INewsContentLangService;
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
 * 新闻内容多语言Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/system/clang")
public class NewsContentLangController extends BaseController
{
    @Autowired
    private INewsContentLangService newsContentLangService;

    /**
     * 查询新闻内容多语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:lang:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsContentLang newsContentLang)
    {
        startPage();
        List<NewsContentLang> list = newsContentLangService.selectNewsContentLangList(newsContentLang);
        return getDataTable(list);
    }

    /**
     * 导出新闻内容多语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:lang:export')")
    @Log(title = "新闻内容多语言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsContentLang newsContentLang)
    {
        List<NewsContentLang> list = newsContentLangService.selectNewsContentLangList(newsContentLang);
        ExcelUtil<NewsContentLang> util = new ExcelUtil<NewsContentLang>(NewsContentLang.class);
        util.exportExcel(response, list, "新闻内容多语言数据");
    }

    /**
     * 获取新闻内容多语言详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:lang:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(newsContentLangService.selectNewsContentLangById(id));
    }

    /**
     * 新增新闻内容多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:add')")
    @Log(title = "新闻内容多语言", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsContentLang newsContentLang)
    {
        return toAjax(newsContentLangService.insertNewsContentLang(newsContentLang));
    }

    /**
     * 修改新闻内容多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:edit')")
    @Log(title = "新闻内容多语言", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsContentLang newsContentLang)
    {
        return toAjax(newsContentLangService.updateNewsContentLang(newsContentLang));
    }

    /**
     * 删除新闻内容多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:remove')")
    @Log(title = "新闻内容多语言", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsContentLangService.deleteNewsContentLangByIds(ids));
    }
}
