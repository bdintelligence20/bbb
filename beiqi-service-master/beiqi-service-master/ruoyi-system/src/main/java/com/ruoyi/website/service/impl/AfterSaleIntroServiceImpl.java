package com.ruoyi.website.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.AfterSaleContent;
import com.ruoyi.website.domain.AfterSaleContentLang;
import com.ruoyi.website.domain.AfterSaleIntro;
import com.ruoyi.website.domain.AfterSaleIntroLang;
import com.ruoyi.website.domain.vo.AfterSaleContentVO;
import com.ruoyi.website.domain.vo.AfterSaleIntroVO;
import com.ruoyi.website.mapper.AfterSaleIntroLangMapper;
import com.ruoyi.website.mapper.AfterSaleIntroMapper;
import com.ruoyi.website.service.IAfterSaleIntroService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 售后简介列Service业务层处理
 * 
 * @author thh
 * @date 2022-06-10
 */
@Service
public class AfterSaleIntroServiceImpl implements IAfterSaleIntroService
{
    @Autowired
    private AfterSaleIntroMapper afterSaleIntroMapper;

    @Autowired
    private AfterSaleIntroLangMapper afterSaleIntroLangMapper;

    /**
     * 查询售后简介列
     * 
     * @param id 售后简介列主键
     * @return 售后简介列
     */
    @Override
    public List<AfterSaleIntroVO> selectAfterSaleIntroById(Long id) {
        return afterSaleIntroMapper.selectAfterSaleIntroDetailById(id);
    }

    /**
     * 查询售后简介列列表
     *
     * @return 售后简介列
     */
    @Override
    public List<AfterSaleIntroVO> selectAfterSaleIntroList() {
        return afterSaleIntroMapper.selectAfterSaleIntroList("en", null);
    }

    /**
     * 新增售后简介列
     * 
     * @param saleIntroVOS 售后简介列
     * @return 结果
     */
    @Override
    @Transactional
    public int insertAfterSaleIntro(List<AfterSaleIntroVO> saleIntroVOS) {

        if (CollUtil.isEmpty(saleIntroVOS)){
            throw new ServiceException("参数不能为空");
        }

        AfterSaleIntroVO saleIntroVO = saleIntroVOS.get(0);
        AfterSaleIntro intro = new AfterSaleIntro();
        intro.setCreateTime(DateUtils.getNowDate());
        intro.setStatus(saleIntroVO.getStatus());
        intro.setSort(saleIntroVO.getSort());
        int i = afterSaleIntroMapper.insertAfterSaleIntro(intro);

        List<AfterSaleIntroLang> langs = saleIntroVOS.stream().map(item ->{
            AfterSaleIntroLang afterSaleIntroLang = new AfterSaleIntroLang();
            BeanUtils.copyProperties(item, afterSaleIntroLang);
            afterSaleIntroLang.setSaleIntroId(intro.getId());
            afterSaleIntroLang.setCreateTime(DateUtils.getNowDate());
            return afterSaleIntroLang;
        }).collect(Collectors.toList());

        afterSaleIntroLangMapper.batchInsert(langs);
        return i;
    }

    /**
     * 修改售后简介列
     *
     * @return 结果
     */
    @Override
    @Transactional
    public int updateAfterSaleIntro(List<AfterSaleIntroVO> saleIntroVOS) {
        if (CollUtil.isEmpty(saleIntroVOS)){
            throw new ServiceException("参数不能为空");
        }

        afterSaleIntroMapper.deleteAfterSaleIntroById(saleIntroVOS.get(0).getId());

        List<Long> list = saleIntroVOS.stream().map(item -> {
            return item.getSaleIntroLangId();
        }).collect(Collectors.toList());
        afterSaleIntroLangMapper.deleteAfterSaleIntroLangByIds(list);
        return insertAfterSaleIntro(saleIntroVOS);
    }

    /**
     * 批量删除售后简介列
     * 
     * @param ids 需要删除的售后简介列主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteAfterSaleIntroByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new ServiceException("参数不能为空");
        }
        int i = afterSaleIntroMapper.deleteAfterSaleIntroByIds(ids);
        afterSaleIntroLangMapper.deleteByIntroIds(ids);
        return i;
    }

    /**
     * 删除售后简介列信息
     * 
     * @param id 售后简介列主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleIntroById(Long id)
    {
        return afterSaleIntroMapper.deleteAfterSaleIntroById(id);
    }
}
