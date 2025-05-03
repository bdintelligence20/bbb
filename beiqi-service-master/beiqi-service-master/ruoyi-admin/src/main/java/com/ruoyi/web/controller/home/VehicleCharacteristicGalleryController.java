package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGallery;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicGalleryService;
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
 * 特性-相册
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/characteristic/gallery")
@Validated
@AllArgsConstructor
@Api(value = "VehicleCharacteristicGalleryController", tags = "车辆特性-相册")
public class VehicleCharacteristicGalleryController {
    private final VehicleCharacteristicGalleryService vehicleCharacteristicGalleryService;

    @PostMapping
    @ApiOperation("新增特性-相册")
    @PreAuthorize("@ss.hasPermi('characteristic:gallery:add')")
    @Log(title = "新增特性-相册", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleCharacteristicGallery vehicleCharacteristicGallery) {
        vehicleCharacteristicGalleryService.insert(vehicleCharacteristicGallery);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新特性-相册")
    @PreAuthorize("@ss.hasPermi('characteristic:gallery:update')")
    @Log(title = "更新特性-相册", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleCharacteristicGallery vehicleCharacteristicGallery) {
        vehicleCharacteristicGalleryService.update(vehicleCharacteristicGallery);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除特性-相册")
    @PreAuthorize("@ss.hasPermi('characteristic:gallery:delete')")
    @Log(title = "删除特性-相册", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleCharacteristicGalleryService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取特性-相册")
    @PreAuthorize("@ss.hasPermi('characteristic:gallery:query')")
    @Log(title = "根据 ID 获取特性-相册", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleCharacteristicGallery> get(@PathVariable Integer id) {
        VehicleCharacteristicGallery vehicleCharacteristicGallery = vehicleCharacteristicGalleryService.getById(id);
        return AjaxResultT.success(vehicleCharacteristicGallery);
    }

    @GetMapping
    @ApiOperation("获取特性-相册列表")
    @PreAuthorize("@ss.hasPermi('characteristic:gallery:list')")
    @Log(title = "获取特性-相册列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleCharacteristicGallery>> list(@RequestParam Integer vehicleClassId) {
        List<VehicleCharacteristicGallery> vehicleCharacteristicGalleries = vehicleCharacteristicGalleryService.list(vehicleClassId);
        vehicleCharacteristicGalleries.sort(Comparator.comparingInt(VehicleCharacteristicGallery::getSort));
        return AjaxResultT.success(vehicleCharacteristicGalleries);
    }
}
