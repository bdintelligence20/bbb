package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleClass;
import com.ruoyi.home.vehicle.service.VehicleClassService;
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
 * 车辆型号
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/class")
@Validated
@AllArgsConstructor
@Api(value = "VehicleClassController", tags = "车辆型号")
public class VehicleClassController {
    private final VehicleClassService vehicleClassService;

    @PostMapping
    @ApiOperation("新增车辆型号")
    @PreAuthorize("@ss.hasPermi('vehicle:class:add')")
    @Log(title = "新增车辆型号", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleClass vehicleClass) {
        vehicleClassService.insert(vehicleClass);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新车辆型号")
    @PreAuthorize("@ss.hasPermi('vehicle:class:update')")
    @Log(title = "更新车辆型号", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleClass vehicleClass) {
        vehicleClassService.update(vehicleClass);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除车辆型号")
    @PreAuthorize("@ss.hasPermi('vehicle:class:delete')")
    @Log(title = "删除车辆型号", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleClassService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取车辆型号")
    @PreAuthorize("@ss.hasPermi('vehicle:class:query')")
    @Log(title = "根据 ID 获取车辆型号", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleClass> get(@PathVariable Integer id) {
        VehicleClass vehicleClass = vehicleClassService.get(id);
        return AjaxResultT.success(vehicleClass);
    }

    @GetMapping
    @ApiOperation("获取车辆型号列表")
    @PreAuthorize("@ss.hasPermi('vehicle:class:list')")
    @Log(title = "获取车辆型号列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleClass>> list(@RequestParam(required = false) Integer typeId) {
        List<VehicleClass> vehicleClasses = vehicleClassService.listByTypeId(typeId);
        vehicleClasses.sort(Comparator.comparingInt(VehicleClass::getSort));
        return AjaxResultT.success(vehicleClasses);
    }
}
