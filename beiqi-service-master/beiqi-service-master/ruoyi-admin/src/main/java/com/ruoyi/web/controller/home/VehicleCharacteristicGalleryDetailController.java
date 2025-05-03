package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGalleryDetail;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicGalleryDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 特性-相册详情
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/characteristic/galleryDetail")
@Validated
@AllArgsConstructor
@Api(value = "VehicleCharacteristicGalleryDetailController", tags = "车辆特性-相册详情")
public class VehicleCharacteristicGalleryDetailController {
    private final VehicleCharacteristicGalleryDetailService vehicleCharacteristicGalleryDetailService;

    @PutMapping
    @ApiOperation("编辑特性-相册详情")
    @PreAuthorize("@ss.hasPermi('characteristic:gallery-detail:update')")
    @Log(title = "更新特性-相册详情", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleCharacteristicGalleryDetail vehicleCharacteristicGalleryDetail) {
        vehicleCharacteristicGalleryDetailService.edit(vehicleCharacteristicGalleryDetail);
        return AjaxResult.success();
    }

    @GetMapping
    @ApiOperation("获取特性-相册详情")
    @PreAuthorize("@ss.hasPermi('characteristic:gallery-detail:query')")
    @Log(title = "根据 ID 获取特性-相册详情", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleCharacteristicGalleryDetail> get(@RequestParam Integer vehicleClassId) {
        VehicleCharacteristicGalleryDetail vehicleCharacteristicGalleryDetail = vehicleCharacteristicGalleryDetailService.getByVehicleClassId(vehicleClassId);
        return AjaxResultT.success(vehicleCharacteristicGalleryDetail);
    }
}
