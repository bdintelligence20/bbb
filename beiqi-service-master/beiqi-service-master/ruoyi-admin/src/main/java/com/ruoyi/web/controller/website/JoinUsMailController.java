package com.ruoyi.web.controller.website;

import java.util.List;
import com.ruoyi.website.domain.JoinUsMail;
import com.ruoyi.website.service.IJoinUsMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;


@Api(tags = "招聘邮件发送记录")
@RestController
@RequestMapping("/website/mail")
public class JoinUsMailController extends BaseController
{
    @Autowired
    private IJoinUsMailService joinUsMailService;

    /**
     * 查询招聘邮件发送记录列表
     */
    @ApiOperation("列表")
    @PreAuthorize("@ss.hasPermi('system:mail:list')")
    @GetMapping("/list")
    public TableDataInfo list(JoinUsMail joinUsMail) {
        startPage();
        List<JoinUsMail> list = joinUsMailService.selectJoinUsMailList(joinUsMail);
        return getDataTable(list);
    }


    /**
     * 获取招聘邮件发送记录详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:mail:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return AjaxResult.success(joinUsMailService.selectJoinUsMailById(id));
//    }

    /**
     * 新增招聘邮件发送记录
     */
//    @PreAuthorize("@ss.hasPermi('system:mail:add')")
//    @Log(title = "招聘邮件发送记录", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody JoinUsMail joinUsMail)
//    {
//        return toAjax(joinUsMailService.insertJoinUsMail(joinUsMail));
//    }

    /**
     * 修改招聘邮件发送记录
     */
//    @PreAuthorize("@ss.hasPermi('system:mail:edit')")
//    @Log(title = "招聘邮件发送记录", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody JoinUsMail joinUsMail)
//    {
//        return toAjax(joinUsMailService.updateJoinUsMail(joinUsMail));
//    }

    /**
     * 删除招聘邮件发送记录
     */
//    @PreAuthorize("@ss.hasPermi('system:mail:remove')")
//    @Log(title = "招聘邮件发送记录", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(joinUsMailService.deleteJoinUsMailByIds(ids));
//    }
}
