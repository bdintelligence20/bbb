package com.ruoyi.website.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "招聘职位实体")
public class JoinUsJobTitleVO {

    private Long id;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

    @ApiModelProperty("职位多语言ID")
    private Long jobTitleLangId;

    @ApiModelProperty("工作地点ID")
    private Long areaId;

    @ApiModelProperty("工作类型ID")
    private Long categoryId;

    @ApiModelProperty("工作类型名称(回显使用)")
    private String categoryName;

    @ApiModelProperty("工作地点名称(回显使用)")
    private String areaName;

    @ApiModelProperty("职位")
    private String jobTitle;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("工作性质")
    private String type;

    @ApiModelProperty("招聘人数")
    private Long numb;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("职位描述ID")
    private Long jobTitleDetailLangId;

    @ApiModelProperty("职位描述")
    private String describe;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
