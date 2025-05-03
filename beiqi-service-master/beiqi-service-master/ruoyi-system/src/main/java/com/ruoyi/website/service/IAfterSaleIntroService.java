package com.ruoyi.website.service;

import com.ruoyi.website.domain.AfterSaleIntro;
import com.ruoyi.website.domain.vo.AfterSaleIntroVO;

import java.util.List;

/**
 * 售后简介列Service接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface IAfterSaleIntroService 
{
    /**
     * 查询售后简介列
     * 
     * @param id 售后简介列主键
     * @return 售后简介列
     */
    public List<AfterSaleIntroVO> selectAfterSaleIntroById(Long id);

    /**
     * 查询售后简介列列表
     *
     * @return 售后简介列集合
     */
    public List<AfterSaleIntroVO> selectAfterSaleIntroList();

    /**
     * 新增售后简介列
     * 
     * @param saleIntroVOS 售后简介列
     * @return 结果
     */
    public int insertAfterSaleIntro(List<AfterSaleIntroVO> saleIntroVOS);

    /**
     * 修改售后简介列
     *
     * @return 结果
     */
    public int updateAfterSaleIntro(List<AfterSaleIntroVO> saleIntroVOS);

    /**
     * 批量删除售后简介列
     * 
     * @param ids 需要删除的售后简介列主键集合
     * @return 结果
     */
    public int deleteAfterSaleIntroByIds(List<Long> ids);

    /**
     * 删除售后简介列信息
     * 
     * @param id 售后简介列主键
     * @return 结果
     */
    public int deleteAfterSaleIntroById(Long id);
}
