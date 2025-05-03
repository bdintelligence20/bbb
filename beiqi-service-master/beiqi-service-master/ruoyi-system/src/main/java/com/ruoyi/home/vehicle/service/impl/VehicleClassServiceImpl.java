package com.ruoyi.home.vehicle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.home.vehicle.domain.VehicleClass;
import com.ruoyi.home.vehicle.domain.VehicleClassLang;
import com.ruoyi.home.vehicle.domain.VehicleColor;
import com.ruoyi.home.vehicle.domain.VehicleType;
import com.ruoyi.home.vehicle.mapper.VehicleClassLangMapper;
import com.ruoyi.home.vehicle.mapper.VehicleClassMapper;
import com.ruoyi.home.vehicle.service.VehicleClassService;
import com.ruoyi.home.vehicle.service.VehicleColorService;
import com.ruoyi.home.vehicle.service.VehicleTypeService;
import com.ruoyi.home.vo.vehicle.VehicleClassHomeVo;
import com.ruoyi.home.vo.vehicle.VehicleClassSummaryHomeVo;
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
 * 车型
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class VehicleClassServiceImpl implements VehicleClassService {
    private VehicleClassMapper vehicleClassMapper;
    private VehicleClassLangMapper vehicleClassLangMapper;
    private VehicleColorService vehicleColorService;
    private LangService langService;
    private VehicleTypeService vehicleTypeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(VehicleClass vehicleClass) {
        vehicleClass.setCreateTime(new Date());
        vehicleClassMapper.insert(vehicleClass);
        for (VehicleClassLang vehicleClassLang : vehicleClass.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleClassLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleClassLang.getLang());
            }
            vehicleClassLang.setCreateTime(new Date());
            vehicleClassLang.setVehicleClassId(vehicleClass.getId());
            vehicleClassLangMapper.insert(vehicleClassLang);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(VehicleClass vehicleClass) {
        vehicleClass.setUpdateTime(new Date());
        vehicleClassMapper.updateById(vehicleClass);
        //删除所有再新增所有
        LambdaQueryWrapper<VehicleClassLang> vehicleClassLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        vehicleClassLangLambdaQueryWrapper.eq(VehicleClassLang::getVehicleClassId, vehicleClass.getId());
        vehicleClassLangMapper.delete(vehicleClassLangLambdaQueryWrapper);
        for (VehicleClassLang vehicleClassLang : vehicleClass.getLangs()) {
            if (!langService.isAllowedLangCode(vehicleClassLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + vehicleClassLang.getLang());
            }
            vehicleClassLang.setCreateTime(new Date());
            vehicleClassLang.setVehicleClassId(vehicleClass.getId());
            vehicleClassLangMapper.insert(vehicleClassLang);
        }
    }

    @Override
    public void delete(Integer id) {
        vehicleClassMapper.deleteById(id);
    }

    @Override
    public VehicleClass get(Integer id) {
        VehicleClass vehicleClass = vehicleClassMapper.selectById(id);
        if (vehicleClass == null) return null;
        LambdaQueryWrapper<VehicleClassLang> vehicleClassLangLambdaQuery = new LambdaQueryWrapper<>();
        vehicleClassLangLambdaQuery.eq(VehicleClassLang::getVehicleClassId, id);
        List<VehicleClassLang> vehicleClassLangs = vehicleClassLangMapper.selectList(vehicleClassLangLambdaQuery);
        vehicleClass.setLangs(vehicleClassLangs);
        VehicleType vehicleType = vehicleTypeService.get(vehicleClass.getVehicleTypeId());
        vehicleClass.setType(vehicleType);
        return vehicleClass;
    }

    @Override
    public List<VehicleClass> listByTypeId(Integer typeId) {
        LambdaQueryWrapper<VehicleClass> vehicleClassLambdaQuery = new LambdaQueryWrapper<>();
        vehicleClassLambdaQuery.eq(typeId != null, VehicleClass::getVehicleTypeId, typeId);
        List<VehicleClass> vehicleClasses = vehicleClassMapper.selectList(vehicleClassLambdaQuery);
        return vehicleClasses.stream()
                .peek(vehicleClass -> {
                    LambdaQueryWrapper<VehicleClassLang> vehicleClassLangLambdaQuery = new LambdaQueryWrapper<>();
                    vehicleClassLangLambdaQuery.eq(VehicleClassLang::getVehicleClassId, vehicleClass.getId());
                    List<VehicleClassLang> vehicleClassLangs = vehicleClassLangMapper.selectList(vehicleClassLangLambdaQuery);
                    vehicleClass.setLangs(vehicleClassLangs);
                    VehicleType vehicleType = vehicleTypeService.get(vehicleClass.getVehicleTypeId());
                    vehicleClass.setType(vehicleType);
                })
                .collect(Collectors.toList());
    }

    @Override
    public TableDataInfoT<VehicleClassSummaryHomeVo> pageByLang(String lang, String keyword, Integer pageNum, Integer pageSize) {
        List<VehicleClass> vehicleClasses = listByTypeId(null);
        List<VehicleClassSummaryHomeVo> vehicleClassSummaryHomeVos = vehicleClasses.stream()
                .map(vehicleClass -> {
                    if (vehicleClass == null) return null;
                    List<VehicleClassLang> langs = vehicleClass.getLangs();
                    VehicleClassLang vehicleClassLang = null;
                    for (VehicleClassLang vehicleClassLangIterator : langs) {
                        if (Objects.equals(vehicleClassLangIterator.getLang(), lang)) {
                            vehicleClassLang = vehicleClassLangIterator;
                            break;
                        }
                    }
                    if (vehicleClassLang == null) return null;
                    VehicleClassSummaryHomeVo vehicleClassSummaryHomeVo = BeanUtils.copyBeanProp(vehicleClass, VehicleClassSummaryHomeVo.class);
                    vehicleClassSummaryHomeVo.setTitle(vehicleClassLang.getTitle());
                    vehicleClassSummaryHomeVo.setSubTitle(vehicleClassLang.getSubTitle());
                    vehicleClassSummaryHomeVo.setDesc(vehicleClassLang.getDesc());
                    return vehicleClassSummaryHomeVo;
                })
                .filter(Objects::nonNull)
                .filter(vehicleClassSummaryHomeVo -> {
                    String title = vehicleClassSummaryHomeVo.getTitle();
                    String subTitle = vehicleClassSummaryHomeVo.getSubTitle();
                    String desc = vehicleClassSummaryHomeVo.getDesc();
                    return (title != null && title.contains(keyword))
                            || (subTitle != null && subTitle.contains(keyword))
                            || (desc != null && desc.contains(keyword));
                })
                .collect(Collectors.toList());

        List<VehicleClassSummaryHomeVo> pageResult = vehicleClassSummaryHomeVos.stream()
                .skip((long) pageSize * (pageNum - 1))
                .limit(pageSize)
                .collect(Collectors.toList());
        TableDataInfoT<VehicleClassSummaryHomeVo> tableDataInfoT = new TableDataInfoT<>();
        tableDataInfoT.setRows(pageResult);
        tableDataInfoT.setTotal(vehicleClassSummaryHomeVos.size());
        return tableDataInfoT;
    }

    @Override
    public VehicleClassHomeVo getByLang(Integer id, String lang) {
        VehicleClass vehicleClass = this.get(id);
        if (vehicleClass == null) return null;
        List<VehicleClassLang> langs = vehicleClass.getLangs();
        VehicleClassLang vehicleClassLang = null;
        for (VehicleClassLang vehicleClassLangIterator : langs) {
            if (Objects.equals(vehicleClassLangIterator.getLang(), lang)) {
                vehicleClassLang = vehicleClassLangIterator;
                break;
            }
        }
        if (vehicleClassLang == null) return null;
        if (vehicleClassLang.getEnable() != 1) return null;

        VehicleClassHomeVo vehicleClassHomeVo = BeanUtils.copyBeanProp(vehicleClass, VehicleClassHomeVo.class);
        vehicleClassHomeVo.setTitle(vehicleClassLang.getTitle());
        vehicleClassHomeVo.setSubTitle(vehicleClassLang.getSubTitle());
        vehicleClassHomeVo.setDesc(vehicleClassLang.getDesc());
        vehicleClassHomeVo.setSpecificationsPDFTitle(vehicleClassLang.getSpecificationsPDFTitle());
        vehicleClassHomeVo.setSpecificationsPDFDesc(vehicleClassLang.getSpecificationsPDFDesc());
        vehicleClassHomeVo.setSpecificationsPDFUrl(vehicleClassLang.getSpecificationsPDFUrl());
        vehicleClassHomeVo.setVrViewUrl(vehicleClassLang.getVrViewUrl());
        return vehicleClassHomeVo;
    }

    @Override
    public List<VehicleClassSummaryHomeVo> listClass(String lang, Integer typeId, Boolean presentation) {
        List<VehicleClass> vehicleClasses = this.listByTypeId(typeId);
        return vehicleClasses.stream()
                .map(vehicleClass -> {
                    if (vehicleClass == null) return null;
                    List<VehicleClassLang> langs = vehicleClass.getLangs();
                    VehicleClassLang vehicleClassLang = null;
                    for (VehicleClassLang vehicleClassLangIterator : langs) {
                        if (Objects.equals(vehicleClassLangIterator.getLang(), lang)) {
                            vehicleClassLang = vehicleClassLangIterator;
                            break;
                        }
                    }
                    if (vehicleClassLang == null) return null;
                    if (!presentation) {
                        if (vehicleClassLang.getEnable() != 1) return null;
                    }

                    List<VehicleColor> vehicleColors = vehicleColorService.list(vehicleClass.getId());

                    VehicleClassSummaryHomeVo vehicleClassSummaryHomeVo = new VehicleClassSummaryHomeVo();
                    vehicleClassSummaryHomeVo.setImgUrl(vehicleClass.getImgUrl());
                    vehicleClassSummaryHomeVo.setTitle(vehicleClassLang.getTitle());
                    vehicleClassSummaryHomeVo.setSubTitle(vehicleClassLang.getSubTitle());
                    vehicleClassSummaryHomeVo.setDesc(vehicleClassLang.getDesc());
                    vehicleClassSummaryHomeVo.setId(vehicleClass.getId());
                    vehicleClassSummaryHomeVo.setSort(vehicleClass.getSort());
                    vehicleClassSummaryHomeVo.setImgVehicleUrl(vehicleClass.getImgVehicleUrl());
                    vehicleClassSummaryHomeVo.setVrUrl(vehicleClassLang.getVrViewUrl());
                    if (!vehicleColors.isEmpty())
                        vehicleClassSummaryHomeVo.setColorImgUrl(vehicleColors.get(0).getImgUrl());
                    return vehicleClassSummaryHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(VehicleClassSummaryHomeVo::getSort))
                .collect(Collectors.toList());
    }

}
