package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.JoinUsWelfare;
import com.ruoyi.website.domain.vo.JoinUsWelfareVO;
import com.ruoyi.website.service.IJoinUsWelfareService;
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

@Api(tags = "招聘福利")
@RestController
@RequestMapping("/website/welfare")
public class JoinUsWelfareController extends BaseController
{
    @Autowired
    private IJoinUsWelfareService joinUsWelfareService;

    /**
     * 查询招聘福利配置列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:welfare:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<JoinUsWelfareVO> list = joinUsWelfareService.selectJoinUsWelfareList();
        return getDataTable(list);
    }


    /**
     * 获取招聘福利配置详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:welfare:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(joinUsWelfareService.selectJoinUsWelfareById(id));
    }

    /**
     * 新增招聘福利配置
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:welfare:add')")
    @Log(title = "招聘福利配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<JoinUsWelfareVO> joinUsWelfareVOS) {
        return toAjax(joinUsWelfareService.insertJoinUsWelfare(joinUsWelfareVOS));
    }

    /**
     * 修改招聘福利配置
     */
    @ApiOperation("修改")
    @PreAuthorize("@ss.hasPermi('system:welfare:edit')")
    @Log(title = "招聘福利配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<JoinUsWelfareVO> joinUsWelfareVOS)
    {
        return toAjax(joinUsWelfareService.updateJoinUsWelfare(joinUsWelfareVOS));
    }

    /**
     * 删除招聘福利配置
     */
    @ApiOperation("批量删除")
    @PreAuthorize("@ss.hasPermi('system:welfare:remove')")
    @Log(title = "招聘福利配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(joinUsWelfareService.deleteJoinUsWelfareByIds(ids));
    }
}
