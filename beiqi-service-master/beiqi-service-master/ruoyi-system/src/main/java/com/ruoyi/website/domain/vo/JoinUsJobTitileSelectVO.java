package com.ruoyi.website.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "招聘查询实体")
public class JoinUsJobTitileSelectVO {

    @ApiModelProperty("工作地点")
    private String areaId;

    @ApiModelProperty("工作类型")
    private String categoryId;

    @ApiModelProperty("职位")
    private String jobTitle;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("工作性质")
    private String type;
}
