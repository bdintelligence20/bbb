package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleColor;
import com.ruoyi.home.vehicle.service.VehicleColorService;
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
 * 车辆颜色
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/color")
@AllArgsConstructor
@Validated
@Api(value = "VehicleColorController", tags = "车辆颜色")
public class VehicleColorController {
    private final VehicleColorService vehicleColorService;

    @PostMapping
    @ApiOperation("新增车辆颜色")
    @PreAuthorize("@ss.hasPermi('vehicle:color:add')")
    @Log(title = "新增车辆颜色", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleColor vehicleColor) {
        vehicleColorService.insert(vehicleColor);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新车辆颜色")
    @PreAuthorize("@ss.hasPermi('vehicle:color:update')")
    @Log(title = "更新车辆颜色", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleColor vehicleColor) {
        vehicleColorService.update(vehicleColor);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除车辆颜色")
    @PreAuthorize("@ss.hasPermi('vehicle:color:delete')")
    @Log(title = "删除车辆颜色", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleColorService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取车辆颜色")
    @PreAuthorize("@ss.hasPermi('vehicle:color:query')")
    @Log(title = "根据 ID 获取车辆颜色", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleColor> get(@PathVariable Integer id) {
        VehicleColor vehicleColor = vehicleColorService.get(id);
        return AjaxResultT.success(vehicleColor);
    }

    @GetMapping
    @ApiOperation("获取车辆颜色列表")
    @PreAuthorize("@ss.hasPermi('vehicle:color:list')")
    @Log(title = "获取车辆颜色列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleColor>> list(@RequestParam Integer vehicleClassId) {
        List<VehicleColor> vehicleColors = vehicleColorService.list(vehicleClassId);
        vehicleColors.sort(Comparator.comparingInt(VehicleColor::getSort));
        return AjaxResultT.success(vehicleColors);
    }
}
