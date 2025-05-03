package com.ruoyi.website.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.index.vo.SocialVO;
import com.ruoyi.website.domain.SocialContent;
import com.ruoyi.website.domain.SocialContentLang;
import com.ruoyi.website.domain.vo.SocialContentVO;
import com.ruoyi.website.domain.vo.SocialReportVO;
import com.ruoyi.website.mapper.SocialContentLangMapper;
import com.ruoyi.website.mapper.SocialContentMapper;
import com.ruoyi.website.mapper.SocialReportMapper;
import com.ruoyi.website.service.ISocialContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 社会责任内容Service业务层处理
 *
 * @author thh
 * @date 2022-06-11
 */
@Service
public class SocialContentServiceImpl implements ISocialContentService
{
    @Autowired
    private SocialContentMapper socialContentMapper;

    @Autowired
    private SocialContentLangMapper socialContentLangMapper;

    @Autowired
    private SocialReportMapper socialReportMapper;

    /**
     * 查询社会责任内容
     * 
     * @param id 社会责任内容主键
     * @return 社会责任内容
     */
    @Override
    public List<SocialContentVO> selectSocialContentById(Long id) {
        return socialContentMapper.selectSocialContentListById(id);
    }

    /**
     * 查询社会责任内容列表
     * @return 社会责任内容
     */
    @Override
    public List<SocialContentVO> selectSocialContentList(Integer status) {
        return socialContentMapper.selectSocialContentList("en", status);
    }

    /**
     * 新增社会责任内容
     * 
     * @param contentVOS 社会责任内容
     * @return 结果
     */
    @Override
    public int insertSocialContent(List<SocialContentVO> contentVOS) {
        if (CollUtil.isEmpty(contentVOS)){
            throw new ServiceException("参数不能为空");
        }
        SocialContentVO socialContentVO = contentVOS.get(0);
        SocialContent content = new SocialContent();
        BeanUtils.copyProperties(socialContentVO, content);
        content.setCreateTime(DateUtils.getNowDate());
        int i = socialContentMapper.insertSocialContent(content);

        List<SocialContentLang> socialContentLangs = contentVOS.stream().map(item ->{
            SocialContentLang socialContentLang = new SocialContentLang();
            BeanUtils.copyProperties(item, socialContentLang);
            socialContentLang.setSocialContentId(content.getId());
            socialContentLang.setCreateTime(DateUtils.getNowDate());
            return socialContentLang;
        }).collect(Collectors.toList());
        socialContentLangMapper.batchInsert(socialContentLangs);
        return i;
    }

    /**
     * 修改社会责任内容
     * 
     * @param contentVOS 社会责任内容
     * @return 结果
     */
    @Override
    public int updateSocialContent(List<SocialContentVO> contentVOS) {
        if (CollUtil.isEmpty(contentVOS)){
            throw new ServiceException("参数不能为空");
        }
        socialContentMapper.deleteSocialContentById(contentVOS.get(0).getId());
        socialContentLangMapper.deleteSocialContentLangByIds(contentVOS.stream().map(item ->{return item.getSocialContentLangId();}).collect(Collectors.toList()));

        return insertSocialContent(contentVOS);
    }

    /**
     * 批量删除社会责任内容
     * 
     * @param list 需要删除的社会责任内容主键
     * @return 结果
     */
    @Override
    public int deleteSocialContentByIds(List<Long> list) {
        return socialContentMapper.deleteSocialContentByIds(list);
    }

    /**
     * 删除社会责任内容信息
     * 
     * @param id 社会责任内容主键
     * @return 结果
     */
    @Override
    public int deleteSocialContentById(Long id)
    {
        return socialContentMapper.deleteSocialContentById(id);
    }

    @Override
    public SocialVO getSocial(String lang) {
        if (StrUtil.isEmpty(lang)){
            throw new ServiceException("语种不能为空");
        }
        SocialVO socialVO = new SocialVO();
        List<SocialContentVO> contentVOS = socialContentMapper.selectSocialContentList(lang, 0);
        if (CollUtil.isNotEmpty(contentVOS)){
            SocialContentVO socialContentVO = contentVOS.get(0);
            socialVO.setContent(socialContentVO.getContent());
        }
        List<SocialReportVO> reportVOS = socialReportMapper.selectSocialReportList(lang, 0, 1);
        if (CollUtil.isNotEmpty(contentVOS)){
            List<com.ruoyi.index.vo.SocialReportVO> reports = new ArrayList<>();
            reportVOS.forEach(item ->{
                com.ruoyi.index.vo.SocialReportVO reportVO = new com.ruoyi.index.vo.SocialReportVO();
                BeanUtils.copyProperties(item, reportVO);
                reports.add(reportVO);
            });
            socialVO.setReports(reports);
        }

        return socialVO;
    }
}
