package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.AfterSaleContent;
import com.ruoyi.website.domain.vo.AfterSaleContentVO;
import com.ruoyi.website.service.IAfterSaleContentService;
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
 * 售后文章内容Controller
 * 
 * @author thh
 * @date 2022-06-10
 */
@Api(tags = "售后顶部文案")
@RestController
@RequestMapping("/system/scontent")
public class AfterSaleContentController extends BaseController
{
    @Autowired
    private IAfterSaleContentService afterSaleContentService;

    /**
     * 查询售后文章内容列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:content:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<AfterSaleContentVO> list = afterSaleContentService.selectAfterSaleContentList();
        return getDataTable(list);
    }


    /**
     * 获取售后文章内容详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(afterSaleContentService.selectAfterSaleContentById(id));
    }

    /**
     * 新增售后文章内容
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:content:add')")
    @Log(title = "售后文章内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<AfterSaleContentVO> contentVOS)
    {
        return toAjax(afterSaleContentService.insertAfterSaleContent(contentVOS));
    }

    /**
     * 修改售后文章内容
     */
    @ApiOperation("编辑")
    @PreAuthorize("@ss.hasPermi('system:content:edit')")
    @Log(title = "售后文章内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<AfterSaleContentVO> contentVOS)
    {
        return toAjax(afterSaleContentService.updateAfterSaleContent(contentVOS));
    }

    /**
     * 删除售后文章内容
     */
    @ApiOperation("删除")
    @PreAuthorize("@ss.hasPermi('system:content:remove')")
    @Log(title = "售后文章内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(afterSaleContentService.deleteAfterSaleContentByIds(ids));
    }
}
