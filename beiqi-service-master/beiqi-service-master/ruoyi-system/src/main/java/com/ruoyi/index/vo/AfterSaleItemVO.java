package com.ruoyi.index.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("售后项")
public class AfterSaleItemVO {

    @ApiModelProperty("标题")
    private String name;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    private String imageUrl;
}
