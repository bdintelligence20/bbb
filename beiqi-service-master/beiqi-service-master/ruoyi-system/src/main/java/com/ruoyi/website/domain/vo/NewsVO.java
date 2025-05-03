package com.ruoyi.website.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "新闻实体")
public class NewsVO extends BaseEntity {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("导航ID")
    private Long categoryId;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("发布状态(0 未发布 1 已发布)")
    private Integer status;

    @ApiModelProperty("所属页面(0 新闻 1 overview 2 our vision)")
    private Integer pageType;

    @ApiModelProperty("是否推荐位(0:否 1:是)")
    private Integer recommend;

    @ApiModelProperty("发布时间")
    private Date publishTime;

    @ApiModelProperty("新闻多语言实体")
    private List<NLangVO> newsLangs;
}
