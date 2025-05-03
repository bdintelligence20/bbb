package com.ruoyi.website.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.domain.History;
import com.ruoyi.website.domain.HistoryLang;
import com.ruoyi.website.domain.vo.HistoryLangVO;
import com.ruoyi.website.mapper.HistoryLangMapper;
import com.ruoyi.website.mapper.HistoryMapper;
import com.ruoyi.website.service.IHistoryService;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 历史Service业务层处理
 * 
 * @author thh
 * @date 2022-06-09
 */
@Service
public class HistoryServiceImpl implements IHistoryService
{
    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private HistoryLangMapper historyLangMapper;

    /**
     * 查询历史
     * 
     * @param id 历史主键
     * @return 历史
     */
    @Override
    public List<HistoryLangVO> selectHistoryById(Long id) {
        if (ObjectUtil.isEmpty(id)){
            throw new ServiceException("参数不能为空");
        }
        List<HistoryLangVO> historyLangs = new ArrayList<>();
        History history = historyMapper.selectHistoryById(id);
        HistoryLang historyLang = new HistoryLang();
        historyLang.setHistoryId(id);
        List<HistoryLang> langs = historyLangMapper.selectHistoryLangList(historyLang);
        langs.forEach(item ->{
            HistoryLangVO historyLangVO = new HistoryLangVO();
            BeanUtils.copyProperties(item, historyLangVO);
            historyLangVO.setSort(history.getSort());
            historyLangs.add(historyLangVO);
        });

        HistoryLangVO historyLangVO = new HistoryLangVO();
        BeanUtils.copyProperties(history, historyLangVO);
        historyLangVO.setLanguage("en");
        historyLangs.add(historyLangVO);
        return historyLangs;


    }

    /**
     * 查询历史列表
     * 
     * @param history 历史
     * @return 历史
     */
    @Override
    public List<History> selectHistoryList(History history)
    {
        return historyMapper.selectHistoryList(history);
    }

    /**
     * 新增历史
     * 
     * @param historyLangs 历史
     * @return 结果
     */
    @Override
    public int insertHistory(List<HistoryLang> historyLangs) {
        if (CollUtil.isEmpty(historyLangs)){
            throw new ServiceException("参数不能为空");
        }
        History history = new History();
        ArrayList<HistoryLang> langs = new ArrayList<>();
        historyLangs.forEach(item ->{
            item.setCreateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(item.getLanguage())){
                BeanUtils.copyProperties(item, history);
            }else {
                HistoryLang historyLang = new HistoryLang();
                BeanUtils.copyProperties(item, historyLang);
                langs.add(historyLang);
            }
        });
        int i = historyMapper.insertHistory(history);
        if (CollUtil.isNotEmpty(langs)){
            langs.forEach(item->{ item.setHistoryId(history.getId());});
            historyLangMapper.batchInsert(langs);
        }
        return i;
    }

    /**
     * 修改历史
     * 
     * @param historyLangs 历史
     * @return 结果
     */
    @Override
    public int updateHistory(List<HistoryLang> historyLangs) {
        if (CollUtil.isEmpty(historyLangs)){
            throw new ServiceException("参数不能为空");
        }
        History history = new History();
        historyLangs.forEach(item ->{
            item.setUpdateTime(DateUtils.getNowDate());
            if ("en".equalsIgnoreCase(item.getLanguage())){
                BeanUtils.copyProperties(item, history);
            }else {
                HistoryLang historyLang = new HistoryLang();
                BeanUtils.copyProperties(item, historyLang);
                historyLangMapper.updateHistoryLang(historyLang);
            }
        });
        return historyMapper.updateHistory(history);
    }

    /**
     * 批量删除历史
     * 
     * @param ids 需要删除的历史主键
     * @return 结果
     */
    @Override
    public int deleteHistoryByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new ServiceException("参数不能为空");
        }
        historyLangMapper.deleteByHistoryId(ids);
        return historyMapper.deleteHistoryByIds(ids);
    }

    /**
     * 删除历史信息
     * 
     * @param id 历史主键
     * @return 结果
     */
    @Override
    public int deleteHistoryById(Long id) {
        return historyMapper.deleteHistoryById(id);
    }

    @Override
    public List<History> getHistoryList(String lang) {
        if (StrUtil.isBlank(lang)){
            throw new ServiceException("语种不能为空");
        }
        ArrayList<History> histories = new ArrayList<>();
        if ("en".equalsIgnoreCase(lang)){
            histories.addAll(historyMapper.selectHistoryList(null));
        }else {
            HistoryLang historyLang = new HistoryLang();
            historyLang.setLanguage(lang);

            historyLangMapper.selectList(historyLang).forEach(item ->{
                History history = new History();
                BeanUtils.copyProperties(item, history);
                histories.add(history);
            });
        }
        return histories;
    }
}
