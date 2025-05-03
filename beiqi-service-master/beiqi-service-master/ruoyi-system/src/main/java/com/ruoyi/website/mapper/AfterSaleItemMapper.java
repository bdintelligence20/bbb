package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.AfterSaleItem;
import com.ruoyi.website.domain.vo.AfterSaleItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 售后服务项Mapper接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface AfterSaleItemMapper 
{
    /**
     * 查询售后服务项
     * 
     * @param id 售后服务项主键
     * @return 售后服务项
     */
    public AfterSaleItem selectAfterSaleItemById(Long id);

    /**
     * 查询售后服务项列表
     * @return 售后服务项集合
     */
    public List<AfterSaleItemVO> selectAfterSaleItemList(@Param("lang") String lang, @Param("status") Integer status);


    /**
     * 新增售后服务项
     * 
     * @param afterSaleItem 售后服务项
     * @return 结果
     */
    public int insertAfterSaleItem(AfterSaleItem afterSaleItem);

    /**
     * 修改售后服务项
     * 
     * @param afterSaleItem 售后服务项
     * @return 结果
     */
    public int updateAfterSaleItem(AfterSaleItem afterSaleItem);

    /**
     * 删除售后服务项
     * 
     * @param id 售后服务项主键
     * @return 结果
     */
    public int deleteAfterSaleItemById(Long id);

    /**
     * 批量删除售后服务项
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAfterSaleItemByIds(@Param("list") List<Long> list);

    List<AfterSaleItemVO> selectAfterSaleItemDetail(@Param("id") Long id);
}
