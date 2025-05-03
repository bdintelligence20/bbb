package com.ruoyi.home.vehicle.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.enums.VehicleCharacteristicType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicImgAndText;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicImgAndTextLang;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicImgAndTextLangMapper;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicImgAndTextMapper;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicImgAndTextService;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicImgAndTextHomeVo;
import com.ruoyi.website.service.LangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleCharacteristicImgAndTextServiceImpl implements VehicleCharacteristicImgAndTextService {
    private VehicleCharacteristicImgAndTextLangMapper vehicleCharacteristicImgAndTextLangMapper;
    private VehicleCharacteristicImgAndTextMapper vehicleCharacteristicImgAndTextMapper;
    private LangService langService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText) {
        vehicleCharacteristicImgAndText.setImgUrl(getEnImgUrl(vehicleCharacteristicImgAndText));
        vehicleCharacteristicImgAndText.setCreateTime(new Date());
        vehicleCharacteristicImgAndTextMapper.insert(vehicleCharacteristicImgAndText);
        for (VehicleCharacteristicImgAndTextLang vehicleCharacteristicImgAndTextLang : vehicleCharacteristicImgAndText.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleCharacteristicImgAndTextLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicImgAndTextLang.getLang());
            }
            vehicleCharacteristicImgAndTextLang.setCreateTime(new Date());
            vehicleCharacteristicImgAndTextLang.setImgAndTextId(vehicleCharacteristicImgAndText.getId());
            vehicleCharacteristicImgAndTextLangMapper.insert(vehicleCharacteristicImgAndTextLang);
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText) {
        vehicleCharacteristicImgAndText.setImgUrl(getEnImgUrl(vehicleCharacteristicImgAndText));
        vehicleCharacteristicImgAndText.setUpdateTime(new Date());
        vehicleCharacteristicImgAndTextMapper.updateById(vehicleCharacteristicImgAndText);
        //先全部删除再全部新增
        LambdaQueryWrapper<VehicleCharacteristicImgAndTextLang> vehicleCharacteristicImgAndTextLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicImgAndTextLangLambdaQueryWrapper.eq(VehicleCharacteristicImgAndTextLang::getImgAndTextId, vehicleCharacteristicImgAndText.getId());
        vehicleCharacteristicImgAndTextLangMapper.delete(vehicleCharacteristicImgAndTextLangLambdaQueryWrapper);
        for (VehicleCharacteristicImgAndTextLang vehicleCharacteristicImgAndTextLang : vehicleCharacteristicImgAndText.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleCharacteristicImgAndTextLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicImgAndTextLang.getLang());
            }
            vehicleCharacteristicImgAndTextLang.setCreateTime(new Date());
            vehicleCharacteristicImgAndTextLang.setImgAndTextId(vehicleCharacteristicImgAndText.getId());
            vehicleCharacteristicImgAndTextLangMapper.insert(vehicleCharacteristicImgAndTextLang);
        }
    }

    @Override
    public void delete(Integer id) {
        vehicleCharacteristicImgAndTextMapper.deleteById(id);
    }

    @Override
    public VehicleCharacteristicImgAndText getById(Integer id) {
        VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText = vehicleCharacteristicImgAndTextMapper.selectById(id);
        if (vehicleCharacteristicImgAndText == null) return null;
        LambdaQueryWrapper<VehicleCharacteristicImgAndTextLang> vehicleCharacteristicImgAndTextLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicImgAndTextLangLambdaQueryWrapper.eq(VehicleCharacteristicImgAndTextLang::getImgAndTextId, id);
        List<VehicleCharacteristicImgAndTextLang> vehicleCharacteristicImgAndTextLangs = vehicleCharacteristicImgAndTextLangMapper.selectList(vehicleCharacteristicImgAndTextLangLambdaQueryWrapper);
        vehicleCharacteristicImgAndText.setLangs(vehicleCharacteristicImgAndTextLangs);
        return vehicleCharacteristicImgAndText;
    }

    @Override
    public List<VehicleCharacteristicImgAndText> list(Integer vehicleClassId) {
        LambdaQueryWrapper<VehicleCharacteristicImgAndText> vehicleCharacteristicImgAndTextLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicImgAndTextLambdaQueryWrapper.eq(VehicleCharacteristicImgAndText::getVehicleClassId, vehicleClassId);
        List<VehicleCharacteristicImgAndText> vehicleCharacteristicGalleries = vehicleCharacteristicImgAndTextMapper.selectList(vehicleCharacteristicImgAndTextLambdaQueryWrapper);
        return vehicleCharacteristicGalleries.stream()
                .peek(vehicleCharacteristicImgAndText -> {
                    LambdaQueryWrapper<VehicleCharacteristicImgAndTextLang> vehicleCharacteristicImgAndTextLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    vehicleCharacteristicImgAndTextLangLambdaQueryWrapper.eq(VehicleCharacteristicImgAndTextLang::getImgAndTextId, vehicleCharacteristicImgAndText.getId());
                    List<VehicleCharacteristicImgAndTextLang> vehicleCharacteristicImgAndTextLangs = vehicleCharacteristicImgAndTextLangMapper.selectList(vehicleCharacteristicImgAndTextLangLambdaQueryWrapper);
                    vehicleCharacteristicImgAndText.setLangs(vehicleCharacteristicImgAndTextLangs);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleCharacteristicImgAndTextHomeVo> listByLang(Integer vehicleClassId, String lang) {
        return list(vehicleClassId).stream()
                .map(vehicleCharacteristicImgAndText -> {
                    VehicleCharacteristicImgAndTextLang vehicleCharacteristicImgAndTextLang = null;
                    for (VehicleCharacteristicImgAndTextLang vehicleCharacteristicImgAndTextLangEntry : vehicleCharacteristicImgAndText.getLangs()) {
                        if (Objects.equals(vehicleCharacteristicImgAndTextLangEntry.getLang(), lang))
                            vehicleCharacteristicImgAndTextLang = vehicleCharacteristicImgAndTextLangEntry;
                    }
                    if (vehicleCharacteristicImgAndTextLang == null) return null;
                    VehicleCharacteristicImgAndTextHomeVo vehicleCharacteristicImgAndTextHomeVo = new VehicleCharacteristicImgAndTextHomeVo();
                    vehicleCharacteristicImgAndTextHomeVo.setSort(vehicleCharacteristicImgAndText.getSort());
                    vehicleCharacteristicImgAndTextHomeVo.setTitle(vehicleCharacteristicImgAndTextLang.getTitle());
                    vehicleCharacteristicImgAndTextHomeVo.setImgUrl(vehicleCharacteristicImgAndTextLang.getImgUrl());
                    vehicleCharacteristicImgAndTextHomeVo.setType(VehicleCharacteristicType.IMG_AND_TEXT.getName());
                    vehicleCharacteristicImgAndTextHomeVo.setSubTitle(vehicleCharacteristicImgAndTextLang.getSubTitle());
                    vehicleCharacteristicImgAndTextHomeVo.setDesc(vehicleCharacteristicImgAndTextLang.getDesc());
                    vehicleCharacteristicImgAndTextHomeVo.setViewType(vehicleCharacteristicImgAndText.getViewType());
                    return vehicleCharacteristicImgAndTextHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(VehicleCharacteristicImgAndTextHomeVo::getSort))
                .collect(Collectors.toList());
    }

    private String getEnImgUrl(VehicleCharacteristicImgAndText vehicleCharacteristicImgAndText){
        List<VehicleCharacteristicImgAndTextLang> list = vehicleCharacteristicImgAndText.getLangs().stream().map(item -> {
            if (item.getLang().equals("en")) {
                return item;
            }
            return null;
        }).collect(Collectors.toList());

        if (CollUtil.isNotEmpty(list)){
            return list.get(0).getImgUrl();
        }
        return null;
    }
}
