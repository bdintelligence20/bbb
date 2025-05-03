package com.ruoyi.home.testdrive.service;

import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.testdrive.domain.TestDriveCommit;
import com.ruoyi.home.testdrive.dto.TestDriveCommitSearchDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 试驾预约申请
 *
 * @author liuzhuoming
 */
public interface TestDriveCommitService {
    void insert(TestDriveCommit testDriveCommit);

    void update(TestDriveCommit testDriveCommit);

    void updateStatusById(Integer id, Integer status, String reason);

    List<TestDriveCommit> list(TestDriveCommitSearchDto testDriveCommitSearchDto);

    TestDriveCommit getById(Integer id);

    TableDataInfoT<TestDriveCommit> page(Integer pageNum, Integer pageSize, Date startTime, Date endTime);

    List<Map<String, Object>> countryBy(String startCreateTime, String endCreateTime);

    List<Map<String, Object>> countryDateBy(String startCreateTime, String endCreateTime);
}
