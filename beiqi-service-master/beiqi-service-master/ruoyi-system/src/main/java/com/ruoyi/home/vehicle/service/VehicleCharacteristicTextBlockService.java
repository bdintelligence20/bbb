package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleCharacteristicTextBlock;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicTextBlockHomeVo;

import java.util.List;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
public interface VehicleCharacteristicTextBlockService {

    void insert(VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock);

    void update(VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock);

    void delete(Integer id);

    VehicleCharacteristicTextBlock getById(Integer id);

    List<VehicleCharacteristicTextBlock> list(Integer vehicleClassId);

    List<VehicleCharacteristicTextBlockHomeVo> listByLang(Integer vehicleClassId, String lang);
}
