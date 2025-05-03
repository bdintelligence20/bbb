package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicImgAndText;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicImgAndTextService;
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
 * 特性-图文
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/characteristic/imgAndText")
@Validated
@AllArgsConstructor
@Api(value = "VehicleCharacteristicImgAndTextController", tags = "车辆特性-图文")
public class VehicleCharacteristicImgAndTextController {
    private final VehicleCharacteristicImgAndTextService vehicleCharacteristicImgAndTextService;

    @PostMapping
    @ApiOperation("新增特性-图文")
    @PreAuthorize("@ss.hasPermi('characteristic:img-and-text:add')")
    @Log(title = "新增特性-图文", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText) {
        vehicleCharacteristicImgAndTextService.insert(vehicleCharacteristicImgAndText);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新特性-图文")
    @PreAuthorize("@ss.hasPermi('characteristic:img-and-text:update')")
    @Log(title = "更新特性-图文", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText) {
        vehicleCharacteristicImgAndTextService.update(vehicleCharacteristicImgAndText);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除特性-图文")
    @PreAuthorize("@ss.hasPermi('characteristic:img-and-text:delete')")
    @Log(title = "删除特性-图文", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleCharacteristicImgAndTextService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取特性-图文")
    @PreAuthorize("@ss.hasPermi('characteristic:img-and-text:query')")
    @Log(title = "根据 ID 获取特性-图文", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleCharacteristicImgAndText> get(@PathVariable Integer id) {
        VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText = vehicleCharacteristicImgAndTextService.getById(id);
        return AjaxResultT.success(vehicleCharacteristicImgAndText);
    }

    @GetMapping
    @ApiOperation("获取特性-图文列表")
    @PreAuthorize("@ss.hasPermi('characteristic:img-and-text:list')")
    @Log(title = "获取特性-图文列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleCharacteristicImgAndText>> list(@RequestParam Integer vehicleClassId) {
        List<VehicleCharacteristicImgAndText> vehicleCharacteristicGalleries = vehicleCharacteristicImgAndTextService.list(vehicleClassId);
        vehicleCharacteristicGalleries.sort(Comparator.comparingInt(VehicleCharacteristicImgAndText::getSort));
        return AjaxResultT.success(vehicleCharacteristicGalleries);
    }
}
