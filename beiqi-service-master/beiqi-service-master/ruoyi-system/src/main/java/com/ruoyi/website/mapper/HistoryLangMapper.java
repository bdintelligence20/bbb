package com.ruoyi.website.mapper;

import java.util.List;
import com.ruoyi.website.domain.HistoryLang;
import org.apache.ibatis.annotations.Param;

/**
 * 历史多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-09
 */
public interface HistoryLangMapper 
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
     * 删除历史多语言
     * 
     * @param id 历史多语言主键
     * @return 结果
     */
    public int deleteHistoryLangById(Long id);

    /**
     * 批量删除历史多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHistoryLangByIds(@Param("list") List<Long> ids);

    void batchInsert(@Param("list") List<HistoryLang> list);

    void deleteByHistoryId(@Param("list") List<Long> ids);
//    List<HistoryLang> selectByHistoryId(@Param("id") Long id);

    List<HistoryLang> selectList(HistoryLang historyLang);
}
