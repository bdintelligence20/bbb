package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.AfterSaleIntro;
import com.ruoyi.website.domain.vo.AfterSaleIntroVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 售后简介列Mapper接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface AfterSaleIntroMapper 
{
    /**
     * 查询售后简介列
     * 
     * @param id 售后简介列主键
     * @return 售后简介列
     */
    public AfterSaleIntro selectAfterSaleIntroById(Long id);

    /**
     * 查询售后简介列列表
     *
     * @return 售后简介列集合
     */
    public List<AfterSaleIntroVO> selectAfterSaleIntroList(@Param("lang") String lang, @Param("status") Integer status);

    /**
     * 新增售后简介列
     * 
     * @param afterSaleIntro 售后简介列
     * @return 结果
     */
    public int insertAfterSaleIntro(AfterSaleIntro afterSaleIntro);

    /**
     * 修改售后简介列
     * 
     * @param afterSaleIntro 售后简介列
     * @return 结果
     */
    public int updateAfterSaleIntro(AfterSaleIntro afterSaleIntro);

    /**
     * 删除售后简介列
     * 
     * @param id 售后简介列主键
     * @return 结果
     */
    public int deleteAfterSaleIntroById(Long id);

    /**
     * 批量删除售后简介列
     *
     * @return 结果
     */
    public int deleteAfterSaleIntroByIds(@Param("list") List<Long> list);

    List<AfterSaleIntroVO> selectAfterSaleIntroDetailById(@Param("id") Long id);
}
