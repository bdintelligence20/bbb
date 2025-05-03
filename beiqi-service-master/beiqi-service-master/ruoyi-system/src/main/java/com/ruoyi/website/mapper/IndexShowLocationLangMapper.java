package com.ruoyi.website.mapper;

import com.ruoyi.website.domain.IndexShowLocationLang;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IndexShowLocationLangMapper {
    /**
     * 查询首页配置多语言
     * 
     * @param id 首页配置多语言主键
     * @return 首页配置多语言
     */
    public IndexShowLocationLang selectIndexShowLocationLangById(Long id);

    /**
     * 查询首页配置多语言列表
     * 
     * @param indexShowLocationLang 首页配置多语言
     * @return 首页配置多语言集合
     */
    public List<IndexShowLocationLang> selectIndexShowLocationLangList(IndexShowLocationLang indexShowLocationLang);

    /**
     * 新增首页配置多语言
     * 
     * @param indexShowLocationLang 首页配置多语言
     * @return 结果
     */
    public int insertIndexShowLocationLang(IndexShowLocationLang indexShowLocationLang);

    /**
     * 修改首页配置多语言
     * 
     * @param indexShowLocationLang 首页配置多语言
     * @return 结果
     */
    public int updateIndexShowLocationLang(IndexShowLocationLang indexShowLocationLang);

    /**
     * 删除首页配置多语言
     * 
     * @param id 首页配置多语言主键
     * @return 结果
     */
    public int deleteIndexShowLocationLangById(Long id);

    /**
     * 批量删除首页配置多语言
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIndexShowLocationLangByIds(@Param("list") List<Long> ids);

    void batchInsert(@Param("list") List<IndexShowLocationLang> list);
}
