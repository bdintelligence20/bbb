package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 车型规格 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "VehicleCharacteristicImgAndTextHomeVo", description = "车型特性-图文轮播 vo")
public class VehicleSpecificationImgAndTextHomeVo {
    @ApiModelProperty("排序值")
    private Integer sort;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("详情")
    private String desc;
    @ApiModelProperty("图片链接")
    private String imgUrl;
}
