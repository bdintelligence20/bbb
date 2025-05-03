package com.ruoyi.web.controller.home;

import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.home.testdrive.service.AreaService;
import com.ruoyi.home.vo.testdrive.AreaHomeVo;
import com.ruoyi.website.service.LangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 区域
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("area")
@Validated
@AllArgsConstructor
@Api(value = "AreaController", tags = "区域")
public class AreaController {
    private final LangService langService;
    private AreaService areaService;

    @GetMapping("{lang}")
    @ApiOperation("获取对应语言的区域列表")
    public AjaxResultT<List<AreaHomeVo>> treeList(@PathVariable String lang,
                                                  @RequestParam Integer type) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResultT.error("不被允许的语言类型：" + lang);

        List<AreaHomeVo> areaHomeVos = areaService.listByLang(lang, type);
        return AjaxResultT.success(areaHomeVos);
    }
}
