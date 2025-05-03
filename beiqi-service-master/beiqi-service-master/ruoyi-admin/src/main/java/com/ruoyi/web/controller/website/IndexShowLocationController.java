package com.ruoyi.web.controller.website;


import com.ruoyi.website.domain.IndexShowLocation;
import com.ruoyi.website.domain.vo.IndexShowLocationVO;
import com.ruoyi.website.service.IIndexShowLocationService;
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
import com.ruoyi.common.core.page.TableDataInfo;
import java.util.List;

/**
 * 首页配置Controller
 * 
 * @author thh
 * @date 2022-06-21
 */
@Api(tags = "首页配置")
@RestController
@RequestMapping("/website/location")
public class IndexShowLocationController extends BaseController
{
    @Autowired
    private IIndexShowLocationService indexShowLocationService;


    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:location:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<IndexShowLocationVO> list = indexShowLocationService.selectIndexShowLocationList();
        return getDataTable(list);
    }


    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:location:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(indexShowLocationService.selectIndexShowLocationById(id));
    }


    @ApiOperation("添加")
    @PreAuthorize("@ss.hasPermi('system:location:add')")
    @Log(title = "首页配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<IndexShowLocationVO> locationVOS) {
        return toAjax(indexShowLocationService.insertIndexShowLocation(locationVOS));
    }


    @ApiOperation("编辑")
    @PreAuthorize("@ss.hasPermi('system:location:edit')")
    @Log(title = "首页配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<IndexShowLocationVO> locationVOS) {
        return toAjax(indexShowLocationService.updateIndexShowLocation(locationVOS));
    }


    @ApiOperation("批量删除")
    @PreAuthorize("@ss.hasPermi('system:location:remove')")
    @Log(title = "首页配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(indexShowLocationService.deleteIndexShowLocationByIds(ids));
    }

    @ApiOperation("展示位类型")
    @PreAuthorize("@ss.hasPermi('system:location:query')")
    @GetMapping
    public AjaxResult getLocationType() {
        return AjaxResult.success(indexShowLocationService.selectIndexShowLocationType());
    }


}
