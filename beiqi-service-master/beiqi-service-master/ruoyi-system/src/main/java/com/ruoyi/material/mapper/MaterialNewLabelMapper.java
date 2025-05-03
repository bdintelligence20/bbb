package com.ruoyi.material.mapper;

import com.ruoyi.material.domain.Material;
import com.ruoyi.website.domain.vo.MaterialNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialVO;

import java.util.List;

/**
 * 素材Mapper接口
 * 
 * @author thh
 * @date 2023-08-03
 */
public interface MaterialNewLabelMapper
{
    public List<MaterialNewLabelVO> selectNewLabel(MaterialVO materialVo);

    public int insertNewLabelVO(MaterialNewLabelVO materialNewLabelVO);

    public List<MaterialNewLabelVO> selectTypeNewLabel(MaterialNewLabelVO materialNewLabelVO);
    public List<MaterialNewLabelVO> selectMaterialNewLabel(MaterialNewLabelVO materialNewLabelVO);

}
