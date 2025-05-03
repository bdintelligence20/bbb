package com.ruoyi.website.service;

import com.ruoyi.index.vo.SocialVO;
import com.ruoyi.website.domain.SocialContent;
import com.ruoyi.website.domain.vo.SocialContentVO;

import java.util.List;

/**
 * 社会责任内容Service接口
 * 
 * @author thh
 * @date 2022-06-11
 */
public interface ISocialContentService 
{
    /**
     * 查询社会责任内容
     * 
     * @param id 社会责任内容主键
     * @return 社会责任内容
     */
    public List<SocialContentVO> selectSocialContentById(Long id);

    /**
     * 查询社会责任内容列表
     * @return 社会责任内容集合
     */
    public List<SocialContentVO> selectSocialContentList(Integer status);

    /**
     * 新增社会责任内容
     * 
     * @param contentVOS 社会责任内容
     * @return 结果
     */
    public int insertSocialContent(List<SocialContentVO> contentVOS);

    /**
     * 修改社会责任内容
     * @return 结果
     */
    public int updateSocialContent(List<SocialContentVO> contentVOS);

    /**
     * 批量删除社会责任内容
     * 
     * @param list 需要删除的社会责任内容主键集合
     * @return 结果
     */
    public int deleteSocialContentByIds(List<Long> list);

    /**
     * 删除社会责任内容信息
     * 
     * @param id 社会责任内容主键
     * @return 结果
     */
    public int deleteSocialContentById(Long id);

    SocialVO getSocial(String lang);
}
