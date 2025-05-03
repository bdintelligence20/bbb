package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleType;

import java.util.List;

/**
 * 车辆类型
 *
 * @author liuzhuoming
 */
public interface VehicleTypeService {

    void insert(VehicleType vehicleType);

    void update(VehicleType vehicleType);

    void delete(Integer id);

    List<VehicleType> list(Boolean presentation);

    VehicleType get(Integer id);

}
