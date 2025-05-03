package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.RateLimiter;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.LimitType;
import com.ruoyi.home.contact.domain.ContactUs;
import com.ruoyi.home.contact.domain.MediaContact;
import com.ruoyi.home.contact.domain.Subscribe;
import com.ruoyi.home.contact.service.ContactUsService;
import com.ruoyi.home.contact.service.CountryService;
import com.ruoyi.home.contact.service.MediaContactService;
import com.ruoyi.home.contact.service.SubscribeService;
import com.ruoyi.home.task.HomeTask;
import com.ruoyi.home.vo.contact.CountryHomeVo;
import com.ruoyi.website.service.LangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 联系
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("contact")
@AllArgsConstructor
@Validated
@Api(value = "ContactController", tags = "联系（媒体联系|媒体订阅|联系我们|经销商国家")
public class ContactController {
    private ContactUsService contactUsService;
    private MediaContactService mediaContactService;
    private SubscribeService subscribeService;
    private CountryService countryService;
    private LangService langService;
    private HomeTask homeTask;

    @PostMapping("us/{lang}")
    @ApiOperation("新增联系我们")
    @RateLimiter(time = 100, count = 2, limitType = LimitType.IP)
    public AjaxResult add(@Valid @RequestBody ContactUs contactUs, @PathVariable String lang) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResult.error("Wrong language type, only (en,es,ar) is supported.");
        contactUs.setLang(lang);
        contactUsService.insert(contactUs);
        return AjaxResult.success();
    }

    @PostMapping("media/{lang}")
    @ApiOperation("新增媒体联系")
    @RateLimiter(time = 100, count = 2, limitType = LimitType.IP)
    public AjaxResult insert(@RequestBody @Valid MediaContact mediaContact, @PathVariable String lang) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResult.error("Wrong language type, only (en,es,ar) is supported.");
        mediaContact.setLang(lang);
        mediaContactService.insert(mediaContact);
        return AjaxResult.success();
    }

    @PostMapping("subscribe/{lang}")
    @ApiOperation("新增媒体订阅")
    @RateLimiter(time = 100, count = 2, limitType = LimitType.IP)
    public AjaxResult inserts(@RequestBody @Valid Subscribe subscribe, @PathVariable String lang) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResult.error("Wrong language type, only (en,es,ar) is supported.");
        subscribe.setLang(lang);
        subscribe.setStatus(0);
        subscribe.setReason(null);
        subscribeService.insert(subscribe);
        return AjaxResult.success();
    }

    @GetMapping("country/{lang}")
    @ApiOperation("获取对应语言的经销商国家列表")
    public AjaxResultT<List<CountryHomeVo>> countryList(@PathVariable String lang, @RequestParam(required = false) Integer areaId) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResultT.error("不被允许的语言类型：" + lang);

        List<CountryHomeVo> countryHomeVos = countryService.listByLang(lang, areaId);
        return AjaxResultT.success(countryHomeVos);
    }

    /*@GetMapping("test")
    public AjaxResult test() {
        homeTask.sendContactUsEmail();
        return AjaxResult.success();
    }*/

}
