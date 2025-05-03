package com.ruoyi.home.contact.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.home.contact.domain.Country;
import com.ruoyi.home.contact.domain.CountryContactUrl;
import com.ruoyi.home.contact.domain.CountryLang;
import com.ruoyi.home.contact.mapper.CountryContactUrlMapper;
import com.ruoyi.home.contact.mapper.CountryLangMapper;
import com.ruoyi.home.contact.mapper.CountryMapper;
import com.ruoyi.home.contact.service.CountryService;
import com.ruoyi.home.vo.contact.CountryContactUrlHomeVo;
import com.ruoyi.home.vo.contact.CountryHomeVo;
import com.ruoyi.website.service.LangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private CountryLangMapper countryLangMapper;
    private CountryMapper countryMapper;
    private LangService langService;
    private CountryContactUrlMapper countryContactUrlMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(Country country) {
        country.setCreateTime(new Date());
        countryMapper.insert(country);
        for (CountryLang countryLang : country.getLangs()) {
            if (!langService.isAllowedLangCode(countryLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + countryLang.getLang());
            }
            countryLang.setCreateTime(new Date());
            countryLang.setCountryId(country.getId());
            countryLangMapper.insert(countryLang);
        }
        for (CountryContactUrl countryContactUrl : country.getUrls()) {
            countryContactUrl.setCountryId(country.getId());
            countryContactUrl.setCreateTime(new Date());
            countryContactUrlMapper.insert(countryContactUrl);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Country country) {
        country.setUpdateTime(new Date());
        countryMapper.updateById(country);
        //先全部删除再全部新增
        LambdaQueryWrapper<CountryLang> countryLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        countryLangLambdaQueryWrapper.eq(CountryLang::getCountryId, country.getId());
        countryLangMapper.delete(countryLangLambdaQueryWrapper);
        for (CountryLang countryLang : country.getLangs()) {
            if (!langService.isAllowedLangCode(countryLang.getLang())) {
                throw new ServiceException("不被允许的语言类型：" + countryLang.getLang());
            }
            countryLang.setCreateTime(new Date());
            countryLang.setCountryId(country.getId());
            countryLangMapper.insert(countryLang);
        }
        LambdaQueryWrapper<CountryContactUrl> countryContactUrlLambdaQueryWrapper = new LambdaQueryWrapper<>();
        countryContactUrlLambdaQueryWrapper.eq(CountryContactUrl::getCountryId, country.getId());
        countryContactUrlMapper.delete(countryContactUrlLambdaQueryWrapper);
        for (CountryContactUrl countryContactUrl : country.getUrls()) {
            countryContactUrl.setCountryId(country.getId());
            countryContactUrl.setCreateTime(new Date());
            countryContactUrlMapper.insert(countryContactUrl);
        }
    }

    @Override
    public void delete(Integer id) {
        countryMapper.deleteById(id);
    }

    @Override
    public Country getById(Integer id) {
        Country country = countryMapper.selectById(id);
        if (country == null) return null;
        LambdaQueryWrapper<CountryLang> countryLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
        countryLangLambdaQueryWrapper.eq(CountryLang::getCountryId, id);
        List<CountryLang> countryLangs = countryLangMapper.selectList(countryLangLambdaQueryWrapper);
        country.setLangs(countryLangs);
        LambdaQueryWrapper<CountryContactUrl> countryContactUrlLambdaQueryWrapper = new LambdaQueryWrapper<>();
        countryContactUrlLambdaQueryWrapper.eq(CountryContactUrl::getCountryId, country.getId());
        List<CountryContactUrl> countryContactUrls = countryContactUrlMapper.selectList(countryContactUrlLambdaQueryWrapper);
        country.setUrls(countryContactUrls);
        return country;
    }

    @Override
    public TableDataInfoT<Country> page(Integer pageNum, Integer pageSize) {
        Page<Country> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Country> countryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Country> countryPage = countryMapper.selectPage(page, countryLambdaQueryWrapper);
        List<Country> countries = countryPage.getRecords().stream()
                .map(country -> {
                    LambdaQueryWrapper<CountryLang> countryLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    countryLangLambdaQueryWrapper.eq(CountryLang::getCountryId, country.getId());
                    List<CountryLang> countryLangs = countryLangMapper.selectList(countryLangLambdaQueryWrapper);
                    countryLangs = countryLangs.stream()
                            .filter(countryLang -> Objects.equals(countryLang.getLang().toLowerCase(Locale.ROOT), "en"))
                            .collect(Collectors.toList());
                    if (countryLangs.isEmpty()) return null;
                    country.setLangs(countryLangs);
                    LambdaQueryWrapper<CountryContactUrl> countryContactUrlLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    countryContactUrlLambdaQueryWrapper.eq(CountryContactUrl::getCountryId, country.getId());
                    List<CountryContactUrl> countryContactUrls = countryContactUrlMapper.selectList(countryContactUrlLambdaQueryWrapper);
                    country.setUrls(countryContactUrls);
                    return country;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Country::getSort))
                .collect(Collectors.toList());
        return new TableDataInfoT<>(countryPage.getTotal(), countries);
    }

    @Override
    public List<CountryHomeVo> listByLang(String lang, Integer areaId) {
        LambdaQueryWrapper<Country> countryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Country> countries = countryMapper.selectList(countryLambdaQueryWrapper);
        countries = countries.stream()
                .peek(country -> {
                    LambdaQueryWrapper<CountryLang> countryLangLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    countryLangLambdaQueryWrapper.eq(CountryLang::getCountryId, country.getId());
                    List<CountryLang> countryLangs = countryLangMapper.selectList(countryLangLambdaQueryWrapper);
                    country.setLangs(countryLangs);
                    LambdaQueryWrapper<CountryContactUrl> countryContactUrlLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    countryContactUrlLambdaQueryWrapper.eq(CountryContactUrl::getCountryId, country.getId());
                    List<CountryContactUrl> countryContactUrls = countryContactUrlMapper.selectList(countryContactUrlLambdaQueryWrapper);
                    country.setUrls(countryContactUrls);
                })
                .sorted(Comparator.comparingInt(Country::getSort))
                .collect(Collectors.toList());
        return countries.stream()
                .filter(country -> areaId == null || Objects.equals(areaId, country.getAreaId()))
                .map(country -> {
                    CountryLang countryLang = null;
                    CountryLang countryLangEn = null;
                    for (CountryLang countryLangEntry : country.getLangs()) {
                        if (Objects.equals(countryLangEntry.getLang(), lang))
                            countryLang = countryLangEntry;
                        if (Objects.equals(countryLangEntry.getLang(), "en"))
                            countryLangEn = countryLangEntry;
                    }
                    if (countryLang == null || countryLangEn == null) return null;
                    LambdaQueryWrapper<CountryContactUrl> countryContactUrlLambdaQueryWrapper = new LambdaQueryWrapper<>();
                    countryContactUrlLambdaQueryWrapper.eq(CountryContactUrl::getCountryId, country.getId());
                    List<CountryContactUrl> countryContactUrls = countryContactUrlMapper.selectList(countryContactUrlLambdaQueryWrapper);
                    List<CountryContactUrlHomeVo> countryContactUrlHomeVos = countryContactUrls.stream()
                            .map(countryContactUrl -> {
                                CountryContactUrlHomeVo countryContactUrlHomeVo = new CountryContactUrlHomeVo();
                                countryContactUrlHomeVo.setUrl(countryContactUrl.getUrl());
                                countryContactUrlHomeVo.setName(countryContactUrl.getName());
                                countryContactUrlHomeVo.setSort(countryContactUrl.getSort());
                                return countryContactUrlHomeVo;
                            })
                            .filter(countryContactUrl -> StringUtils.isNotEmpty(countryContactUrl.getUrl()))
                            .sorted(Comparator.comparingInt(CountryContactUrlHomeVo::getSort))
                            .collect(Collectors.toList());
                    CountryHomeVo countryHomeVo = new CountryHomeVo();
                    countryHomeVo.setSort(country.getSort());
                    countryHomeVo.setName(countryLang.getName());
                    countryHomeVo.setPosition(countryLangEn.getName()
                            .replace(" ", "-")
                            .replace("(", "-")
                            .replace(")", "")
                            .toUpperCase(Locale.ROOT));
                    countryHomeVo.setFlagImgUrl(country.getFlagImgUrl());
                    countryHomeVo.setUrls(countryContactUrlHomeVos);
                    return countryHomeVo;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(CountryHomeVo::getSort))
                .collect(Collectors.toList());
    }
}
