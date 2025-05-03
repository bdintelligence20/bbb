package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("售后底部文章列表实体")
public class AfterSaleIntroVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

    @ApiModelProperty("简介ID")
    private Long saleIntroLangId;

    @ApiModelProperty("一级标题")
    private String title;

    @ApiModelProperty("一级描述")
    private String titleDescribe;

    @ApiModelProperty("二级标题")
    private String subTitle;

    @ApiModelProperty("二级描述")
    private String subTitleDescribe;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
