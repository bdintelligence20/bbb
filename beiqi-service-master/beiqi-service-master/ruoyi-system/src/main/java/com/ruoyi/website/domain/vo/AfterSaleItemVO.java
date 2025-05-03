package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("售后项实体")
public class AfterSaleItemVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("简介ID")
    private Long saleItemLangId;

    @ApiModelProperty("标题")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("语种")
    private String language;
}
