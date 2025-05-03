package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.History;
import com.ruoyi.website.domain.HistoryLang;
import com.ruoyi.website.service.IHistoryService;
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
 * 历史Controller
 * 
 * @author thh
 * @date 2022-06-09
 */
@Api(tags = "北汽历史")
@RestController
@RequestMapping("/system/history")
public class HistoryController extends BaseController
{
    @Autowired
    private IHistoryService historyService;


    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(History history)
    {
        startPage();
        List<History> list = historyService.selectHistoryList(history);
        return getDataTable(list);
    }

    /**
     * 获取历史详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(historyService.selectHistoryById(id));
    }

    /**
     * 新增历史
     */
    @ApiOperation("新增历史")
    @PreAuthorize("@ss.hasPermi('system:history:add')")
    @Log(title = "历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<HistoryLang> historyLangs) {
        return toAjax(historyService.insertHistory(historyLangs));
    }

    /**
     * 修改历史
     */
    @ApiOperation("修改历史")
    @PreAuthorize("@ss.hasPermi('system:history:edit')")
    @Log(title = "历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<HistoryLang> historyLangs) {
        return toAjax(historyService.updateHistory(historyLangs));
    }

    /**
     * 删除历史
     */
    @ApiOperation("删除历史")
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
    @Log(title = "历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(historyService.deleteHistoryByIds(ids));
    }
}
