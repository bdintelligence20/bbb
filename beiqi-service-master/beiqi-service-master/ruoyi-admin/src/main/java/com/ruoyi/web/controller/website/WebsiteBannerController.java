package com.ruoyi.web.controller.website;

import java.util.List;

import com.ruoyi.website.domain.BannerLang;
import com.ruoyi.website.domain.vo.WebSiteBannerVO;
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
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.service.IWebsiteBannerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 首页bannerController
 *
 * @author thh
 * @date 2021-05-05
 */
@Api(tags = "banner管理")
@RestController
@RequestMapping("/website/banner")
public class WebsiteBannerController extends BaseController
{
    @Autowired
    private IWebsiteBannerService websiteBannerService;

    /**
     * 查询首页banner列表
     */
    @ApiOperation("查询首页banner列表")
    @PreAuthorize("@ss.hasPermi('website:banner:list')")
    @GetMapping("/list")
    public TableDataInfo list(WebsiteBanner websiteBanner)
    {
        startPage();
        List<WebsiteBanner> list = websiteBannerService.selectWebsiteBannerList(websiteBanner);
        return getDataTable(list);
    }


    /**
     * 获取首页banner详细信息
     */
    @ApiOperation("获取首页banner详细信息")
    @PreAuthorize("@ss.hasPermi('website:banner:query')")
    @GetMapping(value = "/{bannerId}")
    public AjaxResult getInfo(@PathVariable("bannerId") Long bannerId)
    {
        return AjaxResult.success(websiteBannerService.selectWebsiteBannerById(bannerId));
    }

    /**
     * 新增首页banner
     */
    @ApiOperation("新增首页banner")
    @PreAuthorize("@ss.hasPermi('website:banner:add')")
    @Log(title = "首页banner", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<WebSiteBannerVO> websiteBanners)
    {
        return toAjax(websiteBannerService.insertWebsiteBanner(websiteBanners));
    }

    /**
     * 修改首页banner
     */
    @ApiOperation("修改首页banner")
    @PreAuthorize("@ss.hasPermi('website:banner:edit')")
    @Log(title = "首页banner", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<BannerLang> bannerLangs)
    {
        return toAjax(websiteBannerService.updateWebsiteBanner(bannerLangs));
    }

    /**
     * 删除首页banner
     */
    @ApiOperation("删除首页banner")
    @PreAuthorize("@ss.hasPermi('website:banner:remove')")
    @Log(title = "首页banner", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bannerIds}")
    public AjaxResult remove(@PathVariable List<Long> bannerIds)
    {
        return toAjax(websiteBannerService.deleteWebsiteBannerByIds(bannerIds));
    }
}
