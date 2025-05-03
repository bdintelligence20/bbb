package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.BlacklistIntercept;

import com.ruoyi.system.service.IBlacklistInterceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blacklist/intercept")
public class BlacklistInterceptController extends BaseController {

    @Autowired
    private IBlacklistInterceptService blacklistInterceptService;
    /**
     * 新增黑名单
     */
    @PreAuthorize("@ss.hasPermi('blacklist:intercept:add')")
    @Log(title = "新增黑名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlacklistIntercept blacklistIntercept)
    {
        return toAjax(blacklistInterceptService.insertBlacklistIntercept(blacklistIntercept));
    }

    /**
     * 删除黑名单
     */
    @PreAuthorize("@ss.hasPermi('blacklist:intercept:delect')")
    @Log(title = "删除黑名单", businessType = BusinessType.DELETE)
    @DeleteMapping
    public AjaxResult delect(@RequestBody BlacklistIntercept blacklistIntercept)
    {
        return toAjax(blacklistInterceptService.delectBlacklistIntercept(blacklistIntercept));
    }

    /**
     * 查询黑名单列表
     */
    @PreAuthorize("@ss.hasPermi('blacklist:intercept:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlacklistIntercept blacklistIntercept)
    {
        startPage();
        List<BlacklistIntercept> list = blacklistInterceptService.selectBlacklistInterceptList(blacklistIntercept);
        return getDataTable(list);
    }
}
