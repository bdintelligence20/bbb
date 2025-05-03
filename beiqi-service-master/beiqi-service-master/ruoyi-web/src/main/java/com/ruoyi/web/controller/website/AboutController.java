package com.ruoyi.web.controller.website;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.website.service.IAfterSaleContentService;
import com.ruoyi.website.service.IHistoryService;
import com.ruoyi.website.service.ISocialContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "关于北汽")
@RestController
@RequestMapping("/website/about")
public class AboutController {

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private IAfterSaleContentService afterSaleContentService;

    @Autowired
    private ISocialContentService socialContentService;

    @ApiImplicitParams(@ApiImplicitParam(name = "l", value = "语种", required = true))
    @ApiOperation("北汽历史")
    @PostMapping("/history/{l}")
    public AjaxResult getNewsList(@PathVariable("l") String l){
        return  AjaxResult.success(historyService.getHistoryList(l));
    }


    @ApiImplicitParams(@ApiImplicitParam(name = "l", value = "语种", required = true))
    @ApiOperation("售后")
    @PostMapping("/asale/{l}")
    public AjaxResult afterSale(@PathVariable("l") String l){
        return  AjaxResult.success(afterSaleContentService.getAfterSaleContent(l));
    }

    @ApiImplicitParams(@ApiImplicitParam(name = "l", value = "语种", required = true))
    @ApiOperation("社会责任")
    @PostMapping("/social/{l}")
    public AjaxResult social(@PathVariable("l") String l){
        return  AjaxResult.success(socialContentService.getSocial(l));
    }

    @ApiImplicitParams(@ApiImplicitParam(name = "l", value = "语种", required = true))
    @ApiOperation("售后问卷调查国家列表")
    @PostMapping("/acountry/{l}")
    public AjaxResult afterSaleCountry(@PathVariable("l") String l){
        return  AjaxResult.success(afterSaleContentService.getAfterSaleCountry(l));
    }



}
