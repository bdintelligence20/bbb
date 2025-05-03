package com.ruoyi.website.domain.vo;

import com.ruoyi.material.domain.Material;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MaterialTypeNewLabelVO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("new标 0为展示 1为隐藏")
    private String newLabelFlag;

    List<MaterialNewLabelVO> list;
}
