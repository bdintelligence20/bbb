package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.contact.domain.Country;
import com.ruoyi.home.contact.service.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 国家
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("contact/country")
@AllArgsConstructor
@Validated
@Api(value = "CountryController", tags = "国家经销商")
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    @ApiOperation("新增国家")
    @PreAuthorize("@ss.hasPermi('contact:country:add')")
    @Log(title = "新增国家", businessType = BusinessType.INSERT)
    public AjaxResult insert(@RequestBody @Valid Country country) {
        countryService.insert(country);
        return AjaxResult.success();
    }

    @PutMapping
    @ApiOperation("更新国家")
    @PreAuthorize("@ss.hasPermi('contact:country:update')")
    @Log(title = "更新国家", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestBody @Valid Country country) {
        countryService.update(country);
        return AjaxResult.success();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除国家")
    @PreAuthorize("@ss.hasPermi('contact:country:delete')")
    @Log(title = "删除国家", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Integer id) {
        countryService.delete(id);
        return AjaxResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("获取国家")
    @PreAuthorize("@ss.hasPermi('contact:country:query')")
    @Log(title = "根据 ID 获取国家", businessType = BusinessType.OTHER)
    public AjaxResultT<Country> get(@PathVariable Integer id) {
        Country country = countryService.getById(id);
        return AjaxResultT.success(country);
    }

    @GetMapping
    @ApiOperation("获取国家分页列表")
    @PreAuthorize("@ss.hasPermi('contact:country:page')")
    @Log(title = "获取国家分页列表", businessType = BusinessType.OTHER)
    public AjaxResultT<TableDataInfoT<Country>> page(@RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        TableDataInfoT<Country> vehicleGalleries = countryService.page(pageNum, pageSize);
        return AjaxResultT.success(vehicleGalleries);
    }
}
