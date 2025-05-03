package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleCharacteristicImgAndText;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicImgAndTextHomeVo;

import java.util.List;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
public interface VehicleCharacteristicImgAndTextService {

    void insert(VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText);

    void update(VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText);

    void delete(Integer id);

    VehicleCharacteristicImgAndText getById(Integer id);

    List<VehicleCharacteristicImgAndText> list(Integer vehicleClassId);

    List<VehicleCharacteristicImgAndTextHomeVo> listByLang(Integer vehicleClassId, String lang);
}
