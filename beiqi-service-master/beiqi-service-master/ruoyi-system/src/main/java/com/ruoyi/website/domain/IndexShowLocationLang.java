package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel(description = "首页展示位实体")
public class IndexShowLocationLang extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("")
    private Long indexId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("描述")
    private String describe;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("视频地址")
    private String videoUrl;

    @ApiModelProperty("链接地址")
    private String linkUrl;

    @ApiModelProperty("展示位置(0: 视频 1: 社会责任 2: 企业文化 3: 多媒体)")
    private Integer type;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("图片宽高比(示例:宽/高 16/9)")
    private String aspectRatio;
}
