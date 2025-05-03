package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.Dealer;
import com.ruoyi.website.service.IDealerService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 经销商Controller
 * 
 * @author thh
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/system/dealer")
public class DealerController extends BaseController
{
    @Autowired
    private IDealerService dealerService;

    /**
     * 查询经销商列表
     */
    @PreAuthorize("@ss.hasPermi('system:dealer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Dealer dealer)
    {
        startPage();
        List<Dealer> list = dealerService.selectDealerList(dealer);
        return getDataTable(list);
    }

    /**
     * 导出经销商列表
     */
    @PreAuthorize("@ss.hasPermi('system:dealer:export')")
    @Log(title = "经销商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Dealer dealer)
    {
        List<Dealer> list = dealerService.selectDealerList(dealer);
        ExcelUtil<Dealer> util = new ExcelUtil<Dealer>(Dealer.class);
        util.exportExcel(response, list, "经销商数据");
    }

    /**
     * 获取经销商详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dealer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(dealerService.selectDealerById(id));
    }

    /**
     * 新增经销商
     */
    @PreAuthorize("@ss.hasPermi('system:dealer:add')")
    @Log(title = "经销商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dealer dealer)
    {
        return toAjax(dealerService.insertDealer(dealer));
    }

    /**
     * 修改经销商
     */
    @PreAuthorize("@ss.hasPermi('system:dealer:edit')")
    @Log(title = "经销商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dealer dealer)
    {
        return toAjax(dealerService.updateDealer(dealer));
    }

    /**
     * 删除经销商
     */
    @PreAuthorize("@ss.hasPermi('system:dealer:remove')")
    @Log(title = "经销商", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dealerService.deleteDealerByIds(ids));
    }
}
