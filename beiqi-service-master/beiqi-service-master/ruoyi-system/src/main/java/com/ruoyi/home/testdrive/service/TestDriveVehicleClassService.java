package com.ruoyi.home.testdrive.service;

import com.ruoyi.home.testdrive.domain.TestDriveVehicleClass;

import java.util.List;

/**
 * 商家车型
 *
 * @author liuzhuoming
 */
public interface TestDriveVehicleClassService {
    TestDriveVehicleClass getById(Integer id);

    TestDriveVehicleClass getByLang(Integer id, String lang);

    void insert(TestDriveVehicleClass testDriveVehicleClass);

    void update(TestDriveVehicleClass testDriveVehicleClass);

    void deleteById(Integer id);

    List<TestDriveVehicleClass> list();
}
