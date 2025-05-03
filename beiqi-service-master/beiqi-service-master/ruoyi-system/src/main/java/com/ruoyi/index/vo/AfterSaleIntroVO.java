package com.ruoyi.index.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("简介列表")
public class AfterSaleIntroVO {

    @ApiModelProperty("标题")
    private String subTitle;

    /** 二级描述 */
    @ApiModelProperty("描述")
    private String subTitleDescribe;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    private String imageUrl;
}
