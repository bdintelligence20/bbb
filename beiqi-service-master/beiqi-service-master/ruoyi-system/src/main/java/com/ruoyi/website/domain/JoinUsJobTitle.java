package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel(description = "招聘职位")
public class JoinUsJobTitle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;
}
