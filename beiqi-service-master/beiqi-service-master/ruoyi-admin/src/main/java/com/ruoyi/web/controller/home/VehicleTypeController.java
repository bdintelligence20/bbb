package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleType;
import com.ruoyi.home.vehicle.service.VehicleTypeService;
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
 * 车辆类型
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/type")
@AllArgsConstructor
@Validated
@Api(value = "VehicleTypeController", tags = "车辆类型")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;

    @PostMapping
    @ApiOperation("新增车辆类型")
    @PreAuthorize("@ss.hasPermi('vehicle:type:add')")
    @Log(title = "新增车辆类型", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleType vehicleType) {
        vehicleTypeService.insert(vehicleType);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新车辆类型")
    @PreAuthorize("@ss.hasPermi('vehicle:type:update')")
    @Log(title = "更新车辆类型", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleType vehicleType) {
        vehicleTypeService.update(vehicleType);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除车辆类型")
    @PreAuthorize("@ss.hasPermi('vehicle:type:delete')")
    @Log(title = "删除车辆类型", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleTypeService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取车辆类型")
    @PreAuthorize("@ss.hasPermi('vehicle:type:query')")
    @Log(title = "根据 ID 获取车辆类型", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleType> get(@PathVariable Integer id) {
        VehicleType vehicleType = vehicleTypeService.get(id);
        return AjaxResultT.success(vehicleType);
    }

    @GetMapping
    @ApiOperation("获取车辆类型列表")
    @PreAuthorize("@ss.hasPermi('vehicle:type:list')")
    @Log(title = "获取车辆类型列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleType>> list() {
        List<VehicleType> vehicleTypes = vehicleTypeService.list(true);
        vehicleTypes.sort(Comparator.comparingInt(VehicleType::getSort));
        return AjaxResultT.success(vehicleTypes);
    }
}
