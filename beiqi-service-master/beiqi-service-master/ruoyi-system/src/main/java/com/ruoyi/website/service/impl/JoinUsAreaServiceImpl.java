package com.ruoyi.website.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.JoinUsArea;
import com.ruoyi.website.domain.JoinUsAreaLang;
import com.ruoyi.website.domain.vo.JoinUsAreaVO;
import com.ruoyi.website.mapper.JoinUsAreaLangMapper;
import com.ruoyi.website.mapper.JoinUsAreaMapper;
import com.ruoyi.website.service.IJoinUsAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工作地点Service业务层处理
 * 
 * @author thh
 * @date 2022-06-14
 */
@Service
public class JoinUsAreaServiceImpl implements IJoinUsAreaService
{
    @Autowired
    private JoinUsAreaMapper joinUsAreaMapper;

    @Autowired
    private JoinUsAreaLangMapper joinUsAreaLangMapper;

    /**
     * 查询工作地点
     * 
     * @param id 工作地点主键
     * @return 工作地点
     */
    @Override
    public List<JoinUsAreaVO> selectJoinUsAreaById(Long id)
    {
        return joinUsAreaMapper.selectJoinUsAreaById(id);
    }

    /**
     * 查询工作地点列表
     * 
     * @param joinUsArea 工作地点
     * @return 工作地点
     */
    @Override
    public List<JoinUsAreaVO> selectJoinUsAreaList() {
        return joinUsAreaMapper.selectJoinUsAreaList();
    }

    /**
     * 新增工作地点
     * @return 结果
     */
    @Override
    public int insertJoinUsArea(List<JoinUsAreaVO> joinUsAreaVOS) {
        if (CollUtil.isEmpty(joinUsAreaVOS)){
            throw new ServiceException("参数不能为空");
        }

        JoinUsAreaVO areaVO = joinUsAreaVOS.get(0);
        JoinUsArea joinUsArea = new JoinUsArea();
        joinUsArea.setCreateTime(DateUtils.getNowDate());
        joinUsArea.setStatus(areaVO.getStatus());
        joinUsArea.setSort(areaVO.getSort());
        int i = joinUsAreaMapper.insertJoinUsArea(joinUsArea);

        List<JoinUsAreaLang> langs = joinUsAreaVOS.stream().map(item ->{
            JoinUsAreaLang joinUsAreaLang = new JoinUsAreaLang();
            BeanUtils.copyProperties(item, joinUsAreaLang);
            joinUsAreaLang.setAreaId(joinUsArea.getId());
            joinUsAreaLang.setCreateTime(DateUtils.getNowDate());
            return joinUsAreaLang;
        }).collect(Collectors.toList());

        joinUsAreaLangMapper.batchInsert(langs);
        return i;
    }

    /**
     * 修改工作地点
     * 
     * @param areaVOS 工作地点
     * @return 结果
     */
    @Override
    @Transactional
    public int updateJoinUsArea(List<JoinUsAreaVO> areaVOS) {
        if (CollUtil.isEmpty(areaVOS)){
            throw new ServiceException("参数不能为空");
        }

        JoinUsArea joinUsArea = new JoinUsArea();
        JoinUsAreaVO areaVO = areaVOS.get(0);
        joinUsArea.setId(areaVO.getId());
        joinUsArea.setSort(areaVO.getSort());
        joinUsArea.setStatus(areaVO.getStatus());
        joinUsArea.setUpdateTime(new Date());
        int i = joinUsAreaMapper.updateJoinUsArea(joinUsArea);

        areaVOS.stream().forEach(item ->{
            JoinUsAreaLang areaLang = new JoinUsAreaLang();
            areaLang.setId(item.getAreaLangId());
            areaLang.setAreaId(item.getId());
            areaLang.setName(item.getName());
            areaLang.setLanguage(item.getLanguage());
            areaLang.setUpdateTime(new Date());
            joinUsAreaLangMapper.updateJoinUsAreaLang(areaLang);
        });
        return i;
    }

    /**
     * 批量删除工作地点
     * 
     * @param ids 需要删除的工作地点主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsAreaByIds(List<Long> ids)
    {
        return joinUsAreaMapper.deleteJoinUsAreaByIds(ids);
    }

    /**
     * 删除工作地点信息
     * 
     * @param id 工作地点主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsAreaById(Long id)
    {
        return joinUsAreaMapper.deleteJoinUsAreaById(id);
    }

    @Override
    public List<JoinUsAreaLang> getJoinUsAreaList(String lang) {
        return joinUsAreaMapper.selectByLang(lang);
    }
}
