package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.testdrive.domain.TestDriveVehicleClass;
import com.ruoyi.home.testdrive.service.TestDriveVehicleClassService;
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
 * 车型
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("testdrive/vehicle/class")
@Validated
@AllArgsConstructor
@Api(value = "TestDriveVehicleClassController", tags = "预约试驾车型")
public class TestDriveVehicleClassController {
    private final TestDriveVehicleClassService testDriveVehicleClassService;

    @PostMapping
    @ApiOperation("新增车型")
    @PreAuthorize("@ss.hasPermi('testdrive:vehicle-class:add')")
    @Log(title = "新增车型", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid TestDriveVehicleClass testDriveVehicleClass) {
        testDriveVehicleClassService.insert(testDriveVehicleClass);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新车型")
    @PreAuthorize("@ss.hasPermi('testdrive:vehicle-class:update')")
    @Log(title = "更新车型", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid TestDriveVehicleClass testDriveVehicleClass) {
        testDriveVehicleClassService.update(testDriveVehicleClass);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除车型")
    @PreAuthorize("@ss.hasPermi('testdrive:vehicle-class:delete')")
    @Log(title = "删除车型", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        testDriveVehicleClassService.deleteById(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取车型")
    @PreAuthorize("@ss.hasPermi('testdrive:vehicle-class:query')")
    @Log(title = "根据 ID 获取车型", businessType = BusinessType.OTHER)
    public AjaxResultT<TestDriveVehicleClass> get(@PathVariable Integer id) {
        TestDriveVehicleClass testDriveVehicleClass = testDriveVehicleClassService.getById(id);
        return AjaxResultT.success(testDriveVehicleClass);
    }

    @GetMapping
    @ApiOperation("获取车型列表")
    @PreAuthorize("@ss.hasPermi('testdrive:vehicle-class:list')")
    @Log(title = "获取车型列表", businessType = BusinessType.OTHER)
    public AjaxResultT<List<TestDriveVehicleClass>> list() {
        List<TestDriveVehicleClass> testDriveVehicleClasses = testDriveVehicleClassService.list();
        testDriveVehicleClasses.sort(Comparator.comparingInt(TestDriveVehicleClass::getSort));
        return AjaxResultT.success(testDriveVehicleClasses);
    }
}
