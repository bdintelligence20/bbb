package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysBlockIpListDto;
import com.ruoyi.system.service.SysBlockIpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 被阻止 ip 管理
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("/system/blockIp")
@Api(value = "SysBlockIpController", tags = "阻止 ip 管理")
@AllArgsConstructor
@Validated
public class SysBlockIpController {
    private SysBlockIpService sysBlockIpService;

    @PostMapping
    @ApiOperation("编辑阻止的 ip 列表")
    @PreAuthorize("@ss.hasPermi('system:block-ip:edit')")
    @Log(title = "编辑阻止的 ip 列表", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody @Valid SysBlockIpListDto ipListStr) {
        sysBlockIpService.editIpSet(ipListStr.getIpListStr());
        return AjaxResult.success();
    }

    @GetMapping
    @ApiOperation("获取阻止的 ip 列表")
    @PreAuthorize("@ss.hasPermi('system:block-ip:list')")
    @Log(title = "获取阻止的 ip 列表", businessType = BusinessType.OTHER)
    public AjaxResultT<String> list() {
        String blockIps = sysBlockIpService.getBlockIps();
        return AjaxResultT.success(blockIps);
    }
}
