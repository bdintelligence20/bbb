package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel(description = "banner多语言实体")
public class BannerLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    @ApiModelProperty("banner ID")
    private Long bannerId;

    /** 标题 */
    @ApiModelProperty("标题")
    @Excel(name = "标题")
    private String title;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    @Excel(name = "图片地址")
    private String imageUrl;

    @ApiModelProperty("视频地址")
    @Excel(name = "视频地址")
    private String videoUrl;

    /** 链接地址 */
    @ApiModelProperty("链接地址")
    @Excel(name = "链接地址")
    private String linkUrl;

    /** 显示顺序 */
    @ApiModelProperty("显示顺序")
    @Excel(name = "显示顺序")
    private Integer sort;

    @ApiModelProperty("显示位置(0: 首页 1: 新闻页 2: 其它)")
    @Excel(name = "显示位置")
    private Integer type;

    /** 描述 */
    @ApiModelProperty("描述")
    @Excel(name = "描述")
    private String describe;

    /** 语种 */
    @ApiModelProperty("语种")
    @Excel(name = "语种")
    private String language;

    /** 逻辑删除 1已删除，0未删除 */
    @ApiModelProperty("逻辑删除 1已删除，0未删除")
    private String delFlag;

    @ApiModelProperty("宽高比(示例：16/9)")
    private String aspectRatio;

    @ApiModelProperty("是否启用 1启用 2隐藏")
    private Integer enable;
}
