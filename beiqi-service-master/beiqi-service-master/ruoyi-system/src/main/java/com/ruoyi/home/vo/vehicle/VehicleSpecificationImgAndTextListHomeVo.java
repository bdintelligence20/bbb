package com.ruoyi.home.vo.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 车辆特性
 *
 * @author liuzhuoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "VehicleSpecificationImgAndTextListHomeVo", description = "车型特性-图文轮播 vo")
public class VehicleSpecificationImgAndTextListHomeVo extends VehicleCharacteristicHomeVo {
    @ApiModelProperty("图文轮播集合")
    private List<VehicleSpecificationImgAndTextHomeVo> list;
}
