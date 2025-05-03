package com.ruoyi.website.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.SocialContent;
import com.ruoyi.website.domain.SocialContentLang;
import com.ruoyi.website.domain.SocialReport;
import com.ruoyi.website.domain.SocialReportLang;
import com.ruoyi.website.domain.vo.SocialContentVO;
import com.ruoyi.website.domain.vo.SocialReportVO;
import com.ruoyi.website.mapper.SocialReportLangMapper;
import com.ruoyi.website.mapper.SocialReportMapper;
import com.ruoyi.website.service.ISocialReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 社会责任报告Service业务层处理
 * 
 * @author thh
 * @date 2022-06-11
 */
@Service
public class SocialReportServiceImpl implements ISocialReportService
{
    @Autowired
    private SocialReportMapper socialReportMapper;

    @Autowired
    private SocialReportLangMapper socialReportLangMapper;

    /**
     * 查询社会责任报告
     * 
     * @param id 社会责任报告主键
     * @return 社会责任报告
     */
    @Override
    public List<SocialReportVO> selectSocialReportById(Long id)
    {
        return socialReportMapper.selectSocialReportById(id);
    }

    /**
     * 查询社会责任报告列表
     * @return 社会责任报告
     */
    @Override
    public List<SocialReportVO> selectSocialReportList(Integer status) {
        return socialReportMapper.selectSocialReportList("en", status, 0);
    }

    /**
     * 新增社会责任报告
     * 
     * @return 结果
     */
    @Override
    public int insertSocialReport(List<SocialReportVO> reportVOS) {
        if (CollUtil.isEmpty(reportVOS)){
            throw new ServiceException("参数不能为空");
        }
        SocialReportVO socialReportVO = reportVOS.get(0);
        SocialReport socialReport = new SocialReport();
        BeanUtils.copyProperties(socialReportVO, socialReport);
        socialReport.setCreateTime(DateUtils.getNowDate());
        int i = socialReportMapper.insertSocialReport(socialReport);

        List<SocialReportLang> socialContentLangs = reportVOS.stream().map(item ->{
            SocialReportLang socialReportLang = new SocialReportLang();
            BeanUtils.copyProperties(item, socialReportLang);
            socialReportLang.setSocialReportId(socialReport.getId());
            socialReportLang.setCreateTime(DateUtils.getNowDate());
            return socialReportLang;
        }).collect(Collectors.toList());
        socialReportLangMapper.batchInsert(socialContentLangs);
        return i;
    }

    /**
     * 修改社会责任报告
     * @return 结果
     */
    @Override
    public int updateSocialReport(List<SocialReportVO> reportVOS) {
        if (CollUtil.isEmpty(reportVOS)){
            throw new ServiceException("参数不能为空");
        }
        socialReportMapper.deleteSocialReportById(reportVOS.get(0).getId());
        socialReportLangMapper.deleteSocialReportLangByIds(reportVOS.stream().map(item ->{return item.getSocialReportLangId();}).collect(Collectors.toList()));

        return insertSocialReport(reportVOS);
    }

    /**
     * 批量删除社会责任报告
     * 
     * @param ids 需要删除的社会责任报告主键
     * @return 结果
     */
    @Override
    public int deleteSocialReportByIds(List<Long> ids)
    {
        return socialReportMapper.deleteSocialReportByIds(ids);
    }

    /**
     * 删除社会责任报告信息
     * 
     * @param id 社会责任报告主键
     * @return 结果
     */
    @Override
    public int deleteSocialReportById(Long id)
    {
        return socialReportMapper.deleteSocialReportById(id);
    }
}
