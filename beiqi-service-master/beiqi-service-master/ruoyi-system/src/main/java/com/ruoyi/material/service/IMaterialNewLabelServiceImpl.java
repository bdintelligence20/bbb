package com.ruoyi.material.service;

import com.ruoyi.website.domain.vo.MaterialNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialTypeNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialVO;

import java.util.List;
import java.util.Map;

public interface IMaterialNewLabelServiceImpl {

    public List<MaterialNewLabelVO> selectNewLabel(MaterialVO materialVo);

    int insertNewLabelVO(MaterialNewLabelVO materialNewLabelVO);

    public List<MaterialNewLabelVO> selectTypeNewLabel(MaterialNewLabelVO materialNewLabelVO);
    public Map<String,MaterialTypeNewLabelVO> selectTypeNewLabelS(MaterialNewLabelVO materialNewLabelVO);

    int insertTypeNewLabelVO(MaterialNewLabelVO materialNewLabelVO);
}
