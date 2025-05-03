package com.ruoyi.web.controller.website;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.website.service.IWebConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 获取站点配置
     */
    @PostMapping("/getWebConfig/{lang}")
    @ApiOperation("获取站点配置")
    public AjaxResult getWebConfig(@PathVariable String lang) {
        return AjaxResult.success(webConfigService.getWebConfig());
    }
}
