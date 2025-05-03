package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGallery;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicGalleryHomeVo;

import java.util.List;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
public interface VehicleCharacteristicGalleryService {

    void insert(VehicleCharacteristicGallery vehicleCharacteristicGallery);

    void update(VehicleCharacteristicGallery vehicleCharacteristicGallery);

    void delete(Integer id);

    VehicleCharacteristicGallery getById(Integer id);

    List<VehicleCharacteristicGallery> list(Integer vehicleClassId);

    List<VehicleCharacteristicGalleryHomeVo> listByLang(Integer vehicleClassId, String lang);
}
