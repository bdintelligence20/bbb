package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("岗位具体信息")
public class JoinUsJobTitleContentVO {

    @ApiModelProperty("工作地点")
    private String areaName;

    @ApiModelProperty("工作类型")
    private String categoryName;

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

    @ApiModelProperty("职位描述")
    private String describe;
}
