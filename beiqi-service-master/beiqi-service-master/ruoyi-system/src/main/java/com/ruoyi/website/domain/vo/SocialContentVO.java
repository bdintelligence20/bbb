package com.ruoyi.website.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("社会责任内容实体")
public class SocialContentVO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("是否展示（0显示 1隐藏）")
    private Integer status;

    @ApiModelProperty("内容ID")
    private Long socialContentLangId;

    @ApiModelProperty("语种")
    private String language;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    private String createTime;

}
