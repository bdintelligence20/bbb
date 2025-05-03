package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.AfterSaleContent;
import com.ruoyi.website.domain.vo.AfterSaleContentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 售后文章内容Mapper接口
 * 
 * @author thh
 * @date 2022-06-10
 */
public interface AfterSaleContentMapper 
{
    /**
     * 查询售后文章内容
     * 
     * @param id 售后文章内容主键
     * @return 售后文章内容
     */
    public AfterSaleContent selectAfterSaleContentById(Long id);

    /**
     * 查询售后文章内容列表
     *
     * @return 售后文章内容集合
     */
    public List<AfterSaleContentVO> selectAfterSaleContentList(@Param("lang") String lang, @Param("status") Integer status);

    /**
     * 新增售后文章内容
     * 
     * @param afterSaleContent 售后文章内容
     * @return 结果
     */
    public int insertAfterSaleContent(AfterSaleContent afterSaleContent);

    /**
     * 修改售后文章内容
     * 
     * @param afterSaleContent 售后文章内容
     * @return 结果
     */
    public int updateAfterSaleContent(AfterSaleContent afterSaleContent);

    /**
     * 删除售后文章内容
     * 
     * @param id 售后文章内容主键
     * @return 结果
     */
    public int deleteAfterSaleContentById(Long id);

    /**
     * 批量删除售后文章内容
     * 
     * @param list 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAfterSaleContentByIds(@Param("list") List<Long> list);

    List<AfterSaleContentVO> selectAfterSaleContentDetail(@Param("id") Long id);
}
