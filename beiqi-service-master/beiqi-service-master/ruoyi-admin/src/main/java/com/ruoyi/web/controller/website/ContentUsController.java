package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.ContentUs;
import com.ruoyi.website.service.IContentUsService;
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
 * 联系我们Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/system/us")
public class ContentUsController extends BaseController
{
    @Autowired
    private IContentUsService contentUsService;

    /**
     * 查询联系我们列表
     */
    @PreAuthorize("@ss.hasPermi('system:us:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContentUs contentUs)
    {
        startPage();
        List<ContentUs> list = contentUsService.selectContentUsList(contentUs);
        return getDataTable(list);
    }

    /**
     * 导出联系我们列表
     */
    @PreAuthorize("@ss.hasPermi('system:us:export')")
    @Log(title = "联系我们", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContentUs contentUs)
    {
        List<ContentUs> list = contentUsService.selectContentUsList(contentUs);
        ExcelUtil<ContentUs> util = new ExcelUtil<ContentUs>(ContentUs.class);
        util.exportExcel(response, list, "联系我们数据");
    }

    /**
     * 获取联系我们详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:us:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(contentUsService.selectContentUsById(id));
    }

    /**
     * 新增联系我们
     */
    @PreAuthorize("@ss.hasPermi('system:us:add')")
    @Log(title = "联系我们", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContentUs contentUs)
    {
        return toAjax(contentUsService.insertContentUs(contentUs));
    }

    /**
     * 修改联系我们
     */
    @PreAuthorize("@ss.hasPermi('system:us:edit')")
    @Log(title = "联系我们", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContentUs contentUs)
    {
        return toAjax(contentUsService.updateContentUs(contentUs));
    }

    /**
     * 删除联系我们
     */
    @PreAuthorize("@ss.hasPermi('system:us:remove')")
    @Log(title = "联系我们", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contentUsService.deleteContentUsByIds(ids));
    }
}
