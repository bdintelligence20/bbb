package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleGallery;
import com.ruoyi.home.vehicle.service.VehicleGalleryService;
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
 * 车辆相册
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/gallery")
@AllArgsConstructor
@Validated
@Api(value = "VehicleGalleryController", tags = "车辆相册")
public class VehicleGalleryController {
    private final VehicleGalleryService vehicleGalleryService;

    @PostMapping
    @ApiOperation("新增车辆相册")
    @PreAuthorize("@ss.hasPermi('vehicle:gallery:add')")
    @Log(title = "新增车辆相册", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleGallery vehicleGallery) {
        vehicleGalleryService.insert(vehicleGallery);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新车辆相册")
    @PreAuthorize("@ss.hasPermi('vehicle:gallery:update')")
    @Log(title = "更新车辆相册", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleGallery vehicleGallery) {
        vehicleGalleryService.update(vehicleGallery);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除车辆相册")
    @PreAuthorize("@ss.hasPermi('vehicle:gallery:delete')")
    @Log(title = "删除车辆相册", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleGalleryService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取车辆相册")
    @PreAuthorize("@ss.hasPermi('vehicle:gallery:query')")
    @Log(title = "根据 ID 获取车辆相册", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleGallery> get(@PathVariable Integer id) {
        VehicleGallery vehicleGallery = vehicleGalleryService.get(id);
        return AjaxResultT.success(vehicleGallery);
    }

    @GetMapping
    @ApiOperation("获取车辆相册列表")
    @PreAuthorize("@ss.hasPermi('vehicle:gallery:list')")
    @Log(title = "获取车辆相册列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleGallery>> list(@RequestParam Integer vehicleClassId) {
        List<VehicleGallery> vehicleGalleries = vehicleGalleryService.list(vehicleClassId);
        vehicleGalleries.sort(Comparator.comparingInt(VehicleGallery::getSort));
        return AjaxResultT.success(vehicleGalleries);
    }
}
