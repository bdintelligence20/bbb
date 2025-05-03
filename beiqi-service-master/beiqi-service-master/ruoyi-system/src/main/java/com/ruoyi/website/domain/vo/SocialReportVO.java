package com.ruoyi.website.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("社会责任报告实体")
public class SocialReportVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("封面图片链接")
    private String coverImageUrl;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("报告ID")
    private Long socialReportLangId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片地址")
    private String reportUrl;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("显示顺序")
    private Integer sort;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;
}
