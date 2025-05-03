package com.ruoyi.website.service;

import com.ruoyi.website.domain.History;
import com.ruoyi.website.domain.HistoryLang;
import com.ruoyi.website.domain.vo.HistoryLangVO;

import java.util.List;

/**
 * 历史Service接口
 * 
 * @author thh
 * @date 2022-06-09
 */
public interface IHistoryService 
{
    /**
     * 查询历史
     * 
     * @param id 历史主键
     * @return 历史
     */
    public List<HistoryLangVO> selectHistoryById(Long id);

    /**
     * 查询历史列表
     * 
     * @param history 历史
     * @return 历史集合
     */
    public List<History> selectHistoryList(History history);

    /**
     * 新增历史
     * 
     * @param historyLangs 历史
     * @return 结果
     */
    public int insertHistory(List<HistoryLang> historyLangs);

    /**
     * 修改历史
     * 
     * @param historyLangs 历史
     * @return 结果
     */
    public int updateHistory(List<HistoryLang> historyLangs);

    /**
     * 批量删除历史
     * 
     * @param ids 需要删除的历史主键集合
     * @return 结果
     */
    public int deleteHistoryByIds(List<Long> ids);

    /**
     * 删除历史信息
     * 
     * @param id 历史主键
     * @return 结果
     */
    public int deleteHistoryById(Long id);

    List<History> getHistoryList(String lang);
}
