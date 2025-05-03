package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 车型特性 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "VehicleCharacteristicHomeVo", description = "车型特性 vo")
public class VehicleCharacteristicHomeVo {
    @ApiModelProperty("排序值")
    private Integer sort;
    @ApiModelProperty("特性类型 text_block=文本块 img_and_text=图文 gallery=相册 img_and_text_list=图文轮播")
    private String type;
}
