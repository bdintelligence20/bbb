package com.ruoyi.website.service;

import com.ruoyi.website.domain.ContentUs;

import java.util.List;

/**
 * 联系我们Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface IContentUsService 
{
    /**
     * 查询联系我们
     * 
     * @param id 联系我们主键
     * @return 联系我们
     */
    public ContentUs selectContentUsById(Long id);

    /**
     * 查询联系我们列表
     * 
     * @param contentUs 联系我们
     * @return 联系我们集合
     */
    public List<ContentUs> selectContentUsList(ContentUs contentUs);

    /**
     * 新增联系我们
     * 
     * @param contentUs 联系我们
     * @return 结果
     */
    public int insertContentUs(ContentUs contentUs);

    /**
     * 修改联系我们
     * 
     * @param contentUs 联系我们
     * @return 结果
     */
    public int updateContentUs(ContentUs contentUs);

    /**
     * 批量删除联系我们
     * 
     * @param ids 需要删除的联系我们主键集合
     * @return 结果
     */
    public int deleteContentUsByIds(Long[] ids);

    /**
     * 删除联系我们信息
     * 
     * @param id 联系我们主键
     * @return 结果
     */
    public int deleteContentUsById(Long id);
}
