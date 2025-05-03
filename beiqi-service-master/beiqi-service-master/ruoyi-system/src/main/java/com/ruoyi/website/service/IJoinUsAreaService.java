package com.ruoyi.website.service;

import com.ruoyi.website.domain.JoinUsArea;
import com.ruoyi.website.domain.JoinUsAreaLang;
import com.ruoyi.website.domain.vo.JoinUsAreaVO;

import java.util.List;

/**
 * 工作地点Service接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface IJoinUsAreaService 
{
    /**
     * 查询工作地点
     * 
     * @param id 工作地点主键
     * @return 工作地点
     */
    public List<JoinUsAreaVO> selectJoinUsAreaById(Long id);

    /**
     * 查询工作地点列表
     * @return 工作地点集合
     */
    public List<JoinUsAreaVO> selectJoinUsAreaList();

    /**
     * 新增工作地点
     * @return 结果
     */
    public int insertJoinUsArea(List<JoinUsAreaVO> joinUsAreaVOS);

    /**
     * 修改工作地点
     * @return 结果
     */
    public int updateJoinUsArea(List<JoinUsAreaVO> areaVOS);

    /**
     * 批量删除工作地点
     * 
     * @param ids 需要删除的工作地点主键集合
     * @return 结果
     */
    public int deleteJoinUsAreaByIds(List<Long> ids);

    /**
     * 删除工作地点信息
     * 
     * @param id 工作地点主键
     * @return 结果
     */
    public int deleteJoinUsAreaById(Long id);

    List<JoinUsAreaLang> getJoinUsAreaList(String lang);
}
