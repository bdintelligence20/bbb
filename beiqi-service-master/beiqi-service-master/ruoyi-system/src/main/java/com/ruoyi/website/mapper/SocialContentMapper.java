package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.SocialContent;
import com.ruoyi.website.domain.vo.SocialContentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社会责任内容Mapper接口
 * 
 * @author thh
 * @date 2022-06-11
 */
public interface SocialContentMapper 
{
    /**
     * 查询社会责任内容
     * 
     * @param id 社会责任内容主键
     * @return 社会责任内容
     */
    public List<SocialContentVO> selectSocialContentListById(@Param("id") Long id);

    /**
     * 查询社会责任内容列表
     * @return 社会责任内容集合
     */
    public List<SocialContentVO> selectSocialContentList(@Param("lang") String lang, @Param("status") Integer status);

    /**
     * 新增社会责任内容
     * 
     * @param socialContent 社会责任内容
     * @return 结果
     */
    public int insertSocialContent(SocialContent socialContent);

    /**
     * 修改社会责任内容
     * 
     * @param socialContent 社会责任内容
     * @return 结果
     */
    public int updateSocialContent(SocialContent socialContent);

    /**
     * 删除社会责任内容
     * 
     * @param id 社会责任内容主键
     * @return 结果
     */
    public int deleteSocialContentById(Long id);

    /**
     * 批量删除社会责任内容
     * 
     * @param list 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSocialContentByIds(@Param("list") List<Long> list);
}
