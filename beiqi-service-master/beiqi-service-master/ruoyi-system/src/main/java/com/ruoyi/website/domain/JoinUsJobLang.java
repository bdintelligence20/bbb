package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel(description = "招聘职位多语言")
public class JoinUsJobLang extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("职位ID")
    private Long jobTitleId;

    @ApiModelProperty("工作地点")
    private Long areaId;

    @ApiModelProperty("工作类型")
    private Long categoryId;

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

    private String categoryName;

    private String areaName;
}
