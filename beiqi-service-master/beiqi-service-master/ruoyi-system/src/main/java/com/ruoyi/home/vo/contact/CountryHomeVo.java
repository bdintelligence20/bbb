package com.ruoyi.home.vo.contact;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 国家 Vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "CountryHomeVo", description = "国家 Vo")
public class CountryHomeVo {
    @ApiModelProperty("排序值")
    private Integer sort;
    @ApiModelProperty("国旗图片链接")
    private String flagImgUrl;
    @ApiModelProperty("多语言名称")
    private String name;

    @ApiModelProperty("定位标识")
    private String position;

    @ApiModelProperty("社交媒体链接集合")
    private List<CountryContactUrlHomeVo> urls;
}
