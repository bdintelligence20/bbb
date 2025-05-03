package com.ruoyi.website.mapper;

import java.util.List;

import com.ruoyi.website.domain.JoinUsJobLang;
import org.apache.ibatis.annotations.Param;


public interface JoinUsJobLangMapper {
    /**
     * 查询职位多语言
     * 
     * @param id 职位多语言主键
     * @return 职位多语言
     */
    public JoinUsJobLang selectJoinUsJobLangById(Long id);

    /**
     * 查询职位多语言列表
     * 
     * @param joinUsJobLang 职位多语言
     * @return 职位多语言集合
     */
    public List<JoinUsJobLang> selectJoinUsJobLangList(JoinUsJobLang joinUsJobLang);

    /**
     * 新增职位多语言
     * 
     * @param joinUsJobLang 职位多语言
     * @return 结果
     */
    public int insertJoinUsJobLang(JoinUsJobLang joinUsJobLang);

    /**
     * 修改职位多语言
     * 
     * @param joinUsJobLang 职位多语言
     * @return 结果
     */
    public int updateJoinUsJobLang(JoinUsJobLang joinUsJobLang);

    /**
     * 删除职位多语言
     * 
     * @param id 职位多语言主键
     * @return 结果
     */
    public int deleteJoinUsJobLangById(Long id);

    /**
     * 批量删除职位多语言
     * @return 结果
     */
    public int deleteJoinUsJobLangByIds(@Param("list")List<Long> list);

    void batchInsert(@Param("list") List<JoinUsJobLang> list);

    void deleteByJobTitleIds(@Param("list") List<Long> list);
}
