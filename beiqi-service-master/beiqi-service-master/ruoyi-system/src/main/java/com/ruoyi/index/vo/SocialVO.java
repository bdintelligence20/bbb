package com.ruoyi.index.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("社会责任")
public class SocialVO {

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("报告列表")
    private List<SocialReportVO> reports;

}
