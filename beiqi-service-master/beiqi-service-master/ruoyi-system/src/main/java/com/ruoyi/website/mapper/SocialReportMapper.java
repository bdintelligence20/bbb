package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.SocialReport;
import com.ruoyi.website.domain.vo.SocialReportVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会责任报告Mapper接口
 * 
 * @author thh
 * @date 2022-06-11
 */
public interface SocialReportMapper 
{
    /**
     * 查询社会责任报告
     * 
     * @param id 社会责任报告主键
     * @return 社会责任报告
     */
    public List<SocialReportVO> selectSocialReportById(@Param("id") Long id);

    /**
     * 查询社会责任报告列表
     * @return 社会责任报告集合
     */
    public List<SocialReportVO> selectSocialReportList(@Param("lang") String lang, @Param("status")Integer status,
                                                       @Param("sort")Integer sort);

    /**
     * 新增社会责任报告
     * 
     * @param socialReport 社会责任报告
     * @return 结果
     */
    public int insertSocialReport(SocialReport socialReport);

    /**
     * 修改社会责任报告
     * 
     * @param socialReport 社会责任报告
     * @return 结果
     */
    public int updateSocialReport(SocialReport socialReport);

    /**
     * 删除社会责任报告
     * 
     * @param id 社会责任报告主键
     * @return 结果
     */
    public int deleteSocialReportById(Long id);

    /**
     * 批量删除社会责任报告
     * 
     * @param list 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSocialReportByIds(@Param("list") List<Long> list);
}
