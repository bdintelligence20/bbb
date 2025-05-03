package com.ruoyi.website.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 历史对象 website_history
 * 
 * @author thh
 * @date 2022-06-09
 */
@Data
@ApiModel("北汽历史")
public class History extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片url")
    private String imageUrl;

    @ApiModelProperty("正文")
    private String describe;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty("显示顺序")
    private Integer sort;

}
