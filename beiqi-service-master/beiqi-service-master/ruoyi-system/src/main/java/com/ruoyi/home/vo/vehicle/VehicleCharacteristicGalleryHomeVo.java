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
@ApiModel(value = "VehicleCharacteristicGalleryHomeVo", description = "车型特性-相册 vo")
public class VehicleCharacteristicGalleryHomeVo extends VehicleCharacteristicHomeVo {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("图片链接")
    private String imgUrl;
}
