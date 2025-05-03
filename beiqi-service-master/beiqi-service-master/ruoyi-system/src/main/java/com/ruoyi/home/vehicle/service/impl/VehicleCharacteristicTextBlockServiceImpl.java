package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicTextBlock;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicTextBlockLang;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicTextBlockLangMapper;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicTextBlockMapper;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicTextBlockService;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicTextBlockHomeVo;
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
public class VehicleCharacteristicTextBlockServiceImpl implements VehicleCharacteristicTextBlockService {
    private VehicleCharacteristicTextBlockLangMapper vehicleCharacteristicTextBlockLangMapper;
    private VehicleCharacteristicTextBlockMapper vehicleCharacteristicTextBlockMapper;
    private LangService langService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock) {
        vehicleCharacteristicTextBlock.setCreateTime(new Date());
        vehicleCharacteristicTextBlockMapper.insert(vehicleCharacteristicTextBlock);
        for (VehicleCharacteristicTextBlockLang vehicleCharacteristicTextBlockLang : vehicleCharacteristicTextBlock.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleCharacteristicTextBlockLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicTextBlockLang.getLang());
            }
            vehicleCharacteristicTextBlockLang.setCreateTime(new Date());
            vehicleCharacteristicTextBlockLang.setTextBlockId(vehicleCharacteristicTextBlock.getId());
            vehicleCharacteristicTextBlockLangMapper.insert(vehicleCharacteristicTextBlockLang);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock) {
        vehicleCharacteristicTextBlock.setUpdateTime(new Date());
        vehicleCharacteristicTextBlockMapper.updateById(vehicleCharacteristicTextBlock);
        //先全部删除再全部新增
        LambdaQueryWrapper<VehicleCharacteristicTextBlockLang> vehicleCharacteristicTextBlockLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicTextBlockLangLambdaQueryWrapper.eq(VehicleCharacteristicTextBlockLang::getTextBlockId, vehicleCharacteristicTextBlock.getId());
        vehicleCharacteristicTextBlockLangMapper.delete(vehicleCharacteristicTextBlockLangLambdaQueryWrapper);
        for (VehicleCharacteristicTextBlockLang vehicleCharacteristicTextBlockLang : vehicleCharacteristicTextBlock.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleCharacteristicTextBlockLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicTextBlockLang.getLang());
            }
            vehicleCharacteristicTextBlockLang.setCreateTime(new Date());
            vehicleCharacteristicTextBlockLang.setTextBlockId(vehicleCharacteristicTextBlock.getId());
            vehicleCharacteristicTextBlockLangMapper.insert(vehicleCharacteristicTextBlockLang);
        }
    }

    @Override
    public void delete(Integer id) {
        vehicleCharacteristicTextBlockMapper.deleteById(id);
    }

    @Override
    public VehicleCharacteristicTextBlock getById(Integer id) {
        VehicleCharacteristicTextBlock vehicleCharacteristicTextBlock = vehicleCharacteristicTextBlockMapper.selectById(id);
        if (vehicleCharacteristicTextBlock == null) return null;
        LambdaQueryWrapper<VehicleCharacteristicTextBlockLang> vehicleCharacteristicTextBlockLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicTextBlockLangLambdaQueryWrapper.eq(VehicleCharacteristicTextBlockLang::getTextBlockId, id);
        List<VehicleCharacteristicTextBlockLang> vehicleCharacteristicTextBlockLangs = vehicleCharacteristicTextBlockLangMapper.selectList(vehicleCharacteristicTextBlockLangLambdaQueryWrapper);
        vehicleCharacteristicTextBlock.setLangs(vehicleCharacteristicTextBlockLangs);
        return vehicleCharacteristicTextBlock;
    }

    @Override
    public List<VehicleCharacteristicTextBlock> list(Integer vehicleClassId) {
        LambdaQueryWrapper<VehicleCharacteristicTextBlock> vehicleCharacteristicTextBlockLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicTextBlockLambdaQueryWrapper.eq(VehicleCharacteristicTextBlock::getVehicleClassId, vehicleClassId);
        List<VehicleCharacteristicTextBlock> vehicleCharacteristicGalleries = vehicleCharacteristicTextBlockMapper.selectList(vehicleCharacteristicTextBlockLambdaQueryWrapper);
        return vehicleCharacteristicGalleries.stream()
                .peek(vehicleCharacteristicTextBlock -> {
                    LambdaQueryWrapper<VehicleCharacteristicTextBlockLang> vehicleCharacteristicTextBlockLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    vehicleCharacteristicTextBlockLangLambdaQueryWrapper.eq(VehicleCharacteristicTextBlockLang::getTextBlockId, vehicleCharacteristicTextBlock.getId());
                    List<VehicleCharacteristicTextBlockLang> vehicleCharacteristicTextBlockLangs = vehicleCharacteristicTextBlockLangMapper.selectList(vehicleCharacteristicTextBlockLangLambdaQueryWrapper);
                    vehicleCharacteristicTextBlock.setLangs(vehicleCharacteristicTextBlockLangs);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleCharacteristicTextBlockHomeVo> listByLang(Integer vehicleClassId, String lang) {
        return list(vehicleClassId).stream()
                .map(vehicleCharacteristicTextBlock -> {
                    VehicleCharacteristicTextBlockLang vehicleCharacteristicTextBlockLang = null;
                    for (VehicleCharacteristicTextBlockLang vehicleCharacteristicTextBlockLangEntry : vehicleCharacteristicTextBlock.getLangs()) {
                        if (Objects.equals(vehicleCharacteristicTextBlockLangEntry.getLang(), lang))
                            vehicleCharacteristicTextBlockLang = vehicleCharacteristicTextBlockLangEntry;
                    }
                    if (vehicleCharacteristicTextBlockLang == null) return null;
                    VehicleCharacteristicTextBlockHomeVo vehicleCharacteristicTextBlockHomeVo = new VehicleCharacteristicTextBlockHomeVo();
                    vehicleCharacteristicTextBlockHomeVo.setSort(vehicleCharacteristicTextBlock.getSort());
                    vehicleCharacteristicTextBlockHomeVo.setTitle(vehicleCharacteristicTextBlockLang.getTitle());
                    vehicleCharacteristicTextBlockHomeVo.setDesc(vehicleCharacteristicTextBlockLang.getDesc());
                    return vehicleCharacteristicTextBlockHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(VehicleCharacteristicTextBlockHomeVo::getSort))
                .collect(Collectors.toList());
    }
}
