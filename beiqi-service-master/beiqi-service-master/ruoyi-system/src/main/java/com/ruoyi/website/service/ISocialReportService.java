package com.ruoyi.website.service;

import com.ruoyi.website.domain.SocialReport;
import com.ruoyi.website.domain.vo.SocialReportVO;

import java.util.List;

/**
 * 社会责任报告Service接口
 * 
 * @author thh
 * @date 2022-06-11
 */
public interface ISocialReportService 
{
    /**
     * 查询社会责任报告
     * 
     * @param id 社会责任报告主键
     * @return 社会责任报告
     */
    public List<SocialReportVO> selectSocialReportById(Long id);

    /**
     * 查询社会责任报告列表

     * @return 社会责任报告集合
     */
    public List<SocialReportVO> selectSocialReportList(Integer status);

    /**
     * 新增社会责任报告
     * @return 结果
     */
    public int insertSocialReport(List<SocialReportVO> reportVOS);

    /**
     * 修改社会责任报告
     * @return 结果
     */
    public int updateSocialReport(List<SocialReportVO> reportVOS);

    /**
     * 批量删除社会责任报告
     * 
     * @param ids 需要删除的社会责任报告主键集合
     * @return 结果
     */
    public int deleteSocialReportByIds(List<Long> ids);

    /**
     * 删除社会责任报告信息
     * 
     * @param id 社会责任报告主键
     * @return 结果
     */
    public int deleteSocialReportById(Long id);
}
