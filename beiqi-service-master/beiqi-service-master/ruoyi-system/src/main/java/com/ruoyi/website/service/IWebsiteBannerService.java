package com.ruoyi.website.service;

import java.util.List;

import com.ruoyi.website.domain.BannerLang;
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.domain.vo.WebSiteBannerVO;

/**
 * 首页bannerService接口
 * 
 * @author thh
 * @date 2021-05-05
 */
public interface IWebsiteBannerService 
{
    /**
     * 查询首页banner
     * 
     * @param bannerId 首页bannerID
     * @return 首页banner
     */
    public List<BannerLang> selectWebsiteBannerById(Long bannerId);

    /**
     * 查询首页banner列表
     * 
     * @param websiteBanner 首页banner
     * @return 首页banner集合
     */
    public List<WebsiteBanner> selectWebsiteBannerList(WebsiteBanner websiteBanner);

    /**
     * 新增首页banner
     * 
     * @param websiteBanners 首页banner
     * @return 结果
     */
    public int insertWebsiteBanner(List<WebSiteBannerVO> websiteBanners);

    /**
     * 修改首页banner
     * 
     * @param bannerLangs
     * @return 结果
     */
    public int updateWebsiteBanner(List<BannerLang> bannerLangs);

    /**
     * 批量删除首页banner
     * 
     * @param bannerIds 需要删除的首页bannerID
     * @return 结果
     */
    public int deleteWebsiteBannerByIds(List<Long> bannerIds);

    /**
     * 删除首页banner信息
     * 
     * @param bannerId 首页bannerID
     * @return 结果
     */
    public int deleteWebsiteBannerById(Long bannerId);

    List<WebsiteBanner> selectIndexBanner(String lang, Integer type);
}
