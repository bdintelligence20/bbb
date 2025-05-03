package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.BannerLang;
import com.ruoyi.website.service.IBannerLangService;
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
 * 首页banner多语言Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/system/blang")
public class BannerLangController extends BaseController
{
    @Autowired
    private IBannerLangService bannerLangService;

    /**
     * 查询首页banner多语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:lang:list')")
    @GetMapping("/list")
    public TableDataInfo list(BannerLang bannerLang)
    {
        startPage();
        List<BannerLang> list = bannerLangService.selectBannerLangList(bannerLang);
        return getDataTable(list);
    }

    /**
     * 导出首页banner多语言列表
     */
    @PreAuthorize("@ss.hasPermi('system:lang:export')")
    @Log(title = "首页banner多语言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BannerLang bannerLang)
    {
        List<BannerLang> list = bannerLangService.selectBannerLangList(bannerLang);
        ExcelUtil<BannerLang> util = new ExcelUtil<BannerLang>(BannerLang.class);
        util.exportExcel(response, list, "首页banner多语言数据");
    }

    /**
     * 获取首页banner多语言详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:lang:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bannerLangService.selectBannerLangById(id));
    }

    /**
     * 新增首页banner多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:add')")
    @Log(title = "首页banner多语言", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BannerLang bannerLang)
    {
        return toAjax(bannerLangService.insertBannerLang(bannerLang));
    }

    /**
     * 修改首页banner多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:edit')")
    @Log(title = "首页banner多语言", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BannerLang bannerLang)
    {
        return toAjax(bannerLangService.updateBannerLang(bannerLang));
    }

    /**
     * 删除首页banner多语言
     */
    @PreAuthorize("@ss.hasPermi('system:lang:remove')")
    @Log(title = "首页banner多语言", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bannerLangService.deleteBannerLangByIds(ids));
    }
}
