package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.JoinUsWelfare;
import com.ruoyi.website.domain.vo.JoinUsWelfareListVO;
import com.ruoyi.website.domain.vo.JoinUsWelfareVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招聘福利配置Mapper接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface JoinUsWelfareMapper 
{
    /**
     * 查询招聘福利配置
     * 
     * @param id 招聘福利配置主键
     * @return 招聘福利配置
     */
    public List<JoinUsWelfareVO> selectJoinUsWelfareById(Long id);

    /**
     * 查询招聘福利配置列表
     * @return 招聘福利配置集合
     */
    public List<JoinUsWelfareVO> selectJoinUsWelfareList();

    /**
     * 新增招聘福利配置
     * 
     * @param joinUsWelfare 招聘福利配置
     * @return 结果
     */
    public int insertJoinUsWelfare(JoinUsWelfare joinUsWelfare);

    /**
     * 修改招聘福利配置
     * 
     * @param joinUsWelfare 招聘福利配置
     * @return 结果
     */
    public int updateJoinUsWelfare(JoinUsWelfare joinUsWelfare);

    /**
     * 删除招聘福利配置
     * 
     * @param id 招聘福利配置主键
     * @return 结果
     */
    public int deleteJoinUsWelfareById(Long id);

    /**
     * 批量删除招聘福利配置
     * @return 结果
     */
    public int deleteJoinUsWelfareByIds(@Param("list") List<Long> list);

    List<JoinUsWelfareListVO> selectByLang(@Param("lang") String lang);
}
