package com.ruoyi.web.controller.website;

import java.util.List;

import com.ruoyi.website.domain.vo.AfterSaleCountryLangVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.website.domain.AfterSaleCountry;
import com.ruoyi.system.service.IAfterSaleCountryService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 问卷星国家配置Controller
 * @date 2022-07-05
 */
@Api(tags = "问卷星国家配置")
@RestController
@RequestMapping("/system/country")
public class AfterSaleCountryController extends BaseController
{
    @Autowired
    private IAfterSaleCountryService afterSaleCountryService;


    @ApiOperation("列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "国家名称")})
    @PreAuthorize("@ss.hasPermi('system:country:list')")
    @GetMapping("/list")
    public TableDataInfo list(String name) {
        startPage();
        List<AfterSaleCountryLangVO> list = afterSaleCountryService.selectAfterSaleCountryList(name);
        return getDataTable(list);
    }



    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:country:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(afterSaleCountryService.selectAfterSaleCountryById(id));
    }


    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:country:add')")
    @Log(title = "问卷星国家配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<AfterSaleCountryLangVO> langVOS) {
        return toAjax(afterSaleCountryService.insertAfterSaleCountry(langVOS));
    }


    @ApiOperation("修改")
    @PreAuthorize("@ss.hasPermi('system:country:edit')")
    @Log(title = "问卷星国家配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<AfterSaleCountryLangVO> langVOS) {
        return toAjax(afterSaleCountryService.updateAfterSaleCountry(langVOS));
    }


    @ApiOperation("批量删除")
    @PreAuthorize("@ss.hasPermi('system:country:remove')")
    @Log(title = "问卷星国家配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(afterSaleCountryService.deleteAfterSaleCountryByIds(ids));
    }
}
