package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.home.vehicle.domain.VehicleSpecificationImgAndText;
import com.ruoyi.home.vehicle.domain.VehicleSpecificationImgAndTextLang;
import com.ruoyi.home.vehicle.mapper.VehicleSpecificationImgAndTextLangMapper;
import com.ruoyi.home.vehicle.mapper.VehicleSpecificationImgAndTextMapper;
import com.ruoyi.home.vehicle.service.VehicleSpecificationImgAndTextService;
import com.ruoyi.home.vo.vehicle.VehicleSpecificationImgAndTextHomeVo;
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
 * 车型规格
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleSpecificationImgAndTextServiceImpl implements VehicleSpecificationImgAndTextService {
    private VehicleSpecificationImgAndTextLangMapper vehicleSpecificationImgAndTextLangMapper;
    private VehicleSpecificationImgAndTextMapper vehicleSpecificationImgAndTextMapper;
    private LangService langService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleSpecificationImgAndText vehicleSpecificationImgAndText) {
        vehicleSpecificationImgAndText.setCreateTime(new Date());
        vehicleSpecificationImgAndTextMapper.insert(vehicleSpecificationImgAndText);
        for (VehicleSpecificationImgAndTextLang vehicleSpecificationImgAndTextLang : vehicleSpecificationImgAndText.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleSpecificationImgAndTextLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleSpecificationImgAndTextLang.getLang());
            }
            vehicleSpecificationImgAndTextLang.setCreateTime(new Date());
            vehicleSpecificationImgAndTextLang.setImgAndTextId(vehicleSpecificationImgAndText.getId());
            vehicleSpecificationImgAndTextLangMapper.insert(vehicleSpecificationImgAndTextLang);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleSpecificationImgAndText vehicleSpecificationImgAndText) {
        vehicleSpecificationImgAndText.setUpdateTime(new Date());
        vehicleSpecificationImgAndTextMapper.updateById(vehicleSpecificationImgAndText);
        //先全部删除再全部新增
        LambdaQueryWrapper<VehicleSpecificationImgAndTextLang> vehicleSpecificationImgAndTextLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleSpecificationImgAndTextLangLambdaQueryWrapper.eq(VehicleSpecificationImgAndTextLang::getImgAndTextId, vehicleSpecificationImgAndText.getId());
        vehicleSpecificationImgAndTextLangMapper.delete(vehicleSpecificationImgAndTextLangLambdaQueryWrapper);
        for (VehicleSpecificationImgAndTextLang vehicleSpecificationImgAndTextLang : vehicleSpecificationImgAndText.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleSpecificationImgAndTextLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleSpecificationImgAndTextLang.getLang());
            }
            vehicleSpecificationImgAndTextLang.setCreateTime(new Date());
            vehicleSpecificationImgAndTextLang.setImgAndTextId(vehicleSpecificationImgAndText.getId());
            vehicleSpecificationImgAndTextLangMapper.insert(vehicleSpecificationImgAndTextLang);
        }
    }

    @Override
    public void delete(Integer id) {
        vehicleSpecificationImgAndTextMapper.deleteById(id);
    }

    @Override
    public VehicleSpecificationImgAndText getById(Integer id) {
        VehicleSpecificationImgAndText vehicleSpecificationImgAndText = vehicleSpecificationImgAndTextMapper.selectById(id);
        if (vehicleSpecificationImgAndText == null) return null;
        LambdaQueryWrapper<VehicleSpecificationImgAndTextLang> vehicleSpecificationImgAndTextLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleSpecificationImgAndTextLangLambdaQueryWrapper.eq(VehicleSpecificationImgAndTextLang::getImgAndTextId, id);
        List<VehicleSpecificationImgAndTextLang> vehicleSpecificationImgAndTextLangs = vehicleSpecificationImgAndTextLangMapper.selectList(vehicleSpecificationImgAndTextLangLambdaQueryWrapper);
        vehicleSpecificationImgAndText.setLangs(vehicleSpecificationImgAndTextLangs);
        return vehicleSpecificationImgAndText;
    }

    @Override
    public List<VehicleSpecificationImgAndText> list(Integer vehicleClassId) {
        LambdaQueryWrapper<VehicleSpecificationImgAndText> vehicleSpecificationImgAndTextLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleSpecificationImgAndTextLambdaQueryWrapper.eq(VehicleSpecificationImgAndText::getVehicleClassId, vehicleClassId);
        List<VehicleSpecificationImgAndText> vehicleCharacteristicGalleries = vehicleSpecificationImgAndTextMapper.selectList(vehicleSpecificationImgAndTextLambdaQueryWrapper);
        return vehicleCharacteristicGalleries.stream()
                .peek(vehicleSpecificationImgAndText -> {
                    LambdaQueryWrapper<VehicleSpecificationImgAndTextLang> vehicleSpecificationImgAndTextLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    vehicleSpecificationImgAndTextLangLambdaQueryWrapper.eq(VehicleSpecificationImgAndTextLang::getImgAndTextId, vehicleSpecificationImgAndText.getId());
                    List<VehicleSpecificationImgAndTextLang> vehicleSpecificationImgAndTextLangs = vehicleSpecificationImgAndTextLangMapper.selectList(vehicleSpecificationImgAndTextLangLambdaQueryWrapper);
                    vehicleSpecificationImgAndText.setLangs(vehicleSpecificationImgAndTextLangs);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleSpecificationImgAndTextHomeVo> listByLang(Integer vehicleClassId, String lang) {
        return list(vehicleClassId).stream()
                .map(vehicleSpecificationImgAndText -> {
                    VehicleSpecificationImgAndTextLang vehicleSpecificationImgAndTextLang = null;
                    for (VehicleSpecificationImgAndTextLang vehicleSpecificationImgAndTextLangEntry : vehicleSpecificationImgAndText.getLangs()) {
                        if (Objects.equals(vehicleSpecificationImgAndTextLangEntry.getLang(), lang))
                            vehicleSpecificationImgAndTextLang = vehicleSpecificationImgAndTextLangEntry;
                    }
                    if (vehicleSpecificationImgAndTextLang == null) return null;
                    VehicleSpecificationImgAndTextHomeVo vehicleSpecificationImgAndTextHomeVo = new VehicleSpecificationImgAndTextHomeVo();
                    vehicleSpecificationImgAndTextHomeVo.setSort(vehicleSpecificationImgAndText.getSort());
                    vehicleSpecificationImgAndTextHomeVo.setTitle(vehicleSpecificationImgAndTextLang.getTitle());
                    vehicleSpecificationImgAndTextHomeVo.setImgUrl(vehicleSpecificationImgAndText.getImgUrl());
                    vehicleSpecificationImgAndTextHomeVo.setDesc(vehicleSpecificationImgAndTextLang.getDesc());
                    return vehicleSpecificationImgAndTextHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(VehicleSpecificationImgAndTextHomeVo::getSort))
                .collect(Collectors.toList());
    }
}
