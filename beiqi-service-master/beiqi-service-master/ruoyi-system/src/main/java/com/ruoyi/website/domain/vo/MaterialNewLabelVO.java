package com.ruoyi.website.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.material.domain.Material;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MaterialNewLabelVO extends Material {
    private static final long serialVersionUID = 1L;

    /** 素材ID */
    private Long materialId;

    private Long userId;

    private String type;

    private String model;

    @ApiModelProperty("new标 0为展示 1为隐藏")
    private String newLabelFlag;
}
