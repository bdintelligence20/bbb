package com.ruoyi.website.service;

import com.ruoyi.website.domain.AfterSaleItem;
import com.ruoyi.website.domain.vo.AfterSaleItemVO;

import java.util.List;

/**
 * 售后服务项Service接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface IAfterSaleItemService 
{
    /**
     * 查询售后服务项
     * 
     * @param id 售后服务项主键
     * @return 售后服务项
     */
    public List<AfterSaleItemVO> selectAfterSaleItemById(Long id);

    /**
     * 查询售后服务项列表
     *
     * @return 售后服务项集合
     */
    public List<AfterSaleItemVO> selectAfterSaleItemList();

    /**
     * 新增售后服务项
     * 
     * @return 结果
     */
    public int insertAfterSaleItem(List<AfterSaleItemVO> saleItemVOS);

    /**
     * 修改售后服务项
     * 
     * @return 结果
     */
    public int updateAfterSaleItem(List<AfterSaleItemVO> saleItemVOS);

    /**
     * 批量删除售后服务项
     * 
     * @param ids 需要删除的售后服务项主键集合
     * @return 结果
     */
    public int deleteAfterSaleItemByIds(List<Long> ids);

    /**
     * 删除售后服务项信息
     * 
     * @param id 售后服务项主键
     * @return 结果
     */
    public int deleteAfterSaleItemById(Long id);
}
