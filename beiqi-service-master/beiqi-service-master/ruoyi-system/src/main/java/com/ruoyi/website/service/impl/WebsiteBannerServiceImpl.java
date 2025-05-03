package com.ruoyi.website.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.BannerLang;
import com.ruoyi.website.domain.vo.WebSiteBannerVO;
import com.ruoyi.website.mapper.BannerLangMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.WebsiteBannerMapper;
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.service.IWebsiteBannerService;
import org.springframework.util.Assert;

/**
 * 首页bannerService业务层处理
 * 
 * @author thh
 * @date 2021-05-05
 */
@Service
public class WebsiteBannerServiceImpl implements IWebsiteBannerService 
{
    @Autowired
    private WebsiteBannerMapper websiteBannerMapper;

    @Autowired
    private BannerLangMapper bannerLangMapper;

    /**
     * 查询首页banner
     * 
     * @param bannerId 首页bannerID
     * @return 首页banner
     */
    @Override
    public List<BannerLang> selectWebsiteBannerById(Long bannerId)
    {
        List<BannerLang> bannerLangs = new ArrayList<>();
        WebsiteBanner websiteBanner = websiteBannerMapper.selectWebsiteBannerById(bannerId);
        BannerLang bannerLang = new BannerLang();
        BeanUtil.copyProperties(websiteBanner, bannerLang);
        bannerLang.setLanguage("en");
        bannerLang.setId(websiteBanner.getBannerId());
        bannerLangs.add(bannerLang);

        BannerLang lang = new BannerLang();
        lang.setBannerId(bannerId);
        bannerLangs.addAll(bannerLangMapper.selectBannerLangList(lang));
        return bannerLangs;
    }

    /**
     * 查询首页banner列表
     * 
     * @param websiteBanner 首页banner
     * @return 首页banner
     */
    @Override
    public List<WebsiteBanner> selectWebsiteBannerList(WebsiteBanner websiteBanner)
    {
        return websiteBannerMapper.selectWebsiteBannerList(websiteBanner);
    }

    /**
     * 新增首页banner
     * 
     * @param websiteBanners 首页banner
     * @return 结果
     */
    @Override
    public int insertWebsiteBanner(List<WebSiteBannerVO> websiteBanners) {
        if (CollUtil.isEmpty(websiteBanners)){
            throw new ServiceException("参数不能为空！");
        }
        //限制除首页之外其他页面只能添加一张banner
        Integer type = websiteBanners.get(0).getType();
        if (0 != type){
            WebsiteBanner websiteBanner = new WebsiteBanner();
            websiteBanner.setType(type);
            List<WebsiteBanner> bannerList = websiteBannerMapper.selectWebsiteBannerList(websiteBanner);
            if (CollUtil.isNotEmpty(bannerList)){
                throw new ServiceException("除首页之外其他页面只能添加一张banner！");
            }
        }
        List<BannerLang> bannerLangs = new ArrayList<>();
        WebsiteBanner banner = new WebsiteBanner();
        for (WebSiteBannerVO websiteBanner : websiteBanners) {
            websiteBanner.setCreateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(websiteBanner.getLanguage())){
                BeanUtils.copyProperties(websiteBanner, banner);
            }else {
                BannerLang bannerLang = new BannerLang();
                BeanUtils.copyProperties(websiteBanner, bannerLang);
                bannerLangs.add(bannerLang);
            }
        }
        int i = websiteBannerMapper.insertWebsiteBanner(banner);
        for (BannerLang bannerLang : bannerLangs) {
            bannerLang.setBannerId(banner.getBannerId());
        }
        bannerLangMapper.batchInsert(bannerLangs);
        return i;
    }

    /**
     * 修改首页banner
     * 
     * @param bannerLangs 首页banner
     * @return 结果
     */
    @Override
    public int updateWebsiteBanner(List<BannerLang> bannerLangs) {
        if (CollUtil.isEmpty(bannerLangs)){
            throw new ServiceException("参数不能为空！");
        }
        //限制除首页之外其他页面只能添加一张banner
        Integer type = bannerLangs.get(0).getType();
        if (0 != type){
            WebsiteBanner websiteBanner = new WebsiteBanner();
            websiteBanner.setType(type);
            List<WebsiteBanner> bannerList = websiteBannerMapper.selectWebsiteBannerList(websiteBanner);
            if (CollUtil.isNotEmpty(bannerList)){
                if (bannerLangs.get(0).getBannerId() != bannerList.get(0).getBannerId()){
                    throw new ServiceException("除首页之外其他页面只能添加一张banner");
                }
            }
        }
        int i = 0;
        for (BannerLang bannerLang : bannerLangs) {
            bannerLang.setUpdateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(bannerLang.getLanguage())){
                WebsiteBanner banner = new WebsiteBanner();
                BeanUtils.copyProperties(bannerLang, banner);
                i = websiteBannerMapper.updateWebsiteBanner(banner);
            }else {
                bannerLangMapper.updateBannerLang(bannerLang);
            }
        }
        return i;
    }

    /**
     * 批量删除首页banner
     * 
     * @param bannerIds 需要删除的首页bannerID
     * @return 结果
     */
    @Override
    public int deleteWebsiteBannerByIds(List<Long> bannerIds) {
        if (CollUtil.isEmpty(bannerIds)){
            throw new ServiceException("参数不能为空!");
        }
        int i = websiteBannerMapper.deleteWebsiteBannerByIds(bannerIds);
        bannerLangMapper.deleteByBannerIds(bannerIds);
        return i;
    }

    /**
     * 删除首页banner信息
     * 
     * @param bannerId 首页bannerID
     * @return 结果
     */
    @Override
    public int deleteWebsiteBannerById(Long bannerId)
    {
        return websiteBannerMapper.deleteWebsiteBannerById(bannerId);
    }

    @Override
    public List<WebsiteBanner> selectIndexBanner(String lang, Integer type) {
        List<WebsiteBanner> banners = new ArrayList<>();
        if (StrUtil.isEmpty(lang) || "en".equalsIgnoreCase(lang)){
            WebsiteBanner websiteBanner = new WebsiteBanner();
            websiteBanner.setType(type);
            websiteBanner.setEnable(1);
            banners.addAll(websiteBannerMapper.selectWebsiteBannerList(websiteBanner));
        }else {
            List<BannerLang> bannerLangs = bannerLangMapper.selectBannerByLang(lang, type, 1);
            for (BannerLang bannerLang : bannerLangs) {
                WebsiteBanner websiteBanner = new WebsiteBanner();
                BeanUtils.copyProperties(bannerLang,websiteBanner);
                banners.add(websiteBanner);
            }
        }
        return banners;
    }
}
