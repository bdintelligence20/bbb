package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.JoinUsWelfareLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 招聘福利配置多语言Mapper接口
 * 
 * @author thh
 * @date 2022-06-14
 */
public interface JoinUsWelfareLangMapper 
{
    /**
     * 查询招聘福利配置多语言
     * 
     * @param id 招聘福利配置多语言主键
     * @return 招聘福利配置多语言
     */
    public JoinUsWelfareLang selectJoinUsWelfareLangById(Long id);

    /**
     * 查询招聘福利配置多语言列表
     * 
     * @param joinUsWelfareLang 招聘福利配置多语言
     * @return 招聘福利配置多语言集合
     */
    public List<JoinUsWelfareLang> selectJoinUsWelfareLangList(JoinUsWelfareLang joinUsWelfareLang);

    /**
     * 新增招聘福利配置多语言
     * 
     * @param joinUsWelfareLang 招聘福利配置多语言
     * @return 结果
     */
    public int insertJoinUsWelfareLang(JoinUsWelfareLang joinUsWelfareLang);

    /**
     * 修改招聘福利配置多语言
     * 
     * @param joinUsWelfareLang 招聘福利配置多语言
     * @return 结果
     */
    public int updateJoinUsWelfareLang(JoinUsWelfareLang joinUsWelfareLang);

    /**
     * 删除招聘福利配置多语言
     * 
     * @param id 招聘福利配置多语言主键
     * @return 结果
     */
    public int deleteJoinUsWelfareLangById(Long id);

    /**
     * 批量删除招聘福利配置多语言
     * @return 结果
     */
    public int deleteJoinUsWelfareLangByIds(@Param("list") List<Long> list);

    void batchInsert(@Param("list") List<JoinUsWelfareLang> list);
}
