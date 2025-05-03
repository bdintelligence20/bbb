package com.ruoyi.website.service;

import com.ruoyi.index.vo.AfterSaleVO;
import com.ruoyi.website.domain.AfterSaleContent;
import com.ruoyi.website.domain.vo.AfterSaleContentVO;
import com.ruoyi.website.domain.vo.AfterSaleCountryLangVO;
import com.ruoyi.website.domain.vo.AfterSaleCountryVO;
import org.apache.ibatis.annotations.Lang;

import java.util.List;

/**
 * 售后文章内容Service接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface IAfterSaleContentService 
{
    /**
     * 查询售后文章内容
     * 
     * @param id 售后文章内容主键
     * @return 售后文章内容
     */
    public List<AfterSaleContentVO> selectAfterSaleContentById(Long id);

    /**
     * 查询售后文章内容列表
     *
     * @return 售后文章内容集合
     */
    public List<AfterSaleContentVO> selectAfterSaleContentList();

    /**
     * 新增售后文章内容
     * 
     * @param contentVOS 售后文章内容
     * @return 结果
     */
    public int insertAfterSaleContent(List<AfterSaleContentVO> contentVOS);

    /**
     * 修改售后文章内容
     *
     * @param contentVOS 售后文章内容
     * @return 结果
     */
    public int updateAfterSaleContent(List<AfterSaleContentVO> contentVOS);

    /**
     * 批量删除售后文章内容
     * 
     * @param ids 需要删除的售后文章内容主键集合
     * @return 结果
     */
    public int deleteAfterSaleContentByIds(List<Long> ids);

    /**
     * 删除售后文章内容信息
     * 
     * @param id 售后文章内容主键
     * @return 结果
     */
    public int deleteAfterSaleContentById(Long id);

    AfterSaleVO getAfterSaleContent(String lang);

    List<AfterSaleCountryVO> getAfterSaleCountry(String lang);
}
