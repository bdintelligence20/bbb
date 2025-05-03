package com.ruoyi.website.service.impl;

import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysDictTypeService;
import com.ruoyi.website.service.LangService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 网站支持的多语言类型
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
public class LangServiceImpl implements LangService {
    private ISysDictTypeService sysDictTypeService;

    private List<String> listLangCode() {
        //从字典中获取键值（即语言编码
        List<SysDictData> langs = sysDictTypeService.selectDictDataByType("website_language");
        return langs.stream()
                .sorted(Comparator.comparingLong(SysDictData::getDictSort))
                .map(SysDictData::getDictValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAllowedLangCode(String langCode) {
        return StringUtils.isNotEmpty(langCode) && listLangCode().contains(langCode);
    }
}
