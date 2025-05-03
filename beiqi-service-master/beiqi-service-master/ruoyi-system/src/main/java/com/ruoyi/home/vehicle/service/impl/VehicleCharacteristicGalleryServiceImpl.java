package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.enums.VehicleCharacteristicType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGallery;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGalleryLang;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicGalleryLangMapper;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicGalleryMapper;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicGalleryService;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicGalleryHomeVo;
import com.ruoyi.website.service.LangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleCharacteristicGalleryServiceImpl implements VehicleCharacteristicGalleryService {
    private VehicleCharacteristicGalleryLangMapper vehicleCharacteristicGalleryLangMapper;
    private VehicleCharacteristicGalleryMapper vehicleCharacteristicGalleryMapper;
    private LangService langService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleCharacteristicGallery vehicleCharacteristicGallery) {
        vehicleCharacteristicGallery.setCreateTime(new Date());
        vehicleCharacteristicGalleryMapper.insert(vehicleCharacteristicGallery);
        for (VehicleCharacteristicGalleryLang vehicleCharacteristicGalleryLang : vehicleCharacteristicGallery.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleCharacteristicGalleryLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicGalleryLang.getLang());
            }
            vehicleCharacteristicGalleryLang.setCreateTime(new Date());
            vehicleCharacteristicGalleryLang.setGalleryId(vehicleCharacteristicGallery.getId());
            vehicleCharacteristicGalleryLangMapper.insert(vehicleCharacteristicGalleryLang);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleCharacteristicGallery vehicleCharacteristicGallery) {
        vehicleCharacteristicGallery.setUpdateTime(new Date());
        vehicleCharacteristicGalleryMapper.updateById(vehicleCharacteristicGallery);
        //先全部删除再全部新增
        LambdaQueryWrapper<VehicleCharacteristicGalleryLang> vehicleCharacteristicGalleryLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryLangLambdaQueryWrapper.eq(VehicleCharacteristicGalleryLang::getGalleryId, vehicleCharacteristicGallery.getId());
        vehicleCharacteristicGalleryLangMapper.delete(vehicleCharacteristicGalleryLangLambdaQueryWrapper);
        for (VehicleCharacteristicGalleryLang vehicleCharacteristicGalleryLang : vehicleCharacteristicGallery.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleCharacteristicGalleryLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicGalleryLang.getLang());
            }
            vehicleCharacteristicGalleryLang.setCreateTime(new Date());
            vehicleCharacteristicGalleryLang.setGalleryId(vehicleCharacteristicGallery.getId());
            vehicleCharacteristicGalleryLangMapper.insert(vehicleCharacteristicGalleryLang);
        }
    }

    @Override
    public void delete(Integer id) {
        vehicleCharacteristicGalleryMapper.deleteById(id);
    }

    @Override
    public VehicleCharacteristicGallery getById(Integer id) {
        VehicleCharacteristicGallery vehicleCharacteristicGallery = vehicleCharacteristicGalleryMapper.selectById(id);
        if (vehicleCharacteristicGallery == null) return null;
        LambdaQueryWrapper<VehicleCharacteristicGalleryLang> vehicleCharacteristicGalleryLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryLangLambdaQueryWrapper.eq(VehicleCharacteristicGalleryLang::getGalleryId, id);
        List<VehicleCharacteristicGalleryLang> vehicleCharacteristicGalleryLangs = vehicleCharacteristicGalleryLangMapper.selectList(vehicleCharacteristicGalleryLangLambdaQueryWrapper);
        vehicleCharacteristicGallery.setLangs(vehicleCharacteristicGalleryLangs);
        return vehicleCharacteristicGallery;
    }

    @Override
    public List<VehicleCharacteristicGallery> list(Integer vehicleClassId) {
        LambdaQueryWrapper<VehicleCharacteristicGallery> vehicleCharacteristicGalleryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryLambdaQueryWrapper.eq(VehicleCharacteristicGallery::getVehicleClassId, vehicleClassId);
        List<VehicleCharacteristicGallery> vehicleCharacteristicGalleries = vehicleCharacteristicGalleryMapper.selectList(vehicleCharacteristicGalleryLambdaQueryWrapper);
        return vehicleCharacteristicGalleries.stream()
                .peek(vehicleCharacteristicGallery -> {
                    LambdaQueryWrapper<VehicleCharacteristicGalleryLang> vehicleCharacteristicGalleryLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    vehicleCharacteristicGalleryLangLambdaQueryWrapper.eq(VehicleCharacteristicGalleryLang::getGalleryId, vehicleCharacteristicGallery.getId());
                    List<VehicleCharacteristicGalleryLang> vehicleCharacteristicGalleryLangs = vehicleCharacteristicGalleryLangMapper.selectList(vehicleCharacteristicGalleryLangLambdaQueryWrapper);
                    vehicleCharacteristicGallery.setLangs(vehicleCharacteristicGalleryLangs);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleCharacteristicGalleryHomeVo> listByLang(Integer vehicleClassId, String lang) {
        return list(vehicleClassId).stream()
                .map(vehicleCharacteristicGallery -> {
                    VehicleCharacteristicGalleryLang vehicleCharacteristicGalleryLang = null;
                    for (VehicleCharacteristicGalleryLang vehicleCharacteristicGalleryLangEntry : vehicleCharacteristicGallery.getLangs()) {
                        if (Objects.equals(vehicleCharacteristicGalleryLangEntry.getLang(), lang))
                            vehicleCharacteristicGalleryLang = vehicleCharacteristicGalleryLangEntry;
                    }
                    if (vehicleCharacteristicGalleryLang == null) return null;
                    VehicleCharacteristicGalleryHomeVo vehicleCharacteristicGalleryHomeVo = new VehicleCharacteristicGalleryHomeVo();
                    vehicleCharacteristicGalleryHomeVo.setSort(vehicleCharacteristicGallery.getSort());
                    vehicleCharacteristicGalleryHomeVo.setTitle(vehicleCharacteristicGalleryLang.getTitle());
                    vehicleCharacteristicGalleryHomeVo.setImgUrl(vehicleCharacteristicGallery.getImgUrl());
                    vehicleCharacteristicGalleryHomeVo.setType(VehicleCharacteristicType.GALLERY.getName());
                    return vehicleCharacteristicGalleryHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(VehicleCharacteristicGalleryHomeVo::getSort))
                .collect(Collectors.toList());
    }
}
