package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.JoinUsArea;
import com.ruoyi.website.domain.JoinUsAreaLang;
import com.ruoyi.website.domain.vo.JoinUsAreaVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 工作地点Mapper接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface JoinUsAreaMapper 
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
     * 
     * @param joinUsArea 工作地点
     * @return 结果
     */
    public int insertJoinUsArea(JoinUsArea joinUsArea);

    /**
     * 修改工作地点
     * 
     * @param joinUsArea 工作地点
     * @return 结果
     */
    public int updateJoinUsArea(JoinUsArea joinUsArea);

    /**
     * 删除工作地点
     * 
     * @param id 工作地点主键
     * @return 结果
     */
    public int deleteJoinUsAreaById(Long id);

    /**
     * 批量删除工作地点
     * @return 结果
     */
    public int deleteJoinUsAreaByIds(@Param("list") List<Long> list);

    List<JoinUsAreaLang> selectByLang(@Param("lang") String lang);
}
