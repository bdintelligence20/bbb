package com.ruoyi.web.controller.website;

import java.util.List;

import com.ruoyi.website.domain.JoinUsArea;
import com.ruoyi.website.domain.vo.JoinUsAreaVO;
import com.ruoyi.website.service.IJoinUsAreaService;
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

@Api(tags = "招聘区域")
@RestController
@RequestMapping("/website/area")
public class JoinUsAreaController extends BaseController
{
    @Autowired
    private IJoinUsAreaService joinUsAreaService;

    /**
     * 查询工作地点列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:area:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<JoinUsAreaVO> list = joinUsAreaService.selectJoinUsAreaList();
        return getDataTable(list);
    }


    /**
     * 获取工作地点详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:area:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(joinUsAreaService.selectJoinUsAreaById(id));
    }

    /**
     * 新增工作地点
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:area:add')")
    @Log(title = "工作地点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<JoinUsAreaVO> joinUsAreaVOS) {
        return toAjax(joinUsAreaService.insertJoinUsArea(joinUsAreaVOS));
    }

    /**
     * 修改工作地点
     */
    @ApiOperation("修改")
    @PreAuthorize("@ss.hasPermi('system:area:edit')")
    @Log(title = "工作地点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<JoinUsAreaVO> joinUsAreaVO)
    {
        return toAjax(joinUsAreaService.updateJoinUsArea(joinUsAreaVO));
    }

    /**
     * 删除工作地点
     */
    @ApiOperation("删除")
    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @Log(title = "工作地点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(joinUsAreaService.deleteJoinUsAreaByIds(ids));
    }
}
