package com.ruoyi.home.testdrive.service;

import com.ruoyi.home.testdrive.domain.Area;
import com.ruoyi.home.vo.testdrive.AreaHomeVo;

import java.util.List;

/**
 * 商家区域 service
 *
 * @author liuzhuoming
 */
public interface AreaService {

    void insert(Area area);

    void update(Area area);

    void delete(Integer id);

    List<Area> list(Integer type);

    List<AreaHomeVo> listByLang(String lang, Integer type);

    Area getById(Integer id);

    Area getByLang(Integer id, String lang);
}
