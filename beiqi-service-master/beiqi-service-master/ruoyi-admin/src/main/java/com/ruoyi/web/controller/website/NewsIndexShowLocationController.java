package com.ruoyi.web.controller.website;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.website.domain.vo.NewsIndexShowLocationVO;
import com.ruoyi.website.service.INewsIndexShowLocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "首页新闻配置")
@RestController
@RequestMapping("/website/news")
public class NewsIndexShowLocationController extends BaseController {

    @Autowired
    private INewsIndexShowLocationService newsIndexShowLocationService;


    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<NewsIndexShowLocationVO> list = newsIndexShowLocationService.selectIndexShowLocationList();
        return getDataTable(list);
    }


    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(newsIndexShowLocationService.selectIndexShowLocationById(id));
    }


    @ApiOperation("添加")
    @PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "首页新闻配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<NewsIndexShowLocationVO> locationVOS) {
        return toAjax(newsIndexShowLocationService.insertIndexShowLocation(locationVOS));
    }


    @ApiOperation("编辑")
    @PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "首页新闻配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<NewsIndexShowLocationVO> locationVOS) {
        return toAjax(newsIndexShowLocationService.updateIndexShowLocation(locationVOS));
    }


    @ApiOperation("批量删除")
    @PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "首页新闻配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(newsIndexShowLocationService.deleteIndexShowLocationByIds(ids));
    }


}
