package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.website.domain.JoinUsJobTitle;
import com.ruoyi.website.domain.vo.JoinUsJobTitileSelectVO;
import com.ruoyi.website.domain.vo.JoinUsJobTitleVO;
import com.ruoyi.website.service.IJoinUsJobTitleService;
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

@Api(tags = "招聘职位")
@RestController
@RequestMapping("/website/title")
public class JoinUsJobTitleController extends BaseController
{
    @Autowired
    private IJoinUsJobTitleService joinUsJobTitleService;

    /**
     * 查询职位列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:title:list')")
    @GetMapping("/list")
    public TableDataInfo list(JoinUsJobTitileSelectVO selectVO) {
        startPage();
        List<JoinUsJobTitleVO> list = joinUsJobTitleService.selectJoinUsJobTitleList(selectVO);
        return getDataTable(list);
    }


    /**
     * 获取职位详细信息
     */
    @ApiOperation("详细信息")
    @PreAuthorize("@ss.hasPermi('system:title:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(joinUsJobTitleService.selectJoinUsJobTitleById(id));
    }

    /**
     * 新增职位
     */
    @ApiOperation("新增")
    @PreAuthorize("@ss.hasPermi('system:title:add')")
    @Log(title = "职位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<JoinUsJobTitleVO> titleVOS) {
        return toAjax(joinUsJobTitleService.insertJoinUsJobTitle(titleVOS));
    }

    /**
     * 修改职位
     */
    @ApiOperation("编辑")
    @PreAuthorize("@ss.hasPermi('system:title:edit')")
    @Log(title = "职位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody List<JoinUsJobTitleVO> titleVOS) {
        return toAjax(joinUsJobTitleService.updateJoinUsJobTitle(titleVOS));
    }

    /**
     * 删除职位
     */
    @ApiOperation("删除")
    @PreAuthorize("@ss.hasPermi('system:title:remove')")
    @Log(title = "职位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(joinUsJobTitleService.deleteJoinUsJobTitleByIds(ids));
    }
}
