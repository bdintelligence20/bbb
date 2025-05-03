package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleGallery;

import java.util.List;

/**
 * 车辆相册
 *
 * @author liuzhuoming
 */
public interface VehicleGalleryService {

    void insert(VehicleGallery vehicleGallery);

    void update(VehicleGallery vehicleGallery);

    void delete(Integer id);

    List<VehicleGallery> list(Integer vehicleClassId);

    VehicleGallery get(Integer id);

}
