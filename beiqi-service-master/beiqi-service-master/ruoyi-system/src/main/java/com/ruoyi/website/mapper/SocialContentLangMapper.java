package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.SocialContentLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会责任内容多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-11
 */
public interface SocialContentLangMapper 
{
    /**
     * 查询社会责任内容多语言
     * 
     * @param id 社会责任内容多语言主键
     * @return 社会责任内容多语言
     */
    public SocialContentLang selectSocialContentLangById(Long id);

    /**
     * 查询社会责任内容多语言列表
     * 
     * @param socialContentLang 社会责任内容多语言
     * @return 社会责任内容多语言集合
     */
    public List<SocialContentLang> selectSocialContentLangList(SocialContentLang socialContentLang);

    /**
     * 新增社会责任内容多语言
     * 
     * @param socialContentLang 社会责任内容多语言
     * @return 结果
     */
    public int insertSocialContentLang(SocialContentLang socialContentLang);

    /**
     * 修改社会责任内容多语言
     * 
     * @param socialContentLang 社会责任内容多语言
     * @return 结果
     */
    public int updateSocialContentLang(SocialContentLang socialContentLang);

    /**
     * 删除社会责任内容多语言
     * 
     * @param id 社会责任内容多语言主键
     * @return 结果
     */
    public int deleteSocialContentLangById(Long id);

    /**
     * 批量删除社会责任内容多语言
     * 
     * @param list 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSocialContentLangByIds(@Param("list") List<Long> list);

    void batchInsert(@Param("list") List<SocialContentLang> list);
}
