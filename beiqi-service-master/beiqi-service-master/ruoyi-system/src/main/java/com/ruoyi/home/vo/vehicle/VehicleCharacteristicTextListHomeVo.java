package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 车型特性
 *
 * @author liuzhuoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "VehicleCharacteristicTextListHomeVo", description = "车型特性-文本行 vo")
public class VehicleCharacteristicTextListHomeVo extends VehicleCharacteristicHomeVo {
    @ApiModelProperty("文本块集合")
    private List<VehicleCharacteristicTextBlockHomeVo> list;
}
