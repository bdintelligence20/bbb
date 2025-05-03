package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "售后问卷国家列表")
public class AfterSaleCountryVO {

    @ApiModelProperty("国家名称")
    private String name;

    @ApiModelProperty("链接")
    private String linkUrl;
}
