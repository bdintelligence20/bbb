package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleColor;

import java.util.List;

/**
 * 车辆颜色
 *
 * @author liuzhuoming
 */
public interface VehicleColorService {

    void insert(VehicleColor vehicleColor);

    void update(VehicleColor vehicleColor);

    void delete(Integer id);

    List<VehicleColor> list(Integer vehicleClassId);

    VehicleColor get(Integer id);

}
