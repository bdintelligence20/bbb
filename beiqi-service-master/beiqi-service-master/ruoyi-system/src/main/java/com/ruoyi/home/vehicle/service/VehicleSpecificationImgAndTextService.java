package com.ruoyi.home.vehicle.service;

import com.ruoyi.home.vehicle.domain.VehicleSpecificationImgAndText;
import com.ruoyi.home.vo.vehicle.VehicleSpecificationImgAndTextHomeVo;

import java.util.List;

/**
 * 车型规格
 *
 * @author liuzhuoming
 */
public interface VehicleSpecificationImgAndTextService {

    void insert(VehicleSpecificationImgAndText vehicleSpecificationImgAndText);

    void update(VehicleSpecificationImgAndText vehicleSpecificationImgAndText);

    void delete(Integer id);

    VehicleSpecificationImgAndText getById(Integer id);

    List<VehicleSpecificationImgAndText> list(Integer vehicleClassId);

    List<VehicleSpecificationImgAndTextHomeVo> listByLang(Integer vehicleClassId, String lang);
}
