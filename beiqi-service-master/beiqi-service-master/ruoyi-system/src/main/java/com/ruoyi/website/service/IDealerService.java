package com.ruoyi.website.service;

import com.ruoyi.website.domain.Dealer;

import java.util.List;

/**
 * 经销商Service接口
 * 
 * @author thh
 * @date 2022-06-01
 */
public interface IDealerService 
{
    /**
     * 查询经销商
     * 
     * @param id 经销商主键
     * @return 经销商
     */
    public Dealer selectDealerById(Long id);

    /**
     * 查询经销商列表
     * 
     * @param dealer 经销商
     * @return 经销商集合
     */
    public List<Dealer> selectDealerList(Dealer dealer);

    /**
     * 新增经销商
     * 
     * @param dealer 经销商
     * @return 结果
     */
    public int insertDealer(Dealer dealer);

    /**
     * 修改经销商
     * 
     * @param dealer 经销商
     * @return 结果
     */
    public int updateDealer(Dealer dealer);

    /**
     * 批量删除经销商
     * 
     * @param ids 需要删除的经销商主键集合
     * @return 结果
     */
    public int deleteDealerByIds(Long[] ids);

    /**
     * 删除经销商信息
     * 
     * @param id 经销商主键
     * @return 结果
     */
    public int deleteDealerById(Long id);
}
