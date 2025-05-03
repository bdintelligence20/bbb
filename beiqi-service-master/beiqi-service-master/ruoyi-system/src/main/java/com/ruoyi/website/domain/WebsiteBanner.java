package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel(description = "banner")
public class WebsiteBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long bannerId;

    @ApiModelProperty("标题")
    @Excel(name = "标题")
    private String title;

    @ApiModelProperty("图片地址")
    @Excel(name = "图片地址")
    private String imageUrl;
    @ApiModelProperty("视频地址")
    @Excel(name = "视频地址")
    private String videoUrl;

    @ApiModelProperty("链接地址")
    @Excel(name = "链接地址")
    private String linkUrl;

    @ApiModelProperty("排序")
    @Excel(name = "排序")
    private Integer sort;

    @ApiModelProperty("显示位置(0: 首页 1: 新闻页 2: 其它)")
    @Excel(name = "显示位置")
    private Integer type;

    @ApiModelProperty("描述")
    @Excel(name = "描述")
    private String describe;

    @ApiModelProperty("逻辑删除 1已删除， 0未删除")
    private String delFlag;

    @ApiModelProperty("宽高比(示例：16/9)")
    private String aspectRatio;

    @ApiModelProperty("是否启用 1启用 2隐藏")
    private Integer enable;
}
