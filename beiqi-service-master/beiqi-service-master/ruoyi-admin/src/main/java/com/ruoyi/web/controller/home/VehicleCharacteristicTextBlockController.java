package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicTextBlock;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicTextBlockService;
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
 * 特性-文本块
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("vehicle/characteristic/textBlock")
@Validated
@AllArgsConstructor
@Api(value = "VehicleCharacteristicTextBlockController", tags = "车辆特性-文本块")
public class VehicleCharacteristicTextBlockController {
    private final VehicleCharacteristicTextBlockService vehicleCharacteristicTextBlockService;

    @PostMapping
    @ApiOperation("新增特性-文本块")
    @PreAuthorize("@ss.hasPermi('characteristic:text-block:add')")
    @Log(title = "新增特性-文本块", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock) {
        vehicleCharacteristicTextBlockService.insert(vehicleCharacteristicTextBlock);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新特性-文本块")
    @PreAuthorize("@ss.hasPermi('characteristic:text-block:update')")
    @Log(title = "更新特性-文本块", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock) {
        vehicleCharacteristicTextBlockService.update(vehicleCharacteristicTextBlock);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除特性-文本块")
    @PreAuthorize("@ss.hasPermi('characteristic:text-block:delete')")
    @Log(title = "删除特性-文本块", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        vehicleCharacteristicTextBlockService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取特性-文本块")
    @PreAuthorize("@ss.hasPermi('characteristic:text-block:query')")
    @Log(title = "根据 ID 获取特性-文本块", businessType = BusinessType.OTHER)
    public AjaxResultT<VehicleCharacteristicTextBlock> get(@PathVariable Integer id) {
        VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock = vehicleCharacteristicTextBlockService.getById(id);
        return AjaxResultT.success(vehicleCharacteristicTextBlock);
    }

    @GetMapping
    @ApiOperation("获取特性-文本块列表")
    @PreAuthorize("@ss.hasPermi('characteristic:text-block:list')")
    @Log(title = "获取特性-文本块列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<VehicleCharacteristicTextBlock>> list(@RequestParam Integer vehicleClassId) {
        List<VehicleCharacteristicTextBlock> vehicleCharacteristicGalleries = vehicleCharacteristicTextBlockService.list(vehicleClassId);
        vehicleCharacteristicGalleries.sort(Comparator.comparingInt(VehicleCharacteristicTextBlock::getSort));
        return AjaxResultT.success(vehicleCharacteristicGalleries);
    }
}
