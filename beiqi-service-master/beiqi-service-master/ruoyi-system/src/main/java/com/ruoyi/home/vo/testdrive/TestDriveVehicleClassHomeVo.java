package com.ruoyi.home.vo.testdrive;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 预约试驾车型 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "TestDriveVehicleClassHomeVo", description = "预约试驾车型 vo")
public class TestDriveVehicleClassHomeVo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("图片链接")
    private String imgUrl;
    @ApiModelProperty("排序值")
    private Integer sort;
}
