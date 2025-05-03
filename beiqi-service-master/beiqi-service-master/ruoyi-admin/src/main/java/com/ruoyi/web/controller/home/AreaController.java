package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.testdrive.domain.Area;
import com.ruoyi.home.testdrive.service.AreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

/**
 * 区域
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("area")
@Validated
@AllArgsConstructor
@Api(value = "AreaController", tags = "区域")
public class AreaController {
    private AreaService areaService;

    @PostMapping
    @ApiOperation("新增区域")
    @PreAuthorize("@ss.hasPermi('area:manage:add')")
    @Log(title = "新增区域", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid Area area) {
        areaService.insert(area);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新区域")
    @PreAuthorize("@ss.hasPermi('area:manage:update')")
    @Log(title = "更新区域", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid Area area) {
        areaService.update(area);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除区域")
    @PreAuthorize("@ss.hasPermi('area:manage:delete')")
    @Log(title = "删除区域", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        areaService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取区域")
    @PreAuthorize("@ss.hasPermi('area:manage:query')")
    @Log(title = "根据 ID 获取区域", businessType = BusinessType.OTHER)
    public AjaxResultT<Area> get(@PathVariable Integer id) {
        Area area = areaService.getById(id);
        return AjaxResultT.success(area);
    }

    @GetMapping
    @ApiOperation("获取区域列表")
    @PreAuthorize("@ss.hasPermi('area:manage:list')")
    @Log(title = "获取区域列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<Area>> list(@RequestParam(required = false) Integer type) {
        List<Area> areas = areaService.list(type);
        areas.sort(Comparator.comparingInt(Area::getSort));
        return AjaxResultT.success(areas);
    }

    @GetMapping("/en")
    @ApiOperation("获取区域下拉列表（en）")
    @PreAuthorize("@ss.hasPermi('area:manage:list')")
    @Log(title = "获取区域下拉列表（en）", businessType = BusinessType.OTHER)
    public AjaxResultT<List<Area>> listEn(@RequestParam(required = false) Integer type) {
        return list(type);
    }
}
