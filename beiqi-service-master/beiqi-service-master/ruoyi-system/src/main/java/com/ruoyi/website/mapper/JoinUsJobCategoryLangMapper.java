package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.JoinUsJobCategoryLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工作类型多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface JoinUsJobCategoryLangMapper 
{
    /**
     * 查询工作类型多语言
     * 
     * @param id 工作类型多语言主键
     * @return 工作类型多语言
     */
    public JoinUsJobCategoryLang selectJoinUsJobCategoryLangById(Long id);

    /**
     * 查询工作类型多语言列表
     * 
     * @param joinUsJobCategoryLang 工作类型多语言
     * @return 工作类型多语言集合
     */
    public List<JoinUsJobCategoryLang> selectJoinUsJobCategoryLangList(JoinUsJobCategoryLang joinUsJobCategoryLang);

    /**
     * 新增工作类型多语言
     * 
     * @param joinUsJobCategoryLang 工作类型多语言
     * @return 结果
     */
    public int insertJoinUsJobCategoryLang(JoinUsJobCategoryLang joinUsJobCategoryLang);

    /**
     * 修改工作类型多语言
     * 
     * @param joinUsJobCategoryLang 工作类型多语言
     * @return 结果
     */
    public int updateJoinUsJobCategoryLang(JoinUsJobCategoryLang joinUsJobCategoryLang);

    /**
     * 删除工作类型多语言
     * 
     * @param id 工作类型多语言主键
     * @return 结果
     */
    public int deleteJoinUsJobCategoryLangById(Long id);

    /**
     * 批量删除工作类型多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJoinUsJobCategoryLangByIds(@Param("list") List<Long> list);

    void batchInsert(@Param("list") List<JoinUsJobCategoryLang> list);
}
