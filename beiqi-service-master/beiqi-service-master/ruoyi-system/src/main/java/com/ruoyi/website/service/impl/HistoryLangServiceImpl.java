package com.ruoyi.website.service.impl;

import java.util.Arrays;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.HistoryLang;
import com.ruoyi.website.mapper.HistoryLangMapper;
import com.ruoyi.website.service.IHistoryLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 历史多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-09
 */
@Service
public class HistoryLangServiceImpl implements IHistoryLangService
{
    @Autowired
    private HistoryLangMapper historyLangMapper;

    /**
     * 查询历史多语言
     * 
     * @param id 历史多语言主键
     * @return 历史多语言
     */
    @Override
    public HistoryLang selectHistoryLangById(Long id)
    {
        return historyLangMapper.selectHistoryLangById(id);
    }

    /**
     * 查询历史多语言列表
     * 
     * @param historyLang 历史多语言
     * @return 历史多语言
     */
    @Override
    public List<HistoryLang> selectHistoryLangList(HistoryLang historyLang)
    {
        return historyLangMapper.selectHistoryLangList(historyLang);
    }

    /**
     * 新增历史多语言
     * 
     * @param historyLang 历史多语言
     * @return 结果
     */
    @Override
    public int insertHistoryLang(HistoryLang historyLang)
    {
        historyLang.setCreateTime(DateUtils.getNowDate());
        return historyLangMapper.insertHistoryLang(historyLang);
    }

    /**
     * 修改历史多语言
     * 
     * @param historyLang 历史多语言
     * @return 结果
     */
    @Override
    public int updateHistoryLang(HistoryLang historyLang)
    {
        historyLang.setUpdateTime(DateUtils.getNowDate());
        return historyLangMapper.updateHistoryLang(historyLang);
    }

    /**
     * 批量删除历史多语言
     * 
     * @param ids 需要删除的历史多语言主键
     * @return 结果
     */
    @Override
    public int deleteHistoryLangByIds(Long[] ids)
    {
        return historyLangMapper.deleteHistoryLangByIds(Arrays.asList(ids));
    }

    /**
     * 删除历史多语言信息
     * 
     * @param id 历史多语言主键
     * @return 结果
     */
    @Override
    public int deleteHistoryLangById(Long id)
    {
        return historyLangMapper.deleteHistoryLangById(id);
    }
}
