package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.website.domain.AfterSaleCountryLang;
import org.apache.ibatis.annotations.Param;

/**
 * 问卷星国家配置多语言Mapper接口
 * @date 2022-07-05
 */
public interface AfterSaleCountryLangMapper 
{
    /**
     * 查询问卷星国家配置多语言
     * 
     * @param id 问卷星国家配置多语言主键
     * @return 问卷星国家配置多语言
     */
    public AfterSaleCountryLang selectAfterSaleCountryLangById(Long id);

    /**
     * 查询问卷星国家配置多语言列表
     * 
     * @param afterSaleCountryLang 问卷星国家配置多语言
     * @return 问卷星国家配置多语言集合
     */
    public List<AfterSaleCountryLang> selectAfterSaleCountryLangList(AfterSaleCountryLang afterSaleCountryLang);

    /**
     * 新增问卷星国家配置多语言
     * 
     * @param afterSaleCountryLang 问卷星国家配置多语言
     * @return 结果
     */
    public int insertAfterSaleCountryLang(AfterSaleCountryLang afterSaleCountryLang);

    /**
     * 修改问卷星国家配置多语言
     * 
     * @param afterSaleCountryLang 问卷星国家配置多语言
     * @return 结果
     */
    public int updateAfterSaleCountryLang(AfterSaleCountryLang afterSaleCountryLang);

    /**
     * 删除问卷星国家配置多语言
     * 
     * @param id 问卷星国家配置多语言主键
     * @return 结果
     */
    public int deleteAfterSaleCountryLangById(Long id);

    /**
     * 批量删除问卷星国家配置多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAfterSaleCountryLangByIds(@Param("list") List<Long> ids);

    void batchInsert(@Param("list") List<AfterSaleCountryLang> list);

    void deleteByCountryIds(@Param("list") List<Long> ids);
}
