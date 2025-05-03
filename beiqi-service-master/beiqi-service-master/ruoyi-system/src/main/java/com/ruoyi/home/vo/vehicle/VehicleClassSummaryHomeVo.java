package com.ruoyi.home.vo.vehicle;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 车辆型号简述 vo
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "VehicleClassSummaryHomeVo", description = "车辆型号简述 vo")
public class VehicleClassSummaryHomeVo {
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("图片链接（用于车型列表")
    private String imgUrl;
    
    @ApiModelProperty("VR 链接（用于车型展示")
    private String vrUrl;
    @ApiModelProperty("图片链接（用于车型展示")
    private String colorImgUrl;

    @TableField("img_vehicle_url")
    @ApiModelProperty("车俩图片链接")
    private String imgVehicleUrl;


    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("副标题")
    private String subTitle;
    @ApiModelProperty("详情")
    private String desc;

    @ApiModelProperty("排序值")
    private Integer sort;
}
