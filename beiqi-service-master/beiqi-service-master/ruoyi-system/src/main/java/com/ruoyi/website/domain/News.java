package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 新闻对象 website_news
 * 
 * @author thh
 * @date 2022-06-01
 */
@Data
@ApiModel(description = "新闻实体")
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("categoryId")
    @Excel(name = "导航ID")
    private Long categoryId;

    @ApiModelProperty("标题")
    @Excel(name = "标题")
    private String title;

    @ApiModelProperty("概述")
    private String summarize;

    @ApiModelProperty("列表图")
    private String imageUrl;

    @ApiModelProperty("显示顺序")
    @Excel(name = "显示顺序")
    private Integer sort;

    @ApiModelProperty("发布状态(0 未发布 1 已发布)")
    private Integer status;

    @ApiModelProperty("逻辑删除 1已删除， 0未删除")
    private String delFlag;

    @ApiModelProperty("所属页面(0 新闻 1 overview 2 our vision)")
    private Integer pageType;

    @ApiModelProperty("是否推荐位(0:否 1:是)")
    private Integer recommend;

    @ApiModelProperty("发布时间")
    private Date publishTime;

    @ApiModelProperty("是否启用 1启用 2隐藏")
    private Integer enable;

}
