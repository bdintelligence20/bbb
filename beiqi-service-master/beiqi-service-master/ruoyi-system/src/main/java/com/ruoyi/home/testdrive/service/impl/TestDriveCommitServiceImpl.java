package com.ruoyi.home.testdrive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.config.EmailConfig;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.utils.EmailService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.home.testdrive.domain.Area;
import com.ruoyi.home.testdrive.domain.TestDriveCommit;
import com.ruoyi.home.testdrive.domain.TestDriveVehicleClass;
import com.ruoyi.home.testdrive.domain.TestDriveVehicleClassLang;
import com.ruoyi.home.testdrive.dto.TestDriveCommitSearchDto;
import com.ruoyi.home.testdrive.mapper.TestDriveCommitMapper;
import com.ruoyi.home.testdrive.service.AreaService;
import com.ruoyi.home.testdrive.service.TestDriveCommitService;
import com.ruoyi.home.testdrive.service.TestDriveVehicleClassService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 试驾预约申请
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
@Slf4j
public class TestDriveCommitServiceImpl implements TestDriveCommitService {
    private TestDriveCommitMapper testDriveCommitMapper;
    private AreaService areaService;
    private TestDriveVehicleClassService testDriveVehicleClassService;
    private EmailConfig emailConfig;
    private EmailService emailService;

    @Override
    public void insert(TestDriveCommit testDriveCommit) {
        testDriveCommit.setCreateTime(new Date());
        testDriveCommitMapper.insert(testDriveCommit);
        EmailConfig.TestDriveConfig testDriveConfig = emailConfig.getTestDrive();
        if (testDriveConfig.isEnabled()) {
            Area area = areaService.getByLang(testDriveCommit.getAreaId(), testDriveCommit.getLang());

            if (area == null) {
                updateStatusById(testDriveCommit.getId(), 2, "区域不存在");
                return;
            }
            String toEmails = area.getEmails();
            if (StringUtils.isEmpty(toEmails)) {
                updateStatusById(testDriveCommit.getId(), 2, "区域负责人邮箱不存在");
                return;
            }
            List<String> emails = Arrays.asList(toEmails.split(","));

            //第一个为收件邮箱
            String toEmail = emails.get(0);
            //其他为多个抄送邮箱
            List<String> ccMailsDb = emails.subList(1, emails.size());

            String fromEmail = testDriveConfig.getFrom();
            TestDriveVehicleClass testDriveVehicleClass = testDriveVehicleClassService.getByLang(testDriveCommit.getTestDriveVehicleClassId(), testDriveCommit.getLang());
            if (testDriveVehicleClass == null) {
                updateStatusById(testDriveCommit.getId(), 2, "试驾车型不存在");
                return;
            }
            TestDriveVehicleClassLang testDriveVehicleClassLang = testDriveVehicleClass.getLangs().get(0);

            //配置文件中的抄送邮箱
            List<String> ccMails = testDriveConfig.getCc();

            //全量copy，防止配置文件信息被覆写
            List<String> ccMailsMerge = new ArrayList<>(ccMails);
            //合并数据库和配置文件的抄送邮箱的集合
            if (!ccMailsDb.isEmpty()) ccMailsMerge.addAll(ccMailsDb);

            Map<String, Object> kvs = new HashMap<>();
            kvs.put("name", testDriveCommit.getName());
            kvs.put("gender", testDriveCommit.getGender());
            kvs.put("telephone", testDriveCommit.getTelephone());
            kvs.put("email", testDriveCommit.getMailbox());
            kvs.put("vehicle_name", testDriveVehicleClassLang.getName());
            kvs.put("lang", testDriveCommit.getLang());
            kvs.put("country", area.getLangs().get(0).getName());

            try {
                emailService.send(EmailService.EmailTemplate.TEST_DRIVE, "BAIC test drive application",
                        fromEmail, toEmail, ccMailsMerge,
                        kvs);
            } catch (Exception e) {
                log.error("发送预约试驾失败：{}", e.getMessage());
            }
        }
    }

    @Override
    public void update(TestDriveCommit testDriveCommit) {
        testDriveCommit.setUpdateTime(new Date());
        testDriveCommitMapper.updateById(testDriveCommit);
    }

    @Override
    public void updateStatusById(Integer id, Integer status, String reason) {
        TestDriveCommit testDriveCommit = new TestDriveCommit();
        testDriveCommit.setId(id);
        testDriveCommit.setStatus(status);
        testDriveCommit.setReason(reason);
        update(testDriveCommit);
    }

    @Override
    public List<TestDriveCommit> list(TestDriveCommitSearchDto testDriveCommitSearchDto) {
        LambdaQueryWrapper<TestDriveCommit> testDriveCommitLambdaQueryWrapper = new LambdaQueryWrapper<>();
        testDriveCommitLambdaQueryWrapper
                .eq(StringUtils.isNotEmpty(testDriveCommitSearchDto.getLang()),
                        TestDriveCommit::getLang, testDriveCommitSearchDto.getLang())
                .eq(StringUtils.isNotEmpty(testDriveCommitSearchDto.getGender()),
                        TestDriveCommit::getGender, testDriveCommitSearchDto.getGender())
                .eq(testDriveCommitSearchDto.getAreaId() != null,
                        TestDriveCommit::getAreaId, testDriveCommitSearchDto.getAreaId())
                .eq(testDriveCommitSearchDto.getTestDriveVehicleClassId() != null,
                        TestDriveCommit::getTestDriveVehicleClassId, testDriveCommitSearchDto.getTestDriveVehicleClassId())
                .like(StringUtils.isNotEmpty(testDriveCommitSearchDto.getName()),
                        TestDriveCommit::getName, testDriveCommitSearchDto.getName())
                .like(StringUtils.isNotEmpty(testDriveCommitSearchDto.getTelephone()),
                        TestDriveCommit::getTelephone, testDriveCommitSearchDto.getTelephone())
                .like(StringUtils.isNotEmpty(testDriveCommitSearchDto.getMailbox()),
                        TestDriveCommit::getMailbox, testDriveCommitSearchDto.getMailbox())
                .eq(testDriveCommitSearchDto.getStatus() != null,
                        TestDriveCommit::getStatus, testDriveCommitSearchDto.getStatus())
                .ge(testDriveCommitSearchDto.getCreateTimeStart() != null,
                        TestDriveCommit::getCreateTime, testDriveCommitSearchDto.getCreateTimeStart())
                .le(testDriveCommitSearchDto.getCreateTimeEnd() != null,
                        TestDriveCommit::getCreateTime, testDriveCommitSearchDto.getCreateTimeEnd())
                .ge(testDriveCommitSearchDto.getUpdateTimeStart() != null,
                        TestDriveCommit::getUpdateTime, testDriveCommitSearchDto.getUpdateTimeStart())
                .le(testDriveCommitSearchDto.getUpdateTimeEnd() != null,
                        TestDriveCommit::getUpdateTime, testDriveCommitSearchDto.getUpdateTimeEnd());
        return testDriveCommitMapper.selectList(testDriveCommitLambdaQueryWrapper);
    }

    @Override
    public TestDriveCommit getById(Integer id) {
        return testDriveCommitMapper.selectById(id);
    }

    @Override
    public TableDataInfoT<TestDriveCommit> page(Integer pageNum, Integer pageSize, Date startTime, Date endTime) {
        LambdaQueryWrapper<TestDriveCommit> contactUsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactUsLambdaQueryWrapper
                .lt(endTime != null, TestDriveCommit::getCreateTime, endTime)
                .gt(startTime != null, TestDriveCommit::getCreateTime, startTime)
                .orderByDesc(TestDriveCommit::getCreateTime);
        Page<TestDriveCommit> page = new Page<>(pageNum, pageSize);
        Page<TestDriveCommit> pageRes = testDriveCommitMapper.selectPage(page, contactUsLambdaQueryWrapper);

        List<TestDriveCommit> testDriveCommits = pageRes.getRecords().stream()
                .peek(testDriveCommit -> {
                    TestDriveVehicleClass testDriveVehicleClass = testDriveVehicleClassService.getById(testDriveCommit.getTestDriveVehicleClassId());
                    if (testDriveVehicleClass != null) {
                        String enVehicleName = testDriveVehicleClass.getLangs().stream()
                                .filter(testDriveVehicleClassLang -> Objects.equals(testDriveVehicleClassLang.getLang(), "en"))
                                .map(TestDriveVehicleClassLang::getName)
                                .collect(Collectors.joining());
                        testDriveCommit.setTestDriveVehicleClassName(enVehicleName);
                    }
                    Area area = areaService.getByLang(testDriveCommit.getAreaId(), "en");
                    if (area != null) {
                        String enAreaName = area.getLangs().get(0).getName();
                        testDriveCommit.setAreaName(enAreaName);
                    }
                })
                .collect(Collectors.toList());
        return new TableDataInfoT<>(pageRes.getTotal(), testDriveCommits);
    }

    @Override
    public List<Map<String, Object>> countryBy(String startCreateTime, String endCreateTime) {
        QueryWrapper<TestDriveCommit> wrapper = new QueryWrapper<TestDriveCommit>()
                .select("area_id,(select name from area_lang where lang='en' and area_id=test_drive_commit.area_id limit 1) as area_name ,count(1) as total")
                .between(!StringUtils.isEmpty(startCreateTime) && !StringUtils.isEmpty(endCreateTime), "create_time", startCreateTime, endCreateTime)
                .groupBy("area_id");

        List<Map<String, Object>> testDriveCommitList = testDriveCommitMapper.selectMaps(wrapper);
        return testDriveCommitList;
    }

    @Override
    public List<Map<String, Object>> countryDateBy(String startCreateTime, String endCreateTime) {
        QueryWrapper<TestDriveCommit> wrapper = new QueryWrapper<TestDriveCommit>()
                .select("left(create_time, 10) as create_date, area_id,(select name from area_lang where lang='en' and area_id=test_drive_commit.area_id limit 1) as area_name ,count(1) as total")
                .between(!StringUtils.isEmpty(startCreateTime) && !StringUtils.isEmpty(endCreateTime), "create_time", startCreateTime, endCreateTime)
                .groupBy("left(create_time, 10),area_id")
                .orderByDesc("create_date", "total");

        List<Map<String, Object>> testDriveCommitList = testDriveCommitMapper.selectMaps(wrapper);
        return testDriveCommitList;
    }
}
