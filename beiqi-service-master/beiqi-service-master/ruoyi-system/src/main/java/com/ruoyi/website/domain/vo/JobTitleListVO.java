package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("岗位列表")
public class JobTitleListVO {

    private Long id;

    @ApiModelProperty("职位")
    private String jobTitle;

    @ApiModelProperty("工作性质")
    private String type;

    @ApiModelProperty("招聘人数")
    private Integer numb;

    @ApiModelProperty("工作地点")
    private String areaName;
}
