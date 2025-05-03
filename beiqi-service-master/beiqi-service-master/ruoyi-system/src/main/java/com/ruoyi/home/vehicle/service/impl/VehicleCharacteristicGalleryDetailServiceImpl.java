package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.enums.VehicleCharacteristicType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGalleryDetail;
import com.ruoyi.home.vehicle.domain.VehicleCharacteristicGalleryDetailLang;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicGalleryDetailLangMapper;
import com.ruoyi.home.vehicle.mapper.VehicleCharacteristicGalleryDetailMapper;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicGalleryDetailService;
import com.ruoyi.home.vehicle.service.VehicleCharacteristicGalleryService;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicGalleryDetailHomeVo;
import com.ruoyi.home.vo.vehicle.VehicleCharacteristicGalleryHomeVo;
import com.ruoyi.website.service.LangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleCharacteristicGalleryDetailServiceImpl implements VehicleCharacteristicGalleryDetailService {
    private VehicleCharacteristicGalleryDetailLangMapper vehicleCharacteristicGalleryDetailLangMapper;
    private VehicleCharacteristicGalleryDetailMapper vehicleCharacteristicGalleryDetailMapper;
    private LangService langService;
    private VehicleCharacteristicGalleryService vehicleCharacteristicGalleryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(VehicleCharacteristicGalleryDetail vehicleCharacteristicGalleryDetail) {
        LambdaQueryWrapper<VehicleCharacteristicGalleryDetail> vehicleCharacteristicGalleryDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryDetailLambdaQueryWrapper.eq(VehicleCharacteristicGalleryDetail::getVehicleClassId, vehicleCharacteristicGalleryDetail.getVehicleClassId());
        VehicleCharacteristicGalleryDetail vehicleCharacteristicGalleryDetailDb = vehicleCharacteristicGalleryDetailMapper.selectOne(vehicleCharacteristicGalleryDetailLambdaQueryWrapper);
        if (vehicleCharacteristicGalleryDetailDb == null) {
            vehicleCharacteristicGalleryDetail.setCreateTime(new Date());
            vehicleCharacteristicGalleryDetailMapper.insert(vehicleCharacteristicGalleryDetail);//先全部删除再全部新增
            LambdaQueryWrapper<VehicleCharacteristicGalleryDetailLang> vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
            vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper.eq(VehicleCharacteristicGalleryDetailLang::getGalleryDetailId, vehicleCharacteristicGalleryDetail.getId());
            vehicleCharacteristicGalleryDetailLangMapper.delete(vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper);
            for (VehicleCharacteristicGalleryDetailLang vehicleCharacteristicGalleryDetailLang : vehicleCharacteristicGalleryDetail.getLangs()) {
                if (!langService.isAllowedLangCode(vehicleCharacteristicGalleryDetailLang.getLang())) {
                    throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicGalleryDetailLang.getLang());
                }
                vehicleCharacteristicGalleryDetailLang.setCreateTime(new Date());
                vehicleCharacteristicGalleryDetailLang.setGalleryDetailId(vehicleCharacteristicGalleryDetail.getId());
                vehicleCharacteristicGalleryDetailLangMapper.insert(vehicleCharacteristicGalleryDetailLang);
            }
        } else {
            vehicleCharacteristicGalleryDetail.setUpdateTime(new Date());
            vehicleCharacteristicGalleryDetailMapper.updateById(vehicleCharacteristicGalleryDetail);
            //先全部删除再全部新增
            LambdaQueryWrapper<VehicleCharacteristicGalleryDetailLang> vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
            vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper.eq(VehicleCharacteristicGalleryDetailLang::getGalleryDetailId, vehicleCharacteristicGalleryDetailDb.getId());
            vehicleCharacteristicGalleryDetailLangMapper.delete(vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper);
            for (VehicleCharacteristicGalleryDetailLang vehicleCharacteristicGalleryDetailLang : vehicleCharacteristicGalleryDetail.getLangs()) {
                if (!langService.isAllowedLangCode(vehicleCharacteristicGalleryDetailLang.getLang())) {
                    throw new ServiceException("不被允许的语言类型：" + vehicleCharacteristicGalleryDetailLang.getLang());
                }
                vehicleCharacteristicGalleryDetailLang.setCreateTime(new Date());
                vehicleCharacteristicGalleryDetailLang.setGalleryDetailId(vehicleCharacteristicGalleryDetailDb.getId());
                vehicleCharacteristicGalleryDetailLangMapper.insert(vehicleCharacteristicGalleryDetailLang);
            }
        }
    }

    @Override
    public VehicleCharacteristicGalleryDetail getByVehicleClassId(Integer vehicleClassId) {
        LambdaQueryWrapper<VehicleCharacteristicGalleryDetail> vehicleCharacteristicGalleryDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryDetailLambdaQueryWrapper.eq(VehicleCharacteristicGalleryDetail::getVehicleClassId, vehicleClassId);
        VehicleCharacteristicGalleryDetail vehicleCharacteristicGalleryDetail = vehicleCharacteristicGalleryDetailMapper.selectOne(vehicleCharacteristicGalleryDetailLambdaQueryWrapper);
        if (vehicleCharacteristicGalleryDetail == null) return null;
        LambdaQueryWrapper<VehicleCharacteristicGalleryDetailLang> vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper.eq(VehicleCharacteristicGalleryDetailLang::getGalleryDetailId, vehicleCharacteristicGalleryDetail.getId());
        List<VehicleCharacteristicGalleryDetailLang> vehicleCharacteristicGalleryDetailLangs = vehicleCharacteristicGalleryDetailLangMapper.selectList(vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper);
        vehicleCharacteristicGalleryDetail.setLangs(vehicleCharacteristicGalleryDetailLangs);
        return vehicleCharacteristicGalleryDetail;
    }

    @Override
    public VehicleCharacteristicGalleryDetailHomeVo getByLang(Integer vehicleClassId, String lang) {
        LambdaQueryWrapper<VehicleCharacteristicGalleryDetail> vehicleCharacteristicGalleryDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryDetailLambdaQueryWrapper.eq(VehicleCharacteristicGalleryDetail::getVehicleClassId, vehicleClassId);
        VehicleCharacteristicGalleryDetail vehicleCharacteristicGalleryDetail = vehicleCharacteristicGalleryDetailMapper.selectOne(vehicleCharacteristicGalleryDetailLambdaQueryWrapper);
        if (vehicleCharacteristicGalleryDetail == null) return null;
        LambdaQueryWrapper<VehicleCharacteristicGalleryDetailLang> vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper.eq(VehicleCharacteristicGalleryDetailLang::getGalleryDetailId, vehicleCharacteristicGalleryDetail.getId());
        List<VehicleCharacteristicGalleryDetailLang> vehicleCharacteristicGalleryDetailLangs = vehicleCharacteristicGalleryDetailLangMapper.selectList(vehicleCharacteristicGalleryDetailLangLambdaQueryWrapper);
        vehicleCharacteristicGalleryDetail.setLangs(vehicleCharacteristicGalleryDetailLangs);
        VehicleCharacteristicGalleryDetailLang vehicleCharacteristicGalleryDetailLang = null;
        for (VehicleCharacteristicGalleryDetailLang vehicleCharacteristicGalleryDetailLangEntry : vehicleCharacteristicGalleryDetail.getLangs()) {
            if (Objects.equals(vehicleCharacteristicGalleryDetailLangEntry.getLang(), lang))
                vehicleCharacteristicGalleryDetailLang = vehicleCharacteristicGalleryDetailLangEntry;
        }
        if (vehicleCharacteristicGalleryDetailLang == null) return null;

        List<VehicleCharacteristicGalleryHomeVo> imgs = vehicleCharacteristicGalleryService.listByLang(vehicleClassId, lang);
        if (imgs.isEmpty()) return null;

        VehicleCharacteristicGalleryDetailHomeVo vehicleCharacteristicGalleryDetailHomeVo = new VehicleCharacteristicGalleryDetailHomeVo();
        vehicleCharacteristicGalleryDetailHomeVo.setSort(vehicleCharacteristicGalleryDetail.getSort());
        vehicleCharacteristicGalleryDetailHomeVo.setTitle(vehicleCharacteristicGalleryDetailLang.getTitle());
        vehicleCharacteristicGalleryDetailHomeVo.setDesc(vehicleCharacteristicGalleryDetailLang.getDesc());
        vehicleCharacteristicGalleryDetailHomeVo.setImgs(imgs);
        vehicleCharacteristicGalleryDetailHomeVo.setType(VehicleCharacteristicType.GALLERY.getName());
        return vehicleCharacteristicGalleryDetailHomeVo;
    }
}
