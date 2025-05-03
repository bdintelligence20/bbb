package com.ruoyi.website.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.JoinUsJobCategory;
import com.ruoyi.website.domain.JoinUsJobCategoryLang;
import com.ruoyi.website.domain.JoinUsWelfare;
import com.ruoyi.website.domain.JoinUsWelfareLang;
import com.ruoyi.website.domain.vo.JoinUsJobCategoryVO;
import com.ruoyi.website.domain.vo.JoinUsWelfareVO;
import com.ruoyi.website.mapper.JoinUsJobCategoryLangMapper;
import com.ruoyi.website.mapper.JoinUsJobCategoryMapper;
import com.ruoyi.website.service.IJoinUsJobCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 工作类型Service业务层处理
 * 
 * @author thh
 * @date 2022-06-14
 */
@Service
public class JoinUsJobCategoryServiceImpl implements IJoinUsJobCategoryService
{
    @Autowired
    private JoinUsJobCategoryMapper joinUsJobCategoryMapper;

    @Autowired
    private JoinUsJobCategoryLangMapper joinUsJobCategoryLangMapper;

    /**
     * 查询工作类型
     * 
     * @param id 工作类型主键
     * @return 工作类型
     */
    @Override
    public List<JoinUsJobCategoryVO> selectJoinUsJobCategoryById(Long id) {
        return joinUsJobCategoryMapper.selectJoinUsJobCategoryById(id);
    }

    /**
     * 查询工作类型列表
     * @return 工作类型
     */
    @Override
    public List<JoinUsJobCategoryVO> selectJoinUsJobCategoryList() {
        return joinUsJobCategoryMapper.selectJoinUsJobCategoryList();
    }

    /**
     * 新增工作类型
     * @return 结果
     */
    @Override
    public int insertJoinUsJobCategory(List<JoinUsJobCategoryVO> categoryVOS) {
        if (CollUtil.isEmpty(categoryVOS)){
            throw new ServiceException("参数不能为空");
        }

        JoinUsJobCategoryVO categoryVO = categoryVOS.get(0);
        JoinUsJobCategory jobCategory = new JoinUsJobCategory();
        jobCategory.setCreateTime(DateUtils.getNowDate());
        jobCategory.setStatus(categoryVO.getStatus());
        jobCategory.setSort(categoryVO.getSort());
        int i = joinUsJobCategoryMapper.insertJoinUsJobCategory(jobCategory);

        List<JoinUsJobCategoryLang> langs = categoryVOS.stream().map(item ->{
            JoinUsJobCategoryLang category = new JoinUsJobCategoryLang();
            BeanUtils.copyProperties(item, category);
            category.setJobCategoryId(jobCategory.getId());
            category.setCreateTime(DateUtils.getNowDate());
            return category;
        }).collect(Collectors.toList());

        joinUsJobCategoryLangMapper.batchInsert(langs);
        return i;
    }

    /**
     * 修改工作类型
     * @return 结果
     */
    @Override
    @Transactional
    public int updateJoinUsJobCategory(List<JoinUsJobCategoryVO> categoryVOS) {
        if (CollUtil.isEmpty(categoryVOS)){
            throw new ServiceException("参数不能为空");
        }
        JoinUsJobCategory jobCategory = new JoinUsJobCategory();
        JoinUsJobCategoryVO categoryVO = categoryVOS.get(0);
        jobCategory.setId(categoryVO.getId());
        jobCategory.setSort(categoryVO.getSort());
        jobCategory.setStatus(categoryVO.getStatus());
        jobCategory.setUpdateTime(new Date());
        int i = joinUsJobCategoryMapper.updateJoinUsJobCategory(jobCategory);

        categoryVOS.stream().forEach(item ->{
            JoinUsJobCategoryLang categoryLang = new JoinUsJobCategoryLang();
            categoryLang.setId(item.getJobCategoryLangId());
            categoryLang.setJobCategoryId(item.getId());
            categoryLang.setName(item.getName());
            categoryLang.setLanguage(item.getLanguage());
            categoryLang.setUpdateTime(new Date());
            joinUsJobCategoryLangMapper.updateJoinUsJobCategoryLang(categoryLang);
        });
        return i;
    }

    /**
     * 批量删除工作类型
     * 
     * @param ids 需要删除的工作类型主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsJobCategoryByIds(List<Long> ids) {
        return joinUsJobCategoryMapper.deleteJoinUsJobCategoryByIds(ids);
    }

    /**
     * 删除工作类型信息
     * 
     * @param id 工作类型主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsJobCategoryById(Long id)
    {
        return joinUsJobCategoryMapper.deleteJoinUsJobCategoryById(id);
    }

    @Override
    public List<JoinUsJobCategoryLang> getJoinUsJobCategoryList(String lang) {
        return joinUsJobCategoryMapper.selectJobCategoryByLang(lang);
    }
}
