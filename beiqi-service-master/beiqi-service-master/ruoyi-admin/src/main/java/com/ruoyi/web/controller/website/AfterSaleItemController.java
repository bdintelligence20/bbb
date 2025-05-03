package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.AfterSaleItem;
import com.ruoyi.website.domain.vo.AfterSaleItemVO;
import com.ruoyi.website.service.IAfterSaleItemService;
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
 * 售后服务项Controller
 * 
 * @author thh
 * @date 2022-06-10
 */
@Api(tags = "售后服务项")
@RestController
@RequestMapping("/system/item")
public class AfterSaleItemController extends BaseController
{
    @Autowired
    private IAfterSaleItemService afterSaleItemService;

    /**
     * 查询售后服务项列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<AfterSaleItemVO> list = afterSaleItemService.selectAfterSaleItemList();
        return getDataTable(list);
    }


    /**
     * 获取售后服务项详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(afterSaleItemService.selectAfterSaleItemById(id));
    }

    /**
     * 新增售后服务项
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:item:add')")
    @Log(title = "售后服务项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<AfterSaleItemVO> saleItemVOS) {
        return toAjax(afterSaleItemService.insertAfterSaleItem(saleItemVOS));
    }

    /**
     * 修改售后服务项
     */
    @ApiOperation("修改")
    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "售后服务项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<AfterSaleItemVO> saleItemVOS) {
        return toAjax(afterSaleItemService.updateAfterSaleItem(saleItemVOS));
    }

    /**
     * 删除售后服务项
     */
    @ApiOperation("删除")
    @PreAuthorize("@ss.hasPermi('system:item:remove')")
    @Log(title = "售后服务项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(afterSaleItemService.deleteAfterSaleItemByIds(ids));
    }
}
