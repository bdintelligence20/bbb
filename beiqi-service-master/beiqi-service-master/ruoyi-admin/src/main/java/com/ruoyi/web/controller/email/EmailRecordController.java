package com.ruoyi.web.controller.email;

import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.email.domain.EmailRecord;
import com.ruoyi.email.service.IEmailRecordService;
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

import java.util.List;

/**
 * 邮件发送副本Controller
 * 
 * @author thh
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/system/record")
public class EmailRecordController extends BaseController
{
    @Autowired
    private IEmailRecordService emailRecordService;

    /**
     * 查询邮件发送副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmailRecord emailRecord)
    {
        startPage();
        List<EmailRecord> list = emailRecordService.selectEmailRecordList(emailRecord);
        return getDataTable(list);
    }

    /**
     * 导出邮件发送副本列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "邮件发送副本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmailRecord emailRecord)
    {
        List<EmailRecord> list = emailRecordService.selectEmailRecordList(emailRecord);
        ExcelUtil<EmailRecord> util = new ExcelUtil<EmailRecord>(EmailRecord.class);
        util.exportExcel(response, list, "邮件发送副本数据");
    }

    /**
     * 获取邮件发送副本详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(emailRecordService.selectEmailRecordById(id));
    }

    /**
     * 新增邮件发送副本
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "邮件发送副本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmailRecord emailRecord)
    {
        return toAjax(emailRecordService.insertEmailRecord(emailRecord));
    }

    /**
     * 修改邮件发送副本
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "邮件发送副本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmailRecord emailRecord)
    {
        return toAjax(emailRecordService.updateEmailRecord(emailRecord));
    }

    /**
     * 删除邮件发送副本
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "邮件发送副本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(emailRecordService.deleteEmailRecordByIds(ids));
    }
}
