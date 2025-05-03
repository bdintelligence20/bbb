package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "招聘福利配置列表")
public class JoinUsWelfareListVO {

    @ApiModelProperty("福利名称")
    private String name;

    @ApiModelProperty("福利图片URL")
    private String imageUrl;
}
