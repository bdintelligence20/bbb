package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.History;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 历史Mapper接口
 * 
 * @author thh
 * @date 2022-06-09
 */
public interface HistoryMapper 
{
    /**
     * 查询历史
     * 
     * @param id 历史主键
     * @return 历史
     */
    public History selectHistoryById(Long id);

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
     * @param history 历史
     * @return 结果
     */
    public int insertHistory(History history);

    /**
     * 修改历史
     * 
     * @param history 历史
     * @return 结果
     */
    public int updateHistory(History history);

    /**
     * 删除历史
     * 
     * @param id 历史主键
     * @return 结果
     */
    public int deleteHistoryById(Long id);

    /**
     * 批量删除历史
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHistoryByIds(@Param("list") List<Long> ids);
}
