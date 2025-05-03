package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleSpecificationImgAndText;
import com.ruoyi.home.vehicle.service.VehicleSpecificationImgAndTextService;
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
 * 特性-图文轮播
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/specification/imgAndText")
@Validated
@AllArgsConstructor
@Api(value = "VehicleSpecificationImgAndTextController", tags = "车辆特性-图文轮播")
public class VehicleSpecificationImgAndTextController {
    private final VehicleSpecificationImgAndTextService vehicleSpecificationImgAndTextService;

    @PostMapping
    @ApiOperation("新增特性-图文轮播")
    @PreAuthorize("@ss.hasPermi('specification:img-and-text:add')")
    @Log(title = "新增特性-图文轮播", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleSpecificationImgAndText vehicleSpecificationImgAndText) {
        vehicleSpecificationImgAndTextService.insert(vehicleSpecificationImgAndText);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新特性-图文轮播")
    @PreAuthorize("@ss.hasPermi('specification:img-and-text:update')")
    @Log(title = "更新特性-图文轮播", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleSpecificationImgAndText vehicleSpecificationImgAndText) {
        vehicleSpecificationImgAndTextService.update(vehicleSpecificationImgAndText);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除特性-图文轮播")
    @PreAuthorize("@ss.hasPermi('specification:img-and-text:delete')")
    @Log(title = "删除特性-图文轮播", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleSpecificationImgAndTextService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取特性-图文轮播")
    @PreAuthorize("@ss.hasPermi('specification:img-and-text:query')")
    @Log(title = "根据 ID 获取特性-图文轮播", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleSpecificationImgAndText> get(@PathVariable Integer id) {
        VehicleSpecificationImgAndText vehicleSpecificationImgAndText = vehicleSpecificationImgAndTextService.getById(id);
        return AjaxResultT.success(vehicleSpecificationImgAndText);
    }

    @GetMapping
    @ApiOperation("获取特性-图文轮播列表")
    @PreAuthorize("@ss.hasPermi('specification:img-and-text:list')")
    @Log(title = "获取特性-图文轮播列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleSpecificationImgAndText>> list(@RequestParam Integer vehicleClassId) {
        List<VehicleSpecificationImgAndText> vehicleCharacteristicGalleries = vehicleSpecificationImgAndTextService.list(vehicleClassId);
        vehicleCharacteristicGalleries.sort(Comparator.comparingInt(VehicleSpecificationImgAndText::getSort));
        return AjaxResultT.success(vehicleCharacteristicGalleries);
    }
}
