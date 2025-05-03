package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.JoinUsJobCategory;
import com.ruoyi.website.domain.vo.JoinUsJobCategoryVO;
import com.ruoyi.website.service.IJoinUsJobCategoryService;
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

@Api(tags = "招聘工作岗位")
@RestController
@RequestMapping("/website/category")
public class JoinUsJobCategoryController extends BaseController
{
    @Autowired
    private IJoinUsJobCategoryService joinUsJobCategoryService;

    /**
     * 查询工作类型列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:category:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<JoinUsJobCategoryVO> list = joinUsJobCategoryService.selectJoinUsJobCategoryList();
        return getDataTable(list);
    }


    /**
     * 获取工作类型详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(joinUsJobCategoryService.selectJoinUsJobCategoryById(id));
    }

    /**
     * 新增工作类型
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:category:add')")
    @Log(title = "工作类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<JoinUsJobCategoryVO> categoryVOS) {
        return toAjax(joinUsJobCategoryService.insertJoinUsJobCategory(categoryVOS));
    }

    /**
     * 修改工作类型
     */
    @ApiOperation("修改")
    @PreAuthorize("@ss.hasPermi('system:category:edit')")
    @Log(title = "工作类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<JoinUsJobCategoryVO> categoryVOS)
    {
        return toAjax(joinUsJobCategoryService.updateJoinUsJobCategory(categoryVOS));
    }

    /**
     * 删除工作类型
     */
    @ApiOperation("批量删除")
    @PreAuthorize("@ss.hasPermi('system:category:remove')")
    @Log(title = "工作类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids)
    {
        return toAjax(joinUsJobCategoryService.deleteJoinUsJobCategoryByIds(ids));
    }
}
