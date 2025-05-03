package com.ruoyi.home.contact.service;

import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.contact.domain.Country;
import com.ruoyi.home.vo.contact.CountryHomeVo;

import java.util.List;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
public interface CountryService {

    void insert(Country country);

    void update(Country country);

    void delete(Integer id);

    Country getById(Integer id);

    TableDataInfoT<Country> page(Integer pageNum, Integer pageSize);

    List<CountryHomeVo> listByLang(String lang, Integer areaId);
}
