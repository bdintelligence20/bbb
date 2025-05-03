package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.NewsContent;
import com.ruoyi.website.service.INewsContentService;
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
 * 新闻内容Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/system/content")
public class NewsContentController extends BaseController
{
    @Autowired
    private INewsContentService newsContentService;

    /**
     * 查询新闻内容列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsContent newsContent)
    {
        startPage();
        List<NewsContent> list = newsContentService.selectNewsContentList(newsContent);
        return getDataTable(list);
    }

    /**
     * 导出新闻内容列表
     */
    @PreAuthorize("@ss.hasPermi('system:content:export')")
    @Log(title = "新闻内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsContent newsContent)
    {
        List<NewsContent> list = newsContentService.selectNewsContentList(newsContent);
        ExcelUtil<NewsContent> util = new ExcelUtil<NewsContent>(NewsContent.class);
        util.exportExcel(response, list, "新闻内容数据");
    }

    /**
     * 获取新闻内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(newsContentService.selectNewsContentById(id));
    }

    /**
     * 新增新闻内容
     */
    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @Log(title = "新闻内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsContent newsContent)
    {
        return toAjax(newsContentService.insertNewsContent(newsContent));
    }

    /**
     * 修改新闻内容
     */
    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @Log(title = "新闻内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsContent newsContent)
    {
        return toAjax(newsContentService.updateNewsContent(newsContent));
    }

    /**
     * 删除新闻内容
     */
    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @Log(title = "新闻内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsContentService.deleteNewsContentByIds(ids));
    }
}
