package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.SocialReportLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会责任报告多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-11
 */
public interface SocialReportLangMapper 
{
    /**
     * 查询社会责任报告多语言
     * 
     * @param id 社会责任报告多语言主键
     * @return 社会责任报告多语言
     */
    public SocialReportLang selectSocialReportLangById(Long id);

    /**
     * 查询社会责任报告多语言列表
     * 
     * @param socialReportLang 社会责任报告多语言
     * @return 社会责任报告多语言集合
     */
    public List<SocialReportLang> selectSocialReportLangList(SocialReportLang socialReportLang);

    /**
     * 新增社会责任报告多语言
     * 
     * @param socialReportLang 社会责任报告多语言
     * @return 结果
     */
    public int insertSocialReportLang(SocialReportLang socialReportLang);

    /**
     * 修改社会责任报告多语言
     * 
     * @param socialReportLang 社会责任报告多语言
     * @return 结果
     */
    public int updateSocialReportLang(SocialReportLang socialReportLang);

    /**
     * 删除社会责任报告多语言
     * 
     * @param id 社会责任报告多语言主键
     * @return 结果
     */
    public int deleteSocialReportLangById(Long id);

    /**
     * 批量删除社会责任报告多语言
     * 
     * @param list 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSocialReportLangByIds(@Param("list") List<Long> list);

    void batchInsert(@Param("list") List<SocialReportLang> list);
}
