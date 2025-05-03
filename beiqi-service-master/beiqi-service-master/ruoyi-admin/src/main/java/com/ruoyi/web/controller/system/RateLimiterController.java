package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.RateLimiters;
import com.ruoyi.system.service.IRateLimiterService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 限流注解配置Controller
 * 
 * @author thh
 * @date 2023-09-08
 */
@RestController
@RequestMapping("/system/limiter")
public class RateLimiterController extends BaseController
{
    @Autowired
    private IRateLimiterService rateLimiterService;

    /**
     * 查询限流注解配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:limiter:list')")
    @GetMapping("/list")
    public TableDataInfo list(RateLimiters rateLimiters)
    {
        startPage();
        List<RateLimiters> list = rateLimiterService.selectRateLimiterList(rateLimiters);
        return getDataTable(list);
    }

    /**
     * 导出限流注解配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:limiter:export')")
    @Log(title = "限流注解配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RateLimiters rateLimiters)
    {
        List<RateLimiters> list = rateLimiterService.selectRateLimiterList(rateLimiters);
        ExcelUtil<RateLimiters> util = new ExcelUtil<RateLimiters>(RateLimiters.class);
        util.exportExcel(response, list, "限流注解配置数据");
    }

    /**
     * 获取限流注解配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:limiter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(rateLimiterService.selectRateLimiterById(id));
    }

    /**
     * 新增限流注解配置
     */
    @PreAuthorize("@ss.hasPermi('system:limiter:add')")
    @Log(title = "限流注解配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RateLimiters rateLimiters)
    {
        return toAjax(rateLimiterService.insertRateLimiter(rateLimiters));
    }

    /**
     * 修改限流注解配置
     */
    @PreAuthorize("@ss.hasPermi('system:limiter:edit')")
    @Log(title = "限流注解配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RateLimiters rateLimiters)
    {
        return toAjax(rateLimiterService.updateRateLimiter(rateLimiters));
    }

    /**
     * 删除限流注解配置
     */
    @PreAuthorize("@ss.hasPermi('system:limiter:remove')")
    @Log(title = "限流注解配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rateLimiterService.deleteRateLimiterByIds(ids));
    }
}
