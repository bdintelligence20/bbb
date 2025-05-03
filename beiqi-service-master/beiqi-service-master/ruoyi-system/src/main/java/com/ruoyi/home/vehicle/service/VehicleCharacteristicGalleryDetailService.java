package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGalleryDetail;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicGalleryDetailHomeVo;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
public interface VehicleCharacteristicGalleryDetailService {

    void edit(VehicleCharacteristicGalleryDetail vehicleCharacteristicGalleryDetail);

    VehicleCharacteristicGalleryDetail getByVehicleClassId(Integer vehicleClassId);

    VehicleCharacteristicGalleryDetailHomeVo getByLang(Integer vehicleClassId, String lang);
}
