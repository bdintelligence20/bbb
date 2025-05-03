package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "VehicleCharacteristicTextBlockHomeVo", description = "车型特性-文本块 vo")
public class VehicleCharacteristicTextBlockHomeVo {
    @ApiModelProperty("排序值")
    private Integer sort;
    @ApiModelProperty("多语言标题")
    private String title;
    @ApiModelProperty("多语言详情")
    private String desc;
}
