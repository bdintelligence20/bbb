package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.JoinUsAreaLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface JoinUsAreaLangMapper 
{
    /**
     * 查询工作地点多语言
     * 
     * @param id 工作地点多语言主键
     * @return 工作地点多语言
     */
    public JoinUsAreaLang selectJoinUsAreaLangById(Long id);

    /**
     * 查询工作地点多语言列表
     * 
     * @param joinUsAreaLang 工作地点多语言
     * @return 工作地点多语言集合
     */
    public List<JoinUsAreaLang> selectJoinUsAreaLangList(JoinUsAreaLang joinUsAreaLang);

    /**
     * 新增工作地点多语言
     * 
     * @param joinUsAreaLang 工作地点多语言
     * @return 结果
     */
    public int insertJoinUsAreaLang(JoinUsAreaLang joinUsAreaLang);

    /**
     * 修改工作地点多语言
     * 
     * @param joinUsAreaLang 工作地点多语言
     * @return 结果
     */
    public int updateJoinUsAreaLang(JoinUsAreaLang joinUsAreaLang);

    /**
     * 删除工作地点多语言
     * 
     * @param id 工作地点多语言主键
     * @return 结果
     */
    public int deleteJoinUsAreaLangById(Long id);

    /**
     * 批量删除工作地点多语言
     * @return 结果
     */
    public int deleteJoinUsAreaLangByIds(@Param("list") List<Long> list);

    void batchInsert(@Param("list") List<JoinUsAreaLang> list);
}
