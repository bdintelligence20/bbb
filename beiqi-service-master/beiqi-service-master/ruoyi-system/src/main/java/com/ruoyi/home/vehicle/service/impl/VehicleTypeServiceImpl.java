package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.home.vehicle.domain.VehicleType;
import com.ruoyi.home.vehicle.domain.VehicleTypeLang;
import com.ruoyi.home.vehicle.mapper.VehicleTypeLangMapper;
import com.ruoyi.home.vehicle.mapper.VehicleTypeMapper;
import com.ruoyi.home.vehicle.service.VehicleTypeService;
import com.ruoyi.website.service.LangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 车辆类型
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {
    private final VehicleTypeMapper vehicleTypeMapper;
    private final VehicleTypeLangMapper vehicleTypeLangMapper;
    private final LangService langService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleType vehicleType) {
        vehicleType.setCreateTime(new Date());
        vehicleTypeMapper.insert(vehicleType);
        for (VehicleTypeLang vehicleTypeLang : vehicleType.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleTypeLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleTypeLang.getLang());
            }
            vehicleTypeLang.setVehicleTypeId(vehicleType.getId());
            vehicleTypeLang.setCreateTime(new Date());
            vehicleTypeLangMapper.insert(vehicleTypeLang);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleType vehicleType) {
        vehicleType.setUpdateTime(new Date());
        vehicleTypeMapper.updateById(vehicleType);
        //删除全部再新增全部
        LambdaQueryWrapper<VehicleTypeLang> vehicleTypeLangLambdaQuery = new LambdaQueryWrapper<>();
        vehicleTypeLangLambdaQuery.eq(VehicleTypeLang::getVehicleTypeId, vehicleType.getId());
        vehicleTypeLangMapper.delete(vehicleTypeLangLambdaQuery);
        for (VehicleTypeLang vehicleTypeLang : vehicleType.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleTypeLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleTypeLang.getLang());
            }
            vehicleTypeLang.setVehicleTypeId(vehicleType.getId());
            vehicleTypeLang.setCreateTime(new Date());
            vehicleTypeLangMapper.insert(vehicleTypeLang);
        }
    }

    @Override
    public void delete(Integer id) {
        vehicleTypeMapper.deleteById(id);
    }

    @Override
    public List<VehicleType> list(Boolean presentation) {
        LambdaQueryWrapper<VehicleType> vehicleTypeLambdaQuery = new LambdaQueryWrapper<>();
        List<VehicleType> vehicleTypes = vehicleTypeMapper.selectList(vehicleTypeLambdaQuery);
        return vehicleTypes.stream()
                .peek(vehicleType -> {
                    LambdaQueryWrapper<VehicleTypeLang> vehicleTypeLangLambdaQuery = new LambdaQueryWrapper<>();
                    if (!presentation){
                        vehicleTypeLangLambdaQuery.eq(VehicleTypeLang::getEnable,1);
                    }
                    vehicleTypeLangLambdaQuery.eq(VehicleTypeLang::getVehicleTypeId, vehicleType.getId());
                    List<VehicleTypeLang> vehicleTypeLangs = vehicleTypeLangMapper.selectList(vehicleTypeLangLambdaQuery);
                    vehicleType.setLangs(vehicleTypeLangs);
                })
                .collect(Collectors.toList());
    }

    @Override
    public VehicleType get(Integer id) {
        VehicleType vehicleType = vehicleTypeMapper.selectById(id);
        if (vehicleType == null) return null;
        LambdaQueryWrapper<VehicleTypeLang> vehicleTypeLangLambdaQuery = new LambdaQueryWrapper<>();
        vehicleTypeLangLambdaQuery.eq(VehicleTypeLang::getVehicleTypeId, id);
        List<VehicleTypeLang> vehicleTypeLangs = vehicleTypeLangMapper.selectList(vehicleTypeLangLambdaQuery);
        vehicleTypeLangs.stream().forEach(vehicleTypeLang -> vehicleType.setEnable(vehicleTypeLang.getEnable()));
        vehicleType.setLangs(vehicleTypeLangs);
        return vehicleType;
    }
}
