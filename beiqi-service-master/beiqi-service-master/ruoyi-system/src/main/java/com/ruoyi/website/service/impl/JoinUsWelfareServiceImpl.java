package com.ruoyi.website.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.AfterSaleItem;
import com.ruoyi.website.domain.AfterSaleItemLang;
import com.ruoyi.website.domain.JoinUsWelfare;
import com.ruoyi.website.domain.JoinUsWelfareLang;
import com.ruoyi.website.domain.vo.AfterSaleItemVO;
import com.ruoyi.website.domain.vo.JoinUsWelfareListVO;
import com.ruoyi.website.domain.vo.JoinUsWelfareVO;
import com.ruoyi.website.mapper.JoinUsWelfareLangMapper;
import com.ruoyi.website.mapper.JoinUsWelfareMapper;
import com.ruoyi.website.service.IJoinUsWelfareService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 招聘福利配置Service业务层处理
 * 
 * @author thh
 * @date 2022-06-14
 */
@Service
public class JoinUsWelfareServiceImpl implements IJoinUsWelfareService
{
    @Autowired
    private JoinUsWelfareMapper joinUsWelfareMapper;


    @Autowired
    private JoinUsWelfareLangMapper joinUsWelfareLangMapper;

    /**
     * 查询招聘福利配置
     * 
     * @param id 招聘福利配置主键
     * @return 招聘福利配置
     */
    @Override
    public List<JoinUsWelfareVO> selectJoinUsWelfareById(Long id) {
        return joinUsWelfareMapper.selectJoinUsWelfareById(id);
    }

    /**
     * 查询招聘福利配置列表
     * @return 招聘福利配置
     */
    @Override
    public List<JoinUsWelfareVO> selectJoinUsWelfareList() {
        return joinUsWelfareMapper.selectJoinUsWelfareList();
    }

    /**
     * 新增招聘福利配置
     *
     * @return 结果
     */
    @Override
    public int insertJoinUsWelfare(List<JoinUsWelfareVO> joinUsWelfareVOS) {
        if (CollUtil.isEmpty(joinUsWelfareVOS)){
            throw new ServiceException("参数不能为空");
        }

        JoinUsWelfareVO joinUsWelfareVO = joinUsWelfareVOS.get(0);
        JoinUsWelfare joinUsWelfare = new JoinUsWelfare();
        joinUsWelfare.setCreateTime(DateUtils.getNowDate());
        joinUsWelfare.setStatus(joinUsWelfareVO.getStatus());
        joinUsWelfare.setSort(joinUsWelfareVO.getSort());
        int i = joinUsWelfareMapper.insertJoinUsWelfare(joinUsWelfare);

        List<JoinUsWelfareLang> langs = joinUsWelfareVOS.stream().map(item ->{
            JoinUsWelfareLang welfareLang = new JoinUsWelfareLang();
            BeanUtils.copyProperties(item, welfareLang);
            welfareLang.setWelfareId(joinUsWelfare.getId());
            welfareLang.setCreateTime(DateUtils.getNowDate());
            return welfareLang;
        }).collect(Collectors.toList());

        joinUsWelfareLangMapper.batchInsert(langs);
        return i;
    }

    /**
     * 修改招聘福利配置
     * @return 结果
     */
    @Override
    @Transactional
    public int updateJoinUsWelfare(List<JoinUsWelfareVO> joinUsWelfareVOS) {
        if (CollUtil.isEmpty(joinUsWelfareVOS)){
            throw new ServiceException("参数不能为空");
        }

        if (CollUtil.isEmpty(joinUsWelfareVOS)){
            throw new ServiceException("参数不能为空");
        }

        joinUsWelfareMapper.deleteJoinUsWelfareById(joinUsWelfareVOS.get(0).getId());

        List<Long> list = joinUsWelfareVOS.stream().map(item -> {
            return item.getWelfareLangId();
        }).collect(Collectors.toList());
        joinUsWelfareLangMapper.deleteJoinUsWelfareLangByIds(list);
        return insertJoinUsWelfare(joinUsWelfareVOS);
    }

    /**
     * 批量删除招聘福利配置
     * 
     * @param ids 需要删除的招聘福利配置主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsWelfareByIds(List<Long> ids)
    {
        return joinUsWelfareMapper.deleteJoinUsWelfareByIds(ids);
    }

    /**
     * 删除招聘福利配置信息
     * 
     * @param id 招聘福利配置主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsWelfareById(Long id)
    {
        return joinUsWelfareMapper.deleteJoinUsWelfareById(id);
    }

    @Override
    public List<JoinUsWelfareListVO> getWelfare(String lang) {
        return joinUsWelfareMapper.selectByLang(lang);
    }
}
