package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.home.vehicle.domain.VehicleGallery;
import com.ruoyi.home.vehicle.mapper.VehicleGalleryMapper;
import com.ruoyi.home.vehicle.service.VehicleGalleryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 车辆相册
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleGalleryServiceImpl implements VehicleGalleryService {
    private final VehicleGalleryMapper vehicleGalleryMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleGallery vehicleGallery) {
        vehicleGallery.setCreateTime(new Date());
        vehicleGalleryMapper.insert(vehicleGallery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleGallery vehicleGallery) {
        vehicleGallery.setUpdateTime(new Date());
        vehicleGalleryMapper.updateById(vehicleGallery);
    }

    @Override
    public void delete(Integer id) {
        vehicleGalleryMapper.deleteById(id);
    }

    @Override
    public List<VehicleGallery> list(Integer vehicleClassId) {
        LambdaQueryWrapper<VehicleGallery> vehicleGalleryLambdaQuery = new LambdaQueryWrapper<>();
        vehicleGalleryLambdaQuery.eq(VehicleGallery::getVehicleClassId, vehicleClassId);
        return vehicleGalleryMapper.selectList(vehicleGalleryLambdaQuery);
    }

    @Override
    public VehicleGallery get(Integer id) {
        return vehicleGalleryMapper.selectById(id);
    }
}
