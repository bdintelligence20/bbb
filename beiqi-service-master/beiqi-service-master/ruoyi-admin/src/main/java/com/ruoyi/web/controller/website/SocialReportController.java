package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.SocialReport;
import com.ruoyi.website.domain.vo.SocialReportVO;
import com.ruoyi.website.service.ISocialReportService;
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
import com.ruoyi.common.core.page.TableDataInfo;

@Api(tags = "社会责任报告")
@RestController
@RequestMapping("/system/report")
public class SocialReportController extends BaseController
{
    @Autowired
    private ISocialReportService socialReportService;

    /**
     * 查询社会责任报告列表
     */
    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "是否展示（0显示 1隐藏）")
    })
    @PreAuthorize("@ss.hasPermi('system:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(Integer status) {
        startPage();
        List<SocialReportVO> list = socialReportService.selectSocialReportList(status);
        return getDataTable(list);
    }


    /**
     * 获取社会责任报告详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(socialReportService.selectSocialReportById(id));
    }

    /**
     * 新增社会责任报告
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:report:add')")
    @Log(title = "社会责任报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<SocialReportVO> reportVOS) {
        return toAjax(socialReportService.insertSocialReport(reportVOS));
    }

    /**
     * 修改社会责任报告
     */
    @ApiOperation("编辑")
    @PreAuthorize("@ss.hasPermi('system:report:edit')")
    @Log(title = "社会责任报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<SocialReportVO> reportVOS)
    {
        return toAjax(socialReportService.updateSocialReport(reportVOS));
    }

    /**
     * 删除社会责任报告
     */
    @ApiOperation("批量删除")
    @PreAuthorize("@ss.hasPermi('system:report:remove')")
    @Log(title = "社会责任报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(socialReportService.deleteSocialReportByIds(ids));
    }
}
