package com.ruoyi.index.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SocialReportVO {

    @ApiModelProperty("标题")
    private String title;

    /** 图片地址 */
    @ApiModelProperty("报告地址")
    private String reportUrl;

    @ApiModelProperty("封面图片链接")
    private String coverImageUrl;
}
