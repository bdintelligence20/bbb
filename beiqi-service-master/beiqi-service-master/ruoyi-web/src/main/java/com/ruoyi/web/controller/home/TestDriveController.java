package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.RateLimiter;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.enums.LimitType;
import com.ruoyi.home.testdrive.domain.TestDriveCommit;
import com.ruoyi.home.testdrive.domain.TestDriveVehicleClass;
import com.ruoyi.home.testdrive.domain.TestDriveVehicleClassLang;
import com.ruoyi.home.testdrive.service.TestDriveCommitService;
import com.ruoyi.home.testdrive.service.TestDriveVehicleClassService;
import com.ruoyi.home.vo.testdrive.TestDriveVehicleClassHomeVo;
import com.ruoyi.website.service.LangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 预约试驾申请
 *
 * @author liuzhuoming
 */
@RequestMapping("testdrive")
@RestController
@AllArgsConstructor
@Validated
@Api(value = "TestDriveController", tags = "预约试驾（申请|车型")
public class TestDriveController {
    private final LangService langService;
    private TestDriveCommitService testDriveCommitService;
    private TestDriveVehicleClassService testDriveVehicleClassService;

    @GetMapping("vehicle/class/{lang}")
    @ApiOperation("获取对应语言的试驾车型列表")
    public AjaxResultT<List<TestDriveVehicleClassHomeVo>> list(@PathVariable String lang) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResultT.error("不被允许的语言类型：" + lang);

        List<TestDriveVehicleClass> testDriveVehicleClasses = testDriveVehicleClassService.list();
        List<TestDriveVehicleClassHomeVo> testDriveVehicleClassHomeVos = testDriveVehicleClasses.stream()
                .map(testDriveVehicleClass -> {
                    TestDriveVehicleClassHomeVo testDriveVehicleClassHomeVo = new TestDriveVehicleClassHomeVo();
                    testDriveVehicleClassHomeVo.setId(testDriveVehicleClass.getId());
                    testDriveVehicleClassHomeVo.setImgUrl(testDriveVehicleClass.getImgUrl());
                    testDriveVehicleClassHomeVo.setSort(testDriveVehicleClass.getSort());
                    TestDriveVehicleClassLang testDriveVehicleClassLang = null;
                    for (TestDriveVehicleClassLang testDriveVehicleClassLangIterator : testDriveVehicleClass.getLangs()) {
                        if (Objects.equals(lang, testDriveVehicleClassLangIterator.getLang()))
                            testDriveVehicleClassLang = testDriveVehicleClassLangIterator;
                    }
                    if (testDriveVehicleClassLang == null) return null;
                    testDriveVehicleClassHomeVo.setName(testDriveVehicleClassLang.getName());
                    return testDriveVehicleClassHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(TestDriveVehicleClassHomeVo::getSort))
                .collect(Collectors.toList());
        return AjaxResultT.success(testDriveVehicleClassHomeVos);
    }

    @PostMapping("commit/{lang}")
    @RateLimiter(time = 86400, count = 3, limitType = LimitType.IP)
    @ApiOperation("提交预约试驾申请")
    public AjaxResult insert(@RequestBody @Valid TestDriveCommit testDriveCommit, @PathVariable String lang) {
        boolean isAllowedLangCode = langService.isAllowedLangCode(lang);
        if (!isAllowedLangCode) return AjaxResult.error("Wrong language type, only (en,es,ar) is supported.");
        testDriveCommit.setLang(lang);
        testDriveCommitService.insert(testDriveCommit);
        return AjaxResult.success();
    }
}
