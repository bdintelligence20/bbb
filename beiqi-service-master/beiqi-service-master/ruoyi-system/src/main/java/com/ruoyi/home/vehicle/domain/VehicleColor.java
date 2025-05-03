package com.ruoyi.home.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 车辆颜色
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_color")
@ApiModel(value = "VehicleColor", description = "车辆颜色")
public class VehicleColor {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;
    @TableField("vehicle_class_id")
    @ApiModelProperty("车型 ID")
    @NotNull(message = "车型 ID 不能为空")
    private Integer vehicleClassId;
    @TableField("sort")
    @ApiModelProperty("排序值")
    private Integer sort;

    @TableField("rgb_code")
    @ApiModelProperty("rgb 编码")
    @NotEmpty(message = "rgb 编码不能为空")
    private String rgbCode;
    @TableField("img_url")
    @ApiModelProperty("图片链接")
    @NotEmpty(message = "图片链接不能为空")
    private String imgUrl;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;
}
