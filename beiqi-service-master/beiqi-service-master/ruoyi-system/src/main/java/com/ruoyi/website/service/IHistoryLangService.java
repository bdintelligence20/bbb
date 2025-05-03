package com.ruoyi.website.service;

import com.ruoyi.website.domain.HistoryLang;

import java.util.List;

/**
 * 历史多语言Service接口
 * 
 * @author thh
 * @date 2022-06-09
 */
public interface IHistoryLangService 
{
    /**
     * 查询历史多语言
     * 
     * @param id 历史多语言主键
     * @return 历史多语言
     */
    public HistoryLang selectHistoryLangById(Long id);

    /**
     * 查询历史多语言列表
     * 
     * @param historyLang 历史多语言
     * @return 历史多语言集合
     */
    public List<HistoryLang> selectHistoryLangList(HistoryLang historyLang);

    /**
     * 新增历史多语言
     * 
     * @param historyLang 历史多语言
     * @return 结果
     */
    public int insertHistoryLang(HistoryLang historyLang);

    /**
     * 修改历史多语言
     * 
     * @param historyLang 历史多语言
     * @return 结果
     */
    public int updateHistoryLang(HistoryLang historyLang);

    /**
     * 批量删除历史多语言
     * 
     * @param ids 需要删除的历史多语言主键集合
     * @return 结果
     */
    public int deleteHistoryLangByIds(Long[] ids);

    /**
     * 删除历史多语言信息
     * 
     * @param id 历史多语言主键
     * @return 结果
     */
    public int deleteHistoryLangById(Long id);
}
