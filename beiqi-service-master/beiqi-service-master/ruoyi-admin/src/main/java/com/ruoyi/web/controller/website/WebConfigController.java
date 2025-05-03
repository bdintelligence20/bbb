package com.ruoyi.web.controller.website;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.website.domain.vo.WebConfigVO;
import com.ruoyi.website.service.IWebConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 站点配置Controller
 *
 * @author thh
 * @date 2021-06-08
 */
@RestController
@RequestMapping("/website/webConfig")
@Api(value = "WebConfigController", tags = "站点配置")
public class WebConfigController extends BaseController {
    @Autowired
    private IWebConfigService webConfigService;

    /**
     * 修改站点配置
     */
    @PreAuthorize("@ss.hasPermi('website:webConfig:edit')")
    @Log(title = "站点配置", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("编辑站点配置")
    public AjaxResult edit(@RequestBody WebConfigVO webConfigVO) {
        return toAjax(webConfigService.updateWebConfig(webConfigVO));
    }

    /**
     * 获取站点配置
     */
    @PreAuthorize("@ss.hasPermi('website:webConfig:query')")
    @Log(title = "getWebConfig", businessType = BusinessType.OTHER)
    @PostMapping("/getWebConfig")
    @ApiOperation("获取站点配置")
    public AjaxResult getWebConfig() {
        return AjaxResult.success(webConfigService.getWebConfig());
    }


}
