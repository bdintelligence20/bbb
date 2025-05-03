package com.ruoyi.website.service;

import com.ruoyi.website.domain.BannerLang;

import java.util.List;

/**
 * 首页banner多语言Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface IBannerLangService 
{
    /**
     * 查询首页banner多语言
     * 
     * @param id 首页banner多语言主键
     * @return 首页banner多语言
     */
    public BannerLang selectBannerLangById(Long id);

    /**
     * 查询首页banner多语言列表
     * 
     * @param bannerLang 首页banner多语言
     * @return 首页banner多语言集合
     */
    public List<BannerLang> selectBannerLangList(BannerLang bannerLang);

    /**
     * 新增首页banner多语言
     * 
     * @param bannerLang 首页banner多语言
     * @return 结果
     */
    public int insertBannerLang(BannerLang bannerLang);

    /**
     * 修改首页banner多语言
     * 
     * @param bannerLang 首页banner多语言
     * @return 结果
     */
    public int updateBannerLang(BannerLang bannerLang);

    /**
     * 批量删除首页banner多语言
     * 
     * @param ids 需要删除的首页banner多语言主键集合
     * @return 结果
     */
    public int deleteBannerLangByIds(Long[] ids);

    /**
     * 删除首页banner多语言信息
     * 
     * @param id 首页banner多语言主键
     * @return 结果
     */
    public int deleteBannerLangById(Long id);
}
