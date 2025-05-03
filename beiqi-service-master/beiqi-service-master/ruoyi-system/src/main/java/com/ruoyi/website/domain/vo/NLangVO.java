package com.ruoyi.website.domain.vo;


import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "新闻多语言实体")
public class NLangVO extends BaseEntity {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("新闻内容")
    private String content;

    @ApiModelProperty("概述")
    private String summarize;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("列表图")
    private String imageUrl;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("是否启用 1启用 2隐藏")
    private Integer enable;
}
