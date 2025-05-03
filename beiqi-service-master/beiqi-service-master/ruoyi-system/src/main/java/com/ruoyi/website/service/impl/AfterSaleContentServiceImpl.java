package com.ruoyi.website.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.index.vo.AfterSaleVO;
import com.ruoyi.system.mapper.AfterSaleCountryMapper;
import com.ruoyi.website.domain.AfterSaleContent;
import com.ruoyi.website.domain.AfterSaleContentLang;
import com.ruoyi.website.domain.AfterSaleIntro;
import com.ruoyi.website.domain.vo.*;
import com.ruoyi.website.mapper.AfterSaleContentLangMapper;
import com.ruoyi.website.mapper.AfterSaleContentMapper;
import com.ruoyi.website.mapper.AfterSaleIntroMapper;
import com.ruoyi.website.mapper.AfterSaleItemMapper;
import com.ruoyi.website.service.IAfterSaleContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 售后文章内容Service业务层处理
 *
 * @date 2022-06-10
 */
@Service
public class AfterSaleContentServiceImpl implements IAfterSaleContentService {
    @Autowired
    private AfterSaleContentMapper afterSaleContentMapper;

    @Autowired
    private AfterSaleContentLangMapper afterSaleContentLangMapper;

    @Autowired
    private AfterSaleItemMapper afterSaleItemMapper;

    @Autowired
    private AfterSaleIntroMapper afterSaleIntroMapper;

    @Autowired
    private AfterSaleCountryMapper afterSaleCountryMapper;

    /**
     * 查询售后文章内容
     * 
     * @param id 售后文章内容主键
     * @return 售后文章内容
     */
    @Override
    public List<AfterSaleContentVO> selectAfterSaleContentById(Long id) {
        return afterSaleContentMapper.selectAfterSaleContentDetail(id);
    }

    /**
     * 查询售后文章内容列表
     *
     * @return 售后文章内容
     */
    @Override
    public List<AfterSaleContentVO> selectAfterSaleContentList() {
        return afterSaleContentMapper.selectAfterSaleContentList("en", null);
    }

    /**
     * 新增售后文章内容
     * 
     * @param contentVOS 售后文章内容
     * @return 结果
     */
    @Override
    @Transactional
    public int insertAfterSaleContent(List<AfterSaleContentVO> contentVOS) {
        if (CollUtil.isEmpty(contentVOS)){
            throw new ServiceException("参数不能为空");
        }

        AfterSaleContentVO afterSaleContentVO = contentVOS.get(0);
        AfterSaleContent afterSaleContent = new AfterSaleContent();
        afterSaleContent.setCreateTime(DateUtils.getNowDate());
        afterSaleContent.setStatus(afterSaleContentVO.getStatus());
        int i = afterSaleContentMapper.insertAfterSaleContent(afterSaleContent);

        List<AfterSaleContentLang> langs = contentVOS.stream().map(item ->{
            AfterSaleContentLang afterSaleContentLang = new AfterSaleContentLang();
            BeanUtils.copyProperties(item, afterSaleContentLang);
            afterSaleContentLang.setSaleContentId(afterSaleContent.getId());
            afterSaleContentLang.setCreateTime(DateUtils.getNowDate());
            return afterSaleContentLang;
        }).collect(Collectors.toList());
        afterSaleContentLangMapper.batchInsert(langs);
        return i;
    }

    /**
     * 修改售后文章内容
     * 
     * @param contentVOS 售后文章内容
     * @return 结果
     */
    @Override
    @Transactional
    public int updateAfterSaleContent(List<AfterSaleContentVO> contentVOS) {
        if (CollUtil.isEmpty(contentVOS)){
            throw new ServiceException("参数不能为空");
        }

        afterSaleContentMapper.deleteAfterSaleContentById(contentVOS.get(0).getId());

        List<Long> list = contentVOS.stream().map(item -> {
            return item.getContentLangId();
        }).collect(Collectors.toList());
        afterSaleContentLangMapper.deleteAfterSaleContentLangByIds(list);
        return insertAfterSaleContent(contentVOS);
    }

    /**
     * 批量删除售后文章内容
     * 
     * @param ids 需要删除的售后文章内容主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteAfterSaleContentByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new ServiceException("参数不能为空");
        }
        afterSaleContentLangMapper.deleteByContentId(ids);
        return afterSaleContentMapper.deleteAfterSaleContentByIds(ids);
    }

    /**
     * 删除售后文章内容信息
     * 
     * @param id 售后文章内容主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleContentById(Long id)
    {
        return afterSaleContentMapper.deleteAfterSaleContentById(id);
    }

    @Override
    public AfterSaleVO getAfterSaleContent(String lang) {
        if (StrUtil.isEmpty(lang)){
            throw new ServiceException("参数不能为空");
        }
        AfterSaleVO afterSaleVO = new AfterSaleVO();
        List<AfterSaleContentVO> contentVOS = afterSaleContentMapper.selectAfterSaleContentList(lang, 0);
        if (CollUtil.isNotEmpty(contentVOS)){
            AfterSaleContentVO afterSaleContentVO = contentVOS.get(0);
            afterSaleVO.setContent(afterSaleContentVO.getContent());
        }

        List<AfterSaleIntroVO> introVOS = afterSaleIntroMapper.selectAfterSaleIntroList(lang, 0);
        if (CollUtil.isNotEmpty(introVOS)){
            AfterSaleIntroVO saleIntroVO = introVOS.get(0);
            afterSaleVO.setTitle(saleIntroVO.getTitle());
            afterSaleVO.setTitleDescribe(saleIntroVO.getTitleDescribe());
            List<com.ruoyi.index.vo.AfterSaleIntroVO> afterSaleIntroVOS = new ArrayList<>();
            introVOS.forEach(item ->{
                com.ruoyi.index.vo.AfterSaleIntroVO introVO = new com.ruoyi.index.vo.AfterSaleIntroVO();
                BeanUtils.copyProperties(item, introVO);
                afterSaleIntroVOS.add(introVO);
            });
            afterSaleVO.setIntros(afterSaleIntroVOS);
        }

        List<AfterSaleItemVO> itemVOS = afterSaleItemMapper.selectAfterSaleItemList(lang, 0);
        if (CollUtil.isNotEmpty(itemVOS)){
            List<com.ruoyi.index.vo.AfterSaleItemVO> items = new ArrayList<>();
            itemVOS.forEach(item ->{
                com.ruoyi.index.vo.AfterSaleItemVO itemVO = new com.ruoyi.index.vo.AfterSaleItemVO();
                BeanUtils.copyProperties(item, itemVO);
                items.add(itemVO);
            });
            afterSaleVO.setItems(items);
        }
        return afterSaleVO;
    }

    @Override
    public List<AfterSaleCountryVO> getAfterSaleCountry(String lang) {
        return afterSaleCountryMapper.selectByLang(lang);
    }
}
