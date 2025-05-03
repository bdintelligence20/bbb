package com.ruoyi.material.mapper;

import com.ruoyi.material.domain.Material;
import com.ruoyi.website.domain.vo.MaterialNewLabelVO;
import com.ruoyi.website.domain.vo.MaterialVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 素材Mapper接口
 * 
 * @author thh
 * @date 2023-08-03
 */
public interface MaterialTypeNewLabelMapper
{
    public int insertPyteNewLabelVO(MaterialNewLabelVO materialNewLabelVO);

    List<MaterialNewLabelVO> selectTypeNewLabel(MaterialNewLabelVO materialNewLabelVO);

    int delectTypeNewLabel(Material material);

    int delectTypeNewLabels(MaterialNewLabelVO materialNewLabelVOList);
    //int delectTypeNewLabels(@Param("list")List<MaterialNewLabelVO> list);

}
