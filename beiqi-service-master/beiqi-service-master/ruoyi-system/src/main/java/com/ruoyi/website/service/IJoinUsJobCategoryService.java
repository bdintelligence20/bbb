package com.ruoyi.website.service;

import com.ruoyi.website.domain.JoinUsJobCategory;
import com.ruoyi.website.domain.JoinUsJobCategoryLang;
import com.ruoyi.website.domain.vo.JoinUsJobCategoryVO;

import java.util.List;

/**
 * 工作类型Service接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface IJoinUsJobCategoryService 
{
    /**
     * 查询工作类型
     * 
     * @param id 工作类型主键
     * @return 工作类型
     */
    public List<JoinUsJobCategoryVO> selectJoinUsJobCategoryById(Long id);

    /**
     * 查询工作类型列表
     * @return 工作类型集合
     */
    public List<JoinUsJobCategoryVO> selectJoinUsJobCategoryList();

    /**
     * 新增工作类型
     * @return 结果
     */
    public int insertJoinUsJobCategory(List<JoinUsJobCategoryVO> categoryVOS);

    /**
     * 修改工作类型
     * @return 结果
     */
    public int updateJoinUsJobCategory(List<JoinUsJobCategoryVO> categoryVOS);

    /**
     * 批量删除工作类型
     * 
     * @param ids 需要删除的工作类型主键集合
     * @return 结果
     */
    public int deleteJoinUsJobCategoryByIds(List<Long> ids);

    /**
     * 删除工作类型信息
     * 
     * @param id 工作类型主键
     * @return 结果
     */
    public int deleteJoinUsJobCategoryById(Long id);

    List<JoinUsJobCategoryLang> getJoinUsJobCategoryList(String lang);
}
