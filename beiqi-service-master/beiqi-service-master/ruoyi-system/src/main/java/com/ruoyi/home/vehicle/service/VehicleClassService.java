package com.ruoyi.home.vehicle.service;

import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.vehicle.domain.VehicleClass;
import com.ruoyi.home.vo.vehicle.VehicleClassHomeVo;
import com.ruoyi.home.vo.vehicle.VehicleClassSummaryHomeVo;

import java.util.List;

/**
 * 车型
 *
 * @author liuzhuoming
 */
public interface VehicleClassService {
    void insert(VehicleClass vehicleClass);

    void update(VehicleClass vehicleClass);

    void delete(Integer id);

    VehicleClass get(Integer id);

    List<VehicleClass> listByTypeId(Integer typeId);

    TableDataInfoT<VehicleClassSummaryHomeVo> pageByLang(String lang, String keyword, Integer pageNum, Integer pageSize);

    VehicleClassHomeVo getByLang(Integer id, String lang);

    List<VehicleClassSummaryHomeVo> listClass(String lang, Integer typeId,Boolean presentation);

}
