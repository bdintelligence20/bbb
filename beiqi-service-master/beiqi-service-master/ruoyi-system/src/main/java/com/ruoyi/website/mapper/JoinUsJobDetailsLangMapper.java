package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.JoinUsJobDetailsLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface JoinUsJobDetailsLangMapper 
{
    /**
     * 查询职位内容多语言
     * 
     * @param id 职位内容多语言主键
     * @return 职位内容多语言
     */
    public JoinUsJobDetailsLang selectJoinUsJobDetailsLangById(Long id);

    /**
     * 查询职位内容多语言列表
     * 
     * @param joinUsJobDetailsLang 职位内容多语言
     * @return 职位内容多语言集合
     */
    public List<JoinUsJobDetailsLang> selectJoinUsJobDetailsLangList(JoinUsJobDetailsLang joinUsJobDetailsLang);

    /**
     * 新增职位内容多语言
     * 
     * @param joinUsJobDetailsLang 职位内容多语言
     * @return 结果
     */
    public int insertJoinUsJobDetailsLang(JoinUsJobDetailsLang joinUsJobDetailsLang);

    /**
     * 修改职位内容多语言
     * 
     * @param joinUsJobDetailsLang 职位内容多语言
     * @return 结果
     */
    public int updateJoinUsJobDetailsLang(JoinUsJobDetailsLang joinUsJobDetailsLang);

    /**
     * 删除职位内容多语言
     * 
     * @param id 职位内容多语言主键
     * @return 结果
     */
    public int deleteJoinUsJobDetailsLangById(Long id);

    /**
     * 批量删除职位内容多语言
     * @return 结果
     */
    public int deleteJoinUsJobDetailsLangByIds(@Param("list")List<Long> list);

    void batchInsert(@Param("list") List<JoinUsJobDetailsLang> list);

    void deleteByTitleIds(@Param("list") List<Long> list);
}
