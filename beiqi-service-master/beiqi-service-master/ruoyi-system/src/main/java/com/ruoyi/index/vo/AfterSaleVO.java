package com.ruoyi.index.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("售后")
public class AfterSaleVO {

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("一级标题")
    private String title;

    @ApiModelProperty("一级描述")
    private String titleDescribe;

    @ApiModelProperty("简介列表")
    private List<AfterSaleIntroVO> intros;

    @ApiModelProperty("售后服务项")
    private List<AfterSaleItemVO> items;


}
