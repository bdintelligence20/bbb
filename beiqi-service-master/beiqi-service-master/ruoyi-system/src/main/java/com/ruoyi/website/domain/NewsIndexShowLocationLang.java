package com.ruoyi.website.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "首页展示位实体")
public class NewsIndexShowLocationLang extends BaseEntity {
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

    @ApiModelProperty("移动端图片地址")
    private String mobileImageUrl;

    @ApiModelProperty("视频地址")
    private String videoUrl;

    @ApiModelProperty("链接地址")
    private String linkUrl;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("图片宽高比(示例:宽/高 16/9)")
    private String aspectRatio;
}
