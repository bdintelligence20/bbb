package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel("社会责任内报告实体")
public class SocialReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("封面图片链接")
    private String coverImageUrl;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

}
