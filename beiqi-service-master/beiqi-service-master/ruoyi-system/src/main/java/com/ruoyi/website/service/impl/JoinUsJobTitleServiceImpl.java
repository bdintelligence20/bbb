package com.ruoyi.website.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.*;
import com.ruoyi.website.domain.vo.*;
import com.ruoyi.website.mapper.*;
import com.ruoyi.website.service.IJoinUsJobTitleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 职位Service业务层处理
 * 
 * @author thh
 * @date 2022-06-15
 */
@Service
public class JoinUsJobTitleServiceImpl implements IJoinUsJobTitleService
{
    @Autowired
    private JoinUsJobTitleMapper joinUsJobTitleMapper;

    @Autowired
    private JoinUsJobLangMapper joinUsJobLangMapper;

    @Autowired
    private JoinUsJobDetailsLangMapper joinUsJobDetailsLangMapper;

    @Autowired
    private JoinUsJobCategoryLangMapper joinUsJobCategoryLangMapper;

    @Autowired
    private JoinUsAreaLangMapper joinUsAreaMapper;

    /**
     * 查询职位
     * 
     * @param id 职位主键
     * @return 职位
     */
    @Override
    public List<JoinUsJobTitleVO> selectJoinUsJobTitleById(Long id) {
        List<JoinUsJobTitleVO> jobTitleVOS = joinUsJobTitleMapper.selectJoinUsJobTitleById(id);

        JoinUsJobDetailsLang joinUsJobDetailsLang = new JoinUsJobDetailsLang();
        joinUsJobDetailsLang.setJobTitleId(id);
        List<JoinUsJobDetailsLang> jobDetailsLangs = joinUsJobDetailsLangMapper.selectJoinUsJobDetailsLangList(joinUsJobDetailsLang);
        if (CollUtil.isNotEmpty(jobDetailsLangs)){
            Map<String, JoinUsJobDetailsLang> detailMap = jobDetailsLangs.stream().collect(Collectors.toMap(JoinUsJobDetailsLang::getLanguage, Function.identity()));
            jobTitleVOS.forEach(item ->{
                JoinUsJobDetailsLang jobDetailsLang = detailMap.get(item.getLanguage());
                if (ObjectUtil.isNotNull(jobDetailsLang)){
                    item.setJobTitleDetailLangId(jobDetailsLang.getId());
                    item.setDescribe(jobDetailsLang.getDescribe());
                }
            });
        }
        return jobTitleVOS;
    }

    /**
     * 查询职位列表
     * @return 职位
     */
    @Override
    public List<JoinUsJobTitleVO> selectJoinUsJobTitleList(JoinUsJobTitileSelectVO selectVO) {
        return joinUsJobTitleMapper.selectJoinUsJobTitleList(selectVO);
    }

    /**
     * 新增职位
     * @return 结果
     */
    @Override
    @Transactional
    public int insertJoinUsJobTitle(List<JoinUsJobTitleVO> titleVOS) {
        if (CollUtil.isEmpty(titleVOS)){
            throw new ServiceException("参数不能为空");
        }
        JoinUsJobTitleVO jobTitleVO = titleVOS.get(0);

        JoinUsJobTitle joinUsJobTitle = new JoinUsJobTitle();
        BeanUtils.copyProperties(jobTitleVO, joinUsJobTitle);
        joinUsJobTitle.setCreateTime(DateUtils.getNowDate());
        int i = joinUsJobTitleMapper.insertJoinUsJobTitle(joinUsJobTitle);

        List<JoinUsJobLang> jobLangs = new ArrayList<>();
        List<JoinUsJobDetailsLang> detailsLangs = new ArrayList<>();
        titleVOS.forEach(item ->{
            item.setCreateTime(DateUtils.getNowDate());
            JoinUsJobLang joinUsJobLang = new JoinUsJobLang();
            BeanUtils.copyProperties(item, joinUsJobLang);
            joinUsJobLang.setJobTitleId(joinUsJobTitle.getId());
            JoinUsAreaLang areaName = getAreaName(item.getAreaId(), item.getLanguage());
            if (ObjectUtil.isNotEmpty(areaName)){
                joinUsJobLang.setAreaName(areaName.getName());

            }
            JoinUsJobCategoryLang categoryName = getCategoryName(item.getCategoryId(), item.getLanguage());
            if (ObjectUtil.isNotEmpty(categoryName)){
                joinUsJobLang.setCategoryName(categoryName.getName());
            }
            jobLangs.add(joinUsJobLang);

            JoinUsJobDetailsLang joinUsJobDetailsLang = new JoinUsJobDetailsLang();
            BeanUtils.copyProperties(item, joinUsJobDetailsLang);
            joinUsJobDetailsLang.setJobTitleId(joinUsJobTitle.getId());
            detailsLangs.add(joinUsJobDetailsLang);
        });

        if (CollUtil.isNotEmpty(jobLangs)){
            joinUsJobLangMapper.batchInsert(jobLangs);
        }

        if (CollUtil.isNotEmpty(detailsLangs)){
            joinUsJobDetailsLangMapper.batchInsert(detailsLangs);
        }
        return i;
    }

    /**
     * 修改职位
     * @return 结果
     */
    @Override
    @Transactional
    public int updateJoinUsJobTitle(List<JoinUsJobTitleVO> titleVOS) {
        if (CollUtil.isEmpty(titleVOS)){
            throw new ServiceException("参数不能为空");
        }
        joinUsJobTitleMapper.deleteJoinUsJobTitleById(titleVOS.get(0).getId());

        List<Long> titleLangs = new ArrayList<>();
        List<Long> detailsLangs = new ArrayList<>();

        titleVOS.forEach(item ->{
            titleLangs.add(item.getJobTitleLangId());
            detailsLangs.add(item.getJobTitleDetailLangId());
        });

        joinUsJobLangMapper.deleteJoinUsJobLangByIds(titleLangs);
        joinUsJobDetailsLangMapper.deleteJoinUsJobDetailsLangByIds(detailsLangs);

        return insertJoinUsJobTitle(titleVOS);
    }

    /**
     * 批量删除职位
     * 
     * @param ids 需要删除的职位主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteJoinUsJobTitleByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new ServiceException("参数不能为空");
        }
        int i = joinUsJobTitleMapper.deleteJoinUsJobTitleByIds(ids);
        joinUsJobLangMapper.deleteByJobTitleIds(ids);
        joinUsJobDetailsLangMapper.deleteByTitleIds(ids);
        return i;
    }

    /**
     * 删除职位信息
     * 
     * @param id 职位主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsJobTitleById(Long id)
    {
        return joinUsJobTitleMapper.deleteJoinUsJobTitleById(id);
    }

    @Override
    public List<JobTitleListVO> getPage(String lang, Long areaId, Long categoryId) {
        return joinUsJobTitleMapper.selectJobTitleList(lang, areaId, categoryId);
    }

    @Override
    public JoinUsJobTitleVO getJobDetail(String lang, Long id) {
        return joinUsJobTitleMapper.selectByIdAndLang(lang, id);
    }

    private JoinUsJobCategoryLang getCategoryName(Long id, String lang){
        JoinUsJobCategoryLang categoryLang = new JoinUsJobCategoryLang();
        categoryLang.setJobCategoryId(id);
        categoryLang.setLanguage(lang);
        List<JoinUsJobCategoryLang> categoryLangs = joinUsJobCategoryLangMapper.selectJoinUsJobCategoryLangList(categoryLang);
        if (CollUtil.isNotEmpty(categoryLangs)){
            return categoryLangs.get(0);
        }
        return null;
    }

    private JoinUsAreaLang getAreaName(Long id, String lang){
        JoinUsAreaLang joinUsAreaLang = new JoinUsAreaLang();
        joinUsAreaLang.setAreaId(id);
        joinUsAreaLang.setLanguage(lang);
        List<JoinUsAreaLang> areaVOS = joinUsAreaMapper.selectJoinUsAreaLangList(joinUsAreaLang);
        if (CollUtil.isNotEmpty(areaVOS)){
            return areaVOS.get(0);
        }
        return null;
    }
}
