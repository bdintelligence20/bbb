package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResultT;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.home.testdrive.domain.TestDriveCommit;
import com.ruoyi.home.testdrive.service.TestDriveCommitService;
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
 * 试驾预约
 *
 * @author liuzhuoming
 */
@RestController
@RequestMapping("testdrive/commit")
@Validated
@AllArgsConstructor
@Api(value = "TestDriveCommitController", tags = "预约试驾提交")
public class TestDriveCommitController {
    private TestDriveCommitService testDriveCommitService;

    @GetMapping
    @ApiOperation("获取试驾预约提交分页列表")
    @PreAuthorize("@ss.hasPermi('testdrive:client:list')")
    @Log(title = "获取试驾预约提交分页列表", businessType = BusinessType.OTHER)
    public AjaxResultT<TableDataInfoT<TestDriveCommit>> media(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                              @RequestParam(required = false) Date startTime,
                                                              @RequestParam(required = false) Date enTime) {
        TableDataInfoT<TestDriveCommit> testDriveCommitTableDataInfoT = testDriveCommitService.page(pageNum, pageSize, startTime, enTime);
        return AjaxResultT.success(testDriveCommitTableDataInfoT);
    }
}
