package com.ruoyi.website.service;

import com.ruoyi.website.domain.JoinUsWelfare;
import com.ruoyi.website.domain.vo.JoinUsWelfareListVO;
import com.ruoyi.website.domain.vo.JoinUsWelfareVO;

import java.util.List;

/**
 * 招聘福利配置Service接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface IJoinUsWelfareService 
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
     * @return 结果
     */
    public int insertJoinUsWelfare(List<JoinUsWelfareVO> joinUsWelfareVOS);

    /**
     * 修改招聘福利配置
     * @return 结果
     */
    public int updateJoinUsWelfare(List<JoinUsWelfareVO> joinUsWelfareVOS);

    /**
     * 批量删除招聘福利配置
     * 
     * @param ids 需要删除的招聘福利配置主键集合
     * @return 结果
     */
    public int deleteJoinUsWelfareByIds(List<Long> ids);

    /**
     * 删除招聘福利配置信息
     * 
     * @param id 招聘福利配置主键
     * @return 结果
     */
    public int deleteJoinUsWelfareById(Long id);

    List<JoinUsWelfareListVO> getWelfare(String lang);
}
