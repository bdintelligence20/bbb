package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel(description = "招聘要求多语言")
public class JoinUsJobDetailsLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("职位ID")
    private Long jobTitleId;

    @ApiModelProperty("职位描述")
    private String describe;

    @ApiModelProperty("语种")
    private String language;
}
