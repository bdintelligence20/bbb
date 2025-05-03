package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 车辆颜色
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "VehicleColorHomeVo", description = "车辆颜色 vo")
public class VehicleColorHomeVo {
    @ApiModelProperty("排序值")
    private Integer sort;
    @ApiModelProperty("rgb 编码")
    private String rgbCode;
    @ApiModelProperty("图片链接")
    private String imgUrl;
}
