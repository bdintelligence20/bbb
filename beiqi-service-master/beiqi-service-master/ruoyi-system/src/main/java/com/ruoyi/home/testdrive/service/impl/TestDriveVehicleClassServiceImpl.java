package com.ruoyi.home.testdrive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.home.testdrive.domain.TestDriveVehicleClass;
import com.ruoyi.home.testdrive.domain.TestDriveVehicleClassLang;
import com.ruoyi.home.testdrive.mapper.TestDriveVehicleClassLangMapper;
import com.ruoyi.home.testdrive.mapper.TestDriveVehicleClassMapper;
import com.ruoyi.home.testdrive.service.TestDriveVehicleClassService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商家车型
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class TestDriveVehicleClassServiceImpl implements TestDriveVehicleClassService {
    private TestDriveVehicleClassMapper testDriveVehicleClassMapper;
    private TestDriveVehicleClassLangMapper testDriveVehicleClassLangMapper;

    @Override
    public TestDriveVehicleClass getById(Integer id) {
        TestDriveVehicleClass testDriveVehicleClass = testDriveVehicleClassMapper.selectById(id);
        if (testDriveVehicleClass == null) return null;
        LambdaQueryWrapper<TestDriveVehicleClassLang> testDriveVehicleClassLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        testDriveVehicleClassLangLambdaQueryWrapper.eq(TestDriveVehicleClassLang::getTestDriveVehicleClassId, testDriveVehicleClass.getId());
        List<TestDriveVehicleClassLang> testDriveVehicleClassLangs = testDriveVehicleClassLangMapper.selectList(testDriveVehicleClassLangLambdaQueryWrapper);
        testDriveVehicleClass.setLangs(testDriveVehicleClassLangs);
        return testDriveVehicleClass;
    }

    @Override
    public TestDriveVehicleClass getByLang(Integer id, String lang) {
        TestDriveVehicleClass testDriveVehicleClass = testDriveVehicleClassMapper.selectById(id);
        LambdaQueryWrapper<TestDriveVehicleClassLang> testDriveVehicleClassLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        testDriveVehicleClassLangLambdaQueryWrapper.eq(TestDriveVehicleClassLang::getTestDriveVehicleClassId, testDriveVehicleClass.getId());
        List<TestDriveVehicleClassLang> testDriveVehicleClassLangs = testDriveVehicleClassLangMapper.selectList(testDriveVehicleClassLangLambdaQueryWrapper);
        testDriveVehicleClassLangs = testDriveVehicleClassLangs.stream()
                .filter(testDriveVehicleClassLang -> Objects.equals(lang, testDriveVehicleClassLang.getLang()))
                .collect(Collectors.toList());
        if (testDriveVehicleClassLangs.isEmpty()) return null;
        testDriveVehicleClass.setLangs(testDriveVehicleClassLangs);
        return testDriveVehicleClass;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(TestDriveVehicleClass testDriveVehicleClass) {
        testDriveVehicleClass.setCreateTime(new Date());
        testDriveVehicleClassMapper.insert(testDriveVehicleClass);
        for (TestDriveVehicleClassLang testDriveVehicleClassLang : testDriveVehicleClass.getLangs()) {
            testDriveVehicleClassLang.setCreateTime(new Date());
            testDriveVehicleClassLang.setTestDriveVehicleClassId(testDriveVehicleClass.getId());
            testDriveVehicleClassLangMapper.insert(testDriveVehicleClassLang);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TestDriveVehicleClass testDriveVehicleClass) {
        testDriveVehicleClass.setUpdateTime(new Date());
        testDriveVehicleClassMapper.updateById(testDriveVehicleClass);
        //先删除全部再新增全部
        LambdaQueryWrapper<TestDriveVehicleClassLang> testDriveVehicleClassLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        testDriveVehicleClassLangLambdaQueryWrapper.eq(TestDriveVehicleClassLang::getTestDriveVehicleClassId, testDriveVehicleClass.getId());
        testDriveVehicleClassLangMapper.delete(testDriveVehicleClassLangLambdaQueryWrapper);
        for (TestDriveVehicleClassLang testDriveVehicleClassLang : testDriveVehicleClass.getLangs()) {
            testDriveVehicleClassLang.setCreateTime(new Date());
            testDriveVehicleClassLang.setTestDriveVehicleClassId(testDriveVehicleClass.getId());
            testDriveVehicleClassLangMapper.insert(testDriveVehicleClassLang);
        }
    }

    @Override
    public void deleteById(Integer id) {
        testDriveVehicleClassMapper.deleteById(id);
    }

    @Override
    public List<TestDriveVehicleClass> list() {
        LambdaQueryWrapper<TestDriveVehicleClass> testDriveVehicleClassLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<TestDriveVehicleClass> testDriveVehicleClasses = testDriveVehicleClassMapper.selectList(testDriveVehicleClassLambdaQueryWrapper);
        return testDriveVehicleClasses.stream()
                .peek(sellerVehicleClass -> {
                    LambdaQueryWrapper<TestDriveVehicleClassLang> testDriveVehicleClassLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    testDriveVehicleClassLangLambdaQueryWrapper.eq(TestDriveVehicleClassLang::getTestDriveVehicleClassId, sellerVehicleClass.getId());
                    List<TestDriveVehicleClassLang> testDriveVehicleClassLangs = testDriveVehicleClassLangMapper.selectList(testDriveVehicleClassLangLambdaQueryWrapper);
                    sellerVehicleClass.setLangs(testDriveVehicleClassLangs);
                })
                .sorted(Comparator.comparingInt(TestDriveVehicleClass::getSort))
                .collect(Collectors.toList());
    }
}
