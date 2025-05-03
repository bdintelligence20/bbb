package com.ruoyi.website.mapper;

import java.util.List;
import com.ruoyi.website.domain.JoinUsJobTitle;
import com.ruoyi.website.domain.vo.JobTitleListVO;
import com.ruoyi.website.domain.vo.JoinUsJobTitileSelectVO;
import com.ruoyi.website.domain.vo.JoinUsJobTitleVO;
import org.apache.ibatis.annotations.Param;

/**
 * 职位Mapper接口
 * 
 * @author thh
 * @date 2022-06-15
 */
public interface JoinUsJobTitleMapper 
{
    /**
     * 查询职位
     * 
     * @param id 职位主键
     * @return 职位
     */
    public List<JoinUsJobTitleVO> selectJoinUsJobTitleById(Long id);

    /**
     * 查询职位列表
     * @return 职位集合
     */
    public List<JoinUsJobTitleVO> selectJoinUsJobTitleList(JoinUsJobTitileSelectVO item);

    /**
     * 新增职位
     * 
     * @param joinUsJobTitle 职位
     * @return 结果
     */
    public int insertJoinUsJobTitle(JoinUsJobTitle joinUsJobTitle);

    /**
     * 修改职位
     * 
     * @param joinUsJobTitle 职位
     * @return 结果
     */
    public int updateJoinUsJobTitle(JoinUsJobTitle joinUsJobTitle);

    /**
     * 删除职位
     * 
     * @param id 职位主键
     * @return 结果
     */
    public int deleteJoinUsJobTitleById(Long id);

    /**
     * 批量删除职位
     * @return 结果
     */
    public int deleteJoinUsJobTitleByIds(@Param("list") List<Long> list);

    List<JobTitleListVO> selectJobTitleList(@Param("lang") String lang, @Param("areaId") Long areaId, @Param("categoryId") Long categoryId);

    JoinUsJobTitleVO selectByIdAndLang(@Param("lang") String lang, @Param("id") Long id);
}
