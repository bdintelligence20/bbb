package com.ruoyi.home.testdrive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.home.testdrive.domain.Area;
import com.ruoyi.home.testdrive.domain.AreaLang;
import com.ruoyi.home.testdrive.mapper.AreaLangMapper;
import com.ruoyi.home.testdrive.mapper.AreaMapper;
import com.ruoyi.home.testdrive.service.AreaService;
import com.ruoyi.home.vo.testdrive.AreaHomeVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商家区域 service
 *
 * @author liuzhuoming
 */
@AllArgsConstructor
@Service
public class AreaServiceImpl implements AreaService {
    private AreaMapper areaMapper;
    private AreaLangMapper areaLangMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Area area) {
        area.setCreateTime(new Date());
        areaMapper.insert(area);
        for (AreaLang areaLang : area.getLangs()) {
            areaLang.setAreaId(area.getId());
            areaLang.setCreateTime(new Date());
            areaLangMapper.insert(areaLang);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Area area) {
        area.setUpdateTime(new Date());
        areaMapper.updateById(area);
        //先删除全部再新增全部
        LambdaQueryWrapper<AreaLang> sellerAreaLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sellerAreaLangLambdaQueryWrapper.eq(AreaLang::getAreaId, area.getId());
        areaLangMapper.delete(sellerAreaLangLambdaQueryWrapper);
        for (AreaLang areaLang : area.getLangs()) {
            areaLang.setAreaId(area.getId());
            areaLang.setCreateTime(new Date());
            areaLangMapper.insert(areaLang);
        }
    }

    @Override
    public void delete(Integer id) {
        areaMapper.deleteById(id);
    }

    @Override
    public List<Area> list(Integer type) {
        LambdaQueryWrapper<Area> areaLambdaQueryWrapper = new LambdaQueryWrapper<>();
        areaLambdaQueryWrapper.eq(type != null, Area::getType, type);
        List<Area> areas = areaMapper.selectList(areaLambdaQueryWrapper);
        return areas.stream()
                .map(area -> {
                    LambdaQueryWrapper<AreaLang> areaLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    areaLangLambdaQueryWrapper.eq(AreaLang::getAreaId, area.getId());
                    List<AreaLang> langs = areaLangMapper.selectList(areaLangLambdaQueryWrapper);
                    langs = langs.stream()
                            .filter(areaLang -> Objects.equals(areaLang.getLang().toLowerCase(Locale.ROOT), "en"))
                            .collect(Collectors.toList());
                    if (langs.isEmpty()) return null;
                    area.setLangs(langs);
                    return area;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Area::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public List<AreaHomeVo> listByLang(String lang, Integer type) {
        LambdaQueryWrapper<Area> areaLambdaQueryWrapper = new LambdaQueryWrapper<>();
        areaLambdaQueryWrapper.eq(type != null, Area::getType, type);
        List<Area> areas = areaMapper.selectList(areaLambdaQueryWrapper);
        return areas.stream()
                .map(area -> {
                    LambdaQueryWrapper<AreaLang> areaLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    areaLangLambdaQueryWrapper.eq(AreaLang::getAreaId, area.getId());
                    List<AreaLang> langs = areaLangMapper.selectList(areaLangLambdaQueryWrapper);
                    AreaLang areaLang = null;
                    for (AreaLang areaLangIterator : langs) {
                        if (Objects.equals(areaLangIterator.getLang(), lang))
                            areaLang = areaLangIterator;
                    }
                    if (areaLang == null) return null;
                    AreaHomeVo areaHomeVo = new AreaHomeVo();
                    areaHomeVo.setSort(area.getSort());
                    areaHomeVo.setId(area.getId());
                    areaHomeVo.setName(areaLang.getName());
                    return areaHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(AreaHomeVo::getSort))
                .collect(Collectors.toList());
    }

    @Override
    public Area getById(Integer id) {
        Area area = areaMapper.selectById(id);
        LambdaQueryWrapper<AreaLang> areaLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        areaLangLambdaQueryWrapper.eq(AreaLang::getAreaId, area.getId());
        List<AreaLang> langs = areaLangMapper.selectList(areaLangLambdaQueryWrapper);
        area.setLangs(langs);
        return area;
    }

    @Override
    public Area getByLang(Integer id, String lang) {
        Area area = areaMapper.selectById(id);
        if (area == null) return null;
        LambdaQueryWrapper<AreaLang> areaLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        areaLangLambdaQueryWrapper.eq(AreaLang::getAreaId, area.getId());
        List<AreaLang> langs = areaLangMapper.selectList(areaLangLambdaQueryWrapper);
        AreaLang areaLang = null;
        for (AreaLang areaLangIterator : langs) {
            if (Objects.equals(areaLangIterator.getLang(), lang))
                areaLang = areaLangIterator;
        }
        if (areaLang == null) return null;
        area.setLangs(Collections.singletonList(areaLang));
        return area;
    }
}
