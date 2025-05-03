package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.contact.domain.ContactUs;
import com.ruoyi.home.contact.domain.MediaContact;
import com.ruoyi.home.contact.service.ContactUsService;
import com.ruoyi.home.contact.service.MediaContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 联系
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("contact")
@AllArgsConstructor
@Validated
@Api(value = "ContactController", tags = "联系")
public class ContactController {

    private ContactUsService contactUsService;
    private MediaContactService mediaContactService;

    @GetMapping("/us")
    @ApiOperation("获取联系我们分页列表")
    @PreAuthorize("@ss.hasPermi('system:feedback:list')")
    @Log(title = "获取联系我们分页列表", businessType = BusinessType.OTHER)
    public AjaxResultT<TableDataInfoT<ContactUs>> us(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                     @RequestParam(required = false) Date startTime,
                                                     @RequestParam(required = false) Date enTime) {
        TableDataInfoT<ContactUs> contactUsTableDataInfoT = contactUsService.page(pageNum, pageSize, startTime, enTime);
        return AjaxResultT.success(contactUsTableDataInfoT);
    }

    @GetMapping("/media")
    @ApiOperation("获取媒体联系分页列表")
    @PreAuthorize("@ss.hasPermi('system:mediaLiaison:list')")
    @Log(title = "获取媒体联系分页列表", businessType = BusinessType.OTHER)
    public AjaxResultT<TableDataInfoT<MediaContact>> media(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                           @RequestParam(required = false) Date startTime,
                                                           @RequestParam(required = false) Date enTime) {
        TableDataInfoT<MediaContact> mediaContactTableDataInfoT = mediaContactService.page(pageNum, pageSize, startTime, enTime);
        return AjaxResultT.success(mediaContactTableDataInfoT);
    }
}
