package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.home.vehicle.domain.VehicleColor;
import com.ruoyi.home.vehicle.mapper.VehicleColorMapper;
import com.ruoyi.home.vehicle.service.VehicleColorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 车辆颜色
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleColorServiceImpl implements VehicleColorService {
    private final VehicleColorMapper vehicleColorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleColor vehicleColor) {
        vehicleColor.setCreateTime(new Date());
        vehicleColorMapper.insert(vehicleColor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleColor vehicleColor) {
        vehicleColor.setUpdateTime(new Date());
        vehicleColorMapper.updateById(vehicleColor);
    }

    @Override
    public void delete(Integer id) {
        vehicleColorMapper.deleteById(id);
    }

    @Override
    public List<VehicleColor> list(Integer vehicleClassId) {
        LambdaQueryWrapper<VehicleColor> vehicleColorLambdaQuery = new LambdaQueryWrapper<>();
        vehicleColorLambdaQuery.eq(VehicleColor::getVehicleClassId, vehicleClassId);
        return vehicleColorMapper.selectList(vehicleColorLambdaQuery);
    }

    @Override
    public VehicleColor get(Integer id) {
        return vehicleColorMapper.selectById(id);
    }
}
