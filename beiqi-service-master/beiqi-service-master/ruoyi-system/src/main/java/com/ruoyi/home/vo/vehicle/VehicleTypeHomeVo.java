package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 车辆类型 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "VehicleTypeHomeVo", description = "车辆类型 vo")
public class VehicleTypeHomeVo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("车辆类型名称")
    private String name;
    @ApiModelProperty("排序值")
    private Integer sort;

    @ApiModelProperty("车辆型号简述")
    private List<VehicleClassSummaryHomeVo> vehicleClassSummaries;
}
