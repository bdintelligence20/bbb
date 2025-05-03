package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.AfterSaleIntro;
import com.ruoyi.website.domain.vo.AfterSaleIntroVO;
import com.ruoyi.website.service.IAfterSaleIntroService;
import io.swagger.annotations.Api;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售后简介列Controller
 * 
 * @author thh
 * @date 2022-06-10
 */
@Api(tags = "售后底部文章列表")
@RestController
@RequestMapping("/system/intro")
public class AfterSaleIntroController extends BaseController
{
    @Autowired
    private IAfterSaleIntroService afterSaleIntroService;

    /**
     * 查询售后简介列列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:intro:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<AfterSaleIntroVO> list = afterSaleIntroService.selectAfterSaleIntroList();
        return getDataTable(list);
    }

    /**
     * 获取售后简介列详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:intro:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(afterSaleIntroService.selectAfterSaleIntroById(id));
    }

    /**
     * 新增售后简介列
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:intro:add')")
    @Log(title = "售后简介列", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<AfterSaleIntroVO> saleIntroVOS)
    {
        return toAjax(afterSaleIntroService.insertAfterSaleIntro(saleIntroVOS));
    }

    /**
     * 修改售后简介列
     */
    @ApiOperation("编辑")
    @PreAuthorize("@ss.hasPermi('system:intro:edit')")
    @Log(title = "售后简介列", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<AfterSaleIntroVO> saleIntroVOS) {
        return toAjax(afterSaleIntroService.updateAfterSaleIntro(saleIntroVOS));
    }

    /**
     * 删除售后简介列
     */
    @ApiOperation("删除")
    @PreAuthorize("@ss.hasPermi('system:intro:remove')")
    @Log(title = "售后简介列", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(afterSaleIntroService.deleteAfterSaleIntroByIds(ids));
    }
}
