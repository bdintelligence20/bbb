package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.contact.domain.Subscribe;
import com.ruoyi.home.contact.service.SubscribeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 订阅
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("contact/subscribe")
@AllArgsConstructor
@Validated
@Api(value = "SubscribeController", tags = "订阅")
public class SubscribeController {

    private SubscribeService subscribeService;

    @GetMapping
    @ApiOperation("获取订阅分页列表")
    @PreAuthorize("@ss.hasPermi('contactus:subscribe:list')")
    @Log(title = "获取订阅分页列表", businessType = BusinessType.OTHER)
    public AjaxResultT<TableDataInfoT<Subscribe>> list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                       @RequestParam(required = false) Integer status,
                                                       @RequestParam(required = false) String name) {
        TableDataInfoT<Subscribe> contactUsTableDataInfoT = subscribeService.page(pageNum, pageSize, status, name);
        return AjaxResultT.success(contactUsTableDataInfoT);
    }

    @PutMapping
    @ApiOperation("更新订阅状态")
    @PreAuthorize("@ss.hasPermi('contactus:subscribe:update')")
    @Log(title = "更新订阅状态", businessType = BusinessType.UPDATE)
    public AjaxResult update(@RequestParam Integer id,
                             @RequestParam Integer status,
                             @RequestParam(required = false) String reason) {
        subscribeService.updateStatus(id, status, reason);
        return AjaxResult.success();
    }
}
