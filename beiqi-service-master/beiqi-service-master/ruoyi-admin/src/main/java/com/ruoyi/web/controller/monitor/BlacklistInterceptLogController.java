package com.ruoyi.web.controller.monitor;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.BlacklistInterceptLog;
import com.ruoyi.system.service.IBlacklistInterceptLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 黑名单拦截日志Controller
 * 
 * @author thh
 * @date 2023-09-06
 */
@RestController
@RequestMapping("/monitor/log")
public class BlacklistInterceptLogController extends BaseController
{
    @Autowired
    private IBlacklistInterceptLogService blacklistInterceptLogService;

    /**
     * 查询黑名单拦截日志列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlacklistInterceptLog blacklistInterceptLog)
    {
        startPage();
        List<BlacklistInterceptLog> list = blacklistInterceptLogService.selectBlacklistInterceptLogList(blacklistInterceptLog);
        return getDataTable(list);
    }

    /**
     * 导出黑名单拦截日志列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:log:export')")
    @Log(title = "黑名单拦截日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlacklistInterceptLog blacklistInterceptLog)
    {
        List<BlacklistInterceptLog> list = blacklistInterceptLogService.selectBlacklistInterceptLogList(blacklistInterceptLog);
        ExcelUtil<BlacklistInterceptLog> util = new ExcelUtil<BlacklistInterceptLog>(BlacklistInterceptLog.class);
        util.exportExcel(response, list, "黑名单拦截日志数据");
    }

    /**
     * 获取黑名单拦截日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('monitor:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(blacklistInterceptLogService.selectBlacklistInterceptLogById(id));
    }

}
