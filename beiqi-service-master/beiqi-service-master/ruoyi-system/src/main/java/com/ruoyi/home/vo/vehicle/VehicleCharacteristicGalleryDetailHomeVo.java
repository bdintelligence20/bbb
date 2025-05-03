package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 车型特性 vo
 *
 * @author liuzhuoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "VehicleCharacteristicGalleryDetailHomeVo", description = "车型特性-相册详情 vo")
public class VehicleCharacteristicGalleryDetailHomeVo extends VehicleCharacteristicHomeVo {
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("详情")
    private String desc;

    @ApiModelProperty("图片集合")
    private List<VehicleCharacteristicGalleryHomeVo> imgs;
}
