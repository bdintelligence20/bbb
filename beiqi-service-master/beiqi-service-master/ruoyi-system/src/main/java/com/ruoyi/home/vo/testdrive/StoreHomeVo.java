package com.ruoyi.home.vo.testdrive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 门店 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "StoreHomeVo", description = "门店 vo")
public class StoreHomeVo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("门店下车型 ID 集合")
    private List<Integer> vehicleClassIds;
}
