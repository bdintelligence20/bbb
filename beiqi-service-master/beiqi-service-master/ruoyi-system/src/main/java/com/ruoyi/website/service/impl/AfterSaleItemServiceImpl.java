package com.ruoyi.website.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.AfterSaleIntro;
import com.ruoyi.website.domain.AfterSaleIntroLang;
import com.ruoyi.website.domain.AfterSaleItem;
import com.ruoyi.website.domain.AfterSaleItemLang;
import com.ruoyi.website.domain.vo.AfterSaleIntroVO;
import com.ruoyi.website.domain.vo.AfterSaleItemVO;
import com.ruoyi.website.mapper.AfterSaleItemLangMapper;
import com.ruoyi.website.mapper.AfterSaleItemMapper;
import com.ruoyi.website.service.IAfterSaleItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 售后服务项Service业务层处理
 * 
 * @author thh
 * @date 2022-06-10
 */
@Service
public class AfterSaleItemServiceImpl implements IAfterSaleItemService
{
    @Autowired
    private AfterSaleItemMapper afterSaleItemMapper;

    @Autowired
    private AfterSaleItemLangMapper afterSaleItemLangMapper;

    /**
     * 查询售后服务项
     * 
     * @param id 售后服务项主键
     * @return 售后服务项
     */
    @Override
    public List<AfterSaleItemVO> selectAfterSaleItemById(Long id) {
        return afterSaleItemMapper.selectAfterSaleItemDetail(id);
    }

    /**
     * 查询售后服务项列表
     * @return 售后服务项
     */
    @Override
    public List<AfterSaleItemVO> selectAfterSaleItemList() {
        return afterSaleItemMapper.selectAfterSaleItemList("en", null);
    }

    /**
     * 新增售后服务项
     * 
     * @return 结果
     */
    @Override
    public int insertAfterSaleItem(List<AfterSaleItemVO> saleItemVOS) {

        if (CollUtil.isEmpty(saleItemVOS)){
            throw new ServiceException("参数不能为空");
        }

        AfterSaleItemVO saleItemVO = saleItemVOS.get(0);
        AfterSaleItem saleItem = new AfterSaleItem();
        saleItem.setCreateTime(DateUtils.getNowDate());
        saleItem.setStatus(saleItemVO.getStatus());
        saleItem.setSort(saleItemVO.getSort());
        int i = afterSaleItemMapper.insertAfterSaleItem(saleItem);

        List<AfterSaleItemLang> langs = saleItemVOS.stream().map(item ->{
            AfterSaleItemLang afterSaleItemLang = new AfterSaleItemLang();
            BeanUtils.copyProperties(item, afterSaleItemLang);
            afterSaleItemLang.setSaleItemId(saleItem.getId());
            afterSaleItemLang.setCreateTime(DateUtils.getNowDate());
            return afterSaleItemLang;
        }).collect(Collectors.toList());

        afterSaleItemLangMapper.batchInsert(langs);
        return i;
    }

    /**
     * 修改售后服务项
     * @return 结果
     */
    @Override
    public int updateAfterSaleItem(List<AfterSaleItemVO> saleItemVOS) {
        if (CollUtil.isEmpty(saleItemVOS)){
            throw new ServiceException("参数不能为空");
        }

        afterSaleItemMapper.deleteAfterSaleItemById(saleItemVOS.get(0).getId());

        List<Long> list = saleItemVOS.stream().map(item -> {
            return item.getSaleItemLangId();
        }).collect(Collectors.toList());
        afterSaleItemLangMapper.deleteAfterSaleItemLangByIds(list);
        return insertAfterSaleItem(saleItemVOS);
    }

    /**
     * 批量删除售后服务项
     * 
     * @param ids 需要删除的售后服务项主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleItemByIds(List<Long> ids) {
        afterSaleItemLangMapper.deleteAfterSaleItemLangByItemId(ids);
        return afterSaleItemMapper.deleteAfterSaleItemByIds(ids);
    }

    /**
     * 删除售后服务项信息
     * 
     * @param id 售后服务项主键
     * @return 结果
     */
    @Override
    public int deleteAfterSaleItemById(Long id)
    {
        return afterSaleItemMapper.deleteAfterSaleItemById(id);
    }
}
