package com.ruoyi.home.vo.contact;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 国家
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "CountryContactUrlHomeVo", description = "国家联系链接 vo")
public class CountryContactUrlHomeVo {
    @ApiModelProperty("排序值")
    private Integer sort;

    @ApiModelProperty("社交媒体名称")
    private String name;
    @ApiModelProperty("社交媒体链接")
    private String url;
}
