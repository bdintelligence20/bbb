package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.ContentUs;
import com.ruoyi.website.mapper.ContentUsMapper;
import com.ruoyi.website.service.IContentUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 联系我们Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class ContentUsServiceImpl implements IContentUsService
{
    @Autowired
    private ContentUsMapper contentUsMapper;

    /**
     * 查询联系我们
     * 
     * @param id 联系我们主键
     * @return 联系我们
     */
    @Override
    public ContentUs selectContentUsById(Long id)
    {
        return contentUsMapper.selectContentUsById(id);
    }

    /**
     * 查询联系我们列表
     * 
     * @param contentUs 联系我们
     * @return 联系我们
     */
    @Override
    public List<ContentUs> selectContentUsList(ContentUs contentUs)
    {
        return contentUsMapper.selectContentUsList(contentUs);
    }

    /**
     * 新增联系我们
     * 
     * @param contentUs 联系我们
     * @return 结果
     */
    @Override
    public int insertContentUs(ContentUs contentUs)
    {
        contentUs.setCreateTime(DateUtils.getNowDate());
        return contentUsMapper.insertContentUs(contentUs);
    }

    /**
     * 修改联系我们
     * 
     * @param contentUs 联系我们
     * @return 结果
     */
    @Override
    public int updateContentUs(ContentUs contentUs)
    {
        contentUs.setUpdateTime(DateUtils.getNowDate());
        return contentUsMapper.updateContentUs(contentUs);
    }

    /**
     * 批量删除联系我们
     * 
     * @param ids 需要删除的联系我们主键
     * @return 结果
     */
    @Override
    public int deleteContentUsByIds(Long[] ids)
    {
        return contentUsMapper.deleteContentUsByIds(ids);
    }

    /**
     * 删除联系我们信息
     * 
     * @param id 联系我们主键
     * @return 结果
     */
    @Override
    public int deleteContentUsById(Long id)
    {
        return contentUsMapper.deleteContentUsById(id);
    }
}
