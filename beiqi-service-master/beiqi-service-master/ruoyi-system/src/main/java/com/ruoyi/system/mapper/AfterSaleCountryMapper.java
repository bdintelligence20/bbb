package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.website.domain.AfterSaleCountry;
import com.ruoyi.website.domain.vo.AfterSaleCountryLangVO;
import com.ruoyi.website.domain.vo.AfterSaleCountryVO;
import org.apache.ibatis.annotations.Param;

/**
 * 问卷星国家配置Mapper接口
 * @date 2022-07-05
 */
public interface AfterSaleCountryMapper 
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
    public List<AfterSaleCountryLangVO> selectAfterSaleCountryList(@Param("name") String name);

    /**
     * 新增问卷星国家配置
     * 
     * @param afterSaleCountry 问卷星国家配置
     * @return 结果
     */
    public int insertAfterSaleCountry(AfterSaleCountry afterSaleCountry);

    /**
     * 修改问卷星国家配置
     * 
     * @param afterSaleCountry 问卷星国家配置
     * @return 结果
     */
    public int updateAfterSaleCountry(AfterSaleCountry afterSaleCountry);

    /**
     * 删除问卷星国家配置
     * 
     * @param id 问卷星国家配置主键
     * @return 结果
     */
    public int deleteAfterSaleCountryById(Long id);

    /**
     * 批量删除问卷星国家配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAfterSaleCountryByIds(@Param("ids") List<Long> ids);

    List<AfterSaleCountryVO> selectByLang(@Param("lang") String lang);
}
