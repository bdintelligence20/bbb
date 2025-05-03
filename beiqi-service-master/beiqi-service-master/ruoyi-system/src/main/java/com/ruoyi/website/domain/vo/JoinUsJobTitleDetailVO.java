package com.ruoyi.website.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "岗位详情")
public class JoinUsJobTitleDetailVO {
    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

    List<JoinUsJobTitleContentVO> details;
}
