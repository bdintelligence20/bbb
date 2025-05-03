package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.website.domain.AfterSaleCountry;
import com.ruoyi.website.domain.vo.AfterSaleCountryLangVO;

/**
 * 问卷星国家配置Service接口
 * @date 2022-07-05
 */
public interface IAfterSaleCountryService 
{
    /**
     * 查询问卷星国家配置
     * 
     * @param id 问卷星国家配置主键
     * @return 问卷星国家配置
     */
    public List<AfterSaleCountryLangVO> selectAfterSaleCountryById(Long id);

    /**
     * 查询问卷星国家配置列表
     * @return 问卷星国家配置集合
     */
    public List<AfterSaleCountryLangVO> selectAfterSaleCountryList(String name);

    /**
     * 新增问卷星国家配置
     * @return 结果
     */
    public int insertAfterSaleCountry(List<AfterSaleCountryLangVO> langVOS);

    /**
     * 修改问卷星国家
     * @return 结果
     */
    public int updateAfterSaleCountry(List<AfterSaleCountryLangVO> langVOS);

    /**
     * 批量删除问卷星国家配置
     * 
     * @param ids 需要删除的问卷星国家配置主键集合
     * @return 结果
     */
    public int deleteAfterSaleCountryByIds(List<Long> ids);
}
