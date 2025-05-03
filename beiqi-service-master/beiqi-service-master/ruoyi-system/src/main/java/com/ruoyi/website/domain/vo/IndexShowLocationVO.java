package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "首页展示位实体")
public class IndexShowLocationVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("多语言表ID")
    private Long indexLangId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("描述")
    private String describe;

    @ApiModelProperty("视频地址")
    private String videoUrl;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("链接地址")
    private String linkUrl;

    @ApiModelProperty("展示位置(0:视频 1: Overview 2: Design 3:下载中心 4:Vision)")
    private Integer type;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("图片宽高比(示例:宽/高 16/9)")
    private String aspectRatio;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
