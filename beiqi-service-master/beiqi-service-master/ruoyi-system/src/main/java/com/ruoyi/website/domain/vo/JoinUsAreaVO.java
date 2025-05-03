package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "招聘区域实体")
public class JoinUsAreaVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示 0=显示,1=隐藏")
    private Integer status;

    @ApiModelProperty("工作地点ID")
    private Long areaLangId;

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
