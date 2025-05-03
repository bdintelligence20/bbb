package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.BannerLang;
import com.ruoyi.website.mapper.BannerLangMapper;
import com.ruoyi.website.service.IBannerLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 首页banner多语言Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class BannerLangServiceImpl implements IBannerLangService
{
    @Autowired
    private BannerLangMapper bannerLangMapper;

    /**
     * 查询首页banner多语言
     * 
     * @param id 首页banner多语言主键
     * @return 首页banner多语言
     */
    @Override
    public BannerLang selectBannerLangById(Long id)
    {
        return bannerLangMapper.selectBannerLangById(id);
    }

    /**
     * 查询首页banner多语言列表
     * 
     * @param bannerLang 首页banner多语言
     * @return 首页banner多语言
     */
    @Override
    public List<BannerLang> selectBannerLangList(BannerLang bannerLang)
    {
        return bannerLangMapper.selectBannerLangList(bannerLang);
    }

    /**
     * 新增首页banner多语言
     * 
     * @param bannerLang 首页banner多语言
     * @return 结果
     */
    @Override
    public int insertBannerLang(BannerLang bannerLang)
    {
        bannerLang.setCreateTime(DateUtils.getNowDate());
        return bannerLangMapper.insertBannerLang(bannerLang);
    }

    /**
     * 修改首页banner多语言
     * 
     * @param bannerLang 首页banner多语言
     * @return 结果
     */
    @Override
    public int updateBannerLang(BannerLang bannerLang)
    {
        bannerLang.setUpdateTime(DateUtils.getNowDate());
        return bannerLangMapper.updateBannerLang(bannerLang);
    }

    /**
     * 批量删除首页banner多语言
     * 
     * @param ids 需要删除的首页banner多语言主键
     * @return 结果
     */
    @Override
    public int deleteBannerLangByIds(Long[] ids)
    {
        return bannerLangMapper.deleteBannerLangByIds(ids);
    }

    /**
     * 删除首页banner多语言信息
     * 
     * @param id 首页banner多语言主键
     * @return 结果
     */
    @Override
    public int deleteBannerLangById(Long id)
    {
        return bannerLangMapper.deleteBannerLangById(id);
    }
}
