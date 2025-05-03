package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.AfterSaleItemLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 售后服务项多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface AfterSaleItemLangMapper 
{
    /**
     * 查询售后服务项多语言
     * 
     * @param id 售后服务项多语言主键
     * @return 售后服务项多语言
     */
    public AfterSaleItemLang selectAfterSaleItemLangById(Long id);

    /**
     * 查询售后服务项多语言列表
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 售后服务项多语言集合
     */
    public List<AfterSaleItemLang> selectAfterSaleItemLangList(AfterSaleItemLang afterSaleItemLang);

    /**
     * 新增售后服务项多语言
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 结果
     */
    public int insertAfterSaleItemLang(AfterSaleItemLang afterSaleItemLang);

    /**
     * 修改售后服务项多语言
     * 
     * @param afterSaleItemLang 售后服务项多语言
     * @return 结果
     */
    public int updateAfterSaleItemLang(AfterSaleItemLang afterSaleItemLang);

    /**
     * 删除售后服务项多语言
     * 
     * @param id 售后服务项多语言主键
     * @return 结果
     */
    public int deleteAfterSaleItemLangById(Long id);

    /**
     * 批量删除售后服务项多语言
     * @return 结果
     */
    public int deleteAfterSaleItemLangByIds(@Param("list") List<Long> list);

    void batchInsert(@Param("list") List<AfterSaleItemLang> list);

    int deleteAfterSaleItemLangByItemId(@Param("list") List<Long> list);
}
