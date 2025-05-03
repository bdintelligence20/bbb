package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("售后顶部文案实体")
public class AfterSaleContentVO {

    /** ID */
    @ApiModelProperty("多语言ID")
    private Long id;

    /** 是否展示（0显示 1隐藏） */
    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

    /** 内容ID */
    @ApiModelProperty("内容多语言表ID")
    private Long contentLangId;

    /** 语种 */
    @ApiModelProperty("语种")
    private String language;

    /** 内容 */
    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
