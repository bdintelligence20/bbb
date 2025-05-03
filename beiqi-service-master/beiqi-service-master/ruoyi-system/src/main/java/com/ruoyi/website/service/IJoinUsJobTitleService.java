package com.ruoyi.website.service;

import com.ruoyi.website.domain.vo.JobTitleListVO;
import com.ruoyi.website.domain.vo.JoinUsJobTitileSelectVO;
import com.ruoyi.website.domain.vo.JoinUsJobTitleDetailVO;
import com.ruoyi.website.domain.vo.JoinUsJobTitleVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface IJoinUsJobTitleService 
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
    public List<JoinUsJobTitleVO> selectJoinUsJobTitleList(JoinUsJobTitileSelectVO selectVO);

    /**
     * 新增职位
     * @return 结果
     */
    public int insertJoinUsJobTitle(List<JoinUsJobTitleVO> titleVOS);

    /**
     * 修改职位
     * @return 结果
     */
    public int updateJoinUsJobTitle(List<JoinUsJobTitleVO> titleVOS);

    /**
     * 批量删除职位
     * 
     * @param ids 需要删除的职位主键集合
     * @return 结果
     */
    public int deleteJoinUsJobTitleByIds(List<Long> ids);

    /**
     * 删除职位信息
     * 
     * @param id 职位主键
     * @return 结果
     */
    public int deleteJoinUsJobTitleById(Long id);

    List<JobTitleListVO> getPage(String lang, Long areaId, Long categoryId);

    JoinUsJobTitleVO getJobDetail(String lang, Long id);
}
