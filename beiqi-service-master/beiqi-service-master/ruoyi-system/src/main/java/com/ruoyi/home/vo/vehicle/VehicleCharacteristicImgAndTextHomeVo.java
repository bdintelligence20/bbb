package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 车型特性 vo
 *
 * @author liuzhuoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "VehicleCharacteristicImgAndTextHomeVo", description = "车型特性-图文 vo")
public class VehicleCharacteristicImgAndTextHomeVo extends VehicleCharacteristicHomeVo {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subTitle;
    @ApiModelProperty("详情")
    private String desc;
    @ApiModelProperty("图片链接")
    private String imgUrl;
    @ApiModelProperty("展示类型")
    private String viewType;
}
