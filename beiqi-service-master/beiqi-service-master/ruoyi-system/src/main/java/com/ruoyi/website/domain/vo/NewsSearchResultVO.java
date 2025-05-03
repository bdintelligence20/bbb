package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("搜索结果实体")
public class NewsSearchResultVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("概述")
    private String summarize;

    @ApiModelProperty("列表图")
    private String imageUrl;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("导航ID")
    private Long categoryId;

    @ApiModelProperty("创建时间")
    private Date publishTime;

}
