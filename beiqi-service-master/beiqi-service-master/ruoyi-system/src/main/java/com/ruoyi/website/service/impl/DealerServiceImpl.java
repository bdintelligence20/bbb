package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.Dealer;
import com.ruoyi.website.mapper.DealerMapper;
import com.ruoyi.website.service.IDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 经销商Service业务层处理
 * 
 * @author thh
 * @date 2022-06-01
 */
@Service
public class DealerServiceImpl implements IDealerService
{
    @Autowired
    private DealerMapper dealerMapper;

    /**
     * 查询经销商
     * 
     * @param id 经销商主键
     * @return 经销商
     */
    @Override
    public Dealer selectDealerById(Long id)
    {
        return dealerMapper.selectDealerById(id);
    }

    /**
     * 查询经销商列表
     * 
     * @param dealer 经销商
     * @return 经销商
     */
    @Override
    public List<Dealer> selectDealerList(Dealer dealer)
    {
        return dealerMapper.selectDealerList(dealer);
    }

    /**
     * 新增经销商
     * 
     * @param dealer 经销商
     * @return 结果
     */
    @Override
    public int insertDealer(Dealer dealer)
    {
        dealer.setCreateTime(DateUtils.getNowDate());
        return dealerMapper.insertDealer(dealer);
    }

    /**
     * 修改经销商
     * 
     * @param dealer 经销商
     * @return 结果
     */
    @Override
    public int updateDealer(Dealer dealer)
    {
        dealer.setUpdateTime(DateUtils.getNowDate());
        return dealerMapper.updateDealer(dealer);
    }

    /**
     * 批量删除经销商
     * 
     * @param ids 需要删除的经销商主键
     * @return 结果
     */
    @Override
    public int deleteDealerByIds(Long[] ids)
    {
        return dealerMapper.deleteDealerByIds(ids);
    }

    /**
     * 删除经销商信息
     * 
     * @param id 经销商主键
     * @return 结果
     */
    @Override
    public int deleteDealerById(Long id)
    {
        return dealerMapper.deleteDealerById(id);
    }
}
