package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.JoinUsJobCategory;
import com.ruoyi.website.domain.JoinUsJobCategoryLang;
import com.ruoyi.website.domain.vo.JoinUsJobCategoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工作类型Mapper接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface JoinUsJobCategoryMapper 
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
     * 
     * @param joinUsJobCategory 工作类型
     * @return 结果
     */
    public int insertJoinUsJobCategory(JoinUsJobCategory joinUsJobCategory);

    /**
     * 修改工作类型
     * 
     * @param joinUsJobCategory 工作类型
     * @return 结果
     */
    public int updateJoinUsJobCategory(JoinUsJobCategory joinUsJobCategory);

    /**
     * 删除工作类型
     * 
     * @param id 工作类型主键
     * @return 结果
     */
    public int deleteJoinUsJobCategoryById(Long id);

    /**
     * 批量删除工作类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJoinUsJobCategoryByIds(@Param("list") List<Long> ids);

    List<JoinUsJobCategoryLang> selectJobCategoryByLang(@Param("lang") String lang);
}
