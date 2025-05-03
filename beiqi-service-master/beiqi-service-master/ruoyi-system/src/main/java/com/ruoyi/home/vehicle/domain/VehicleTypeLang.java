package com.ruoyi.home.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 车辆类型（多语言
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_type_lang")
@ApiModel(value = "VehicleTypeLang", description = "车辆类型（多语言")
public class VehicleTypeLang {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("多语言 ID")
    private Integer id;
    @TableField("vehicle_type_id")
    @ApiModelProperty("车辆类型 ID")
    private Integer vehicleTypeId;
    @TableField("lang")
    @ApiModelProperty("语言类型")
    @NotBlank(message = "多语言类型不能为空")
    private String lang;

    @ApiModelProperty("多语言名称")
    @NotBlank(message = "多语言名称不能为空")
    @TableField("name")
    private String name;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;
    @TableField("enable")
    @ApiModelProperty("是否启用 1启用 2隐藏")
    private Integer enable;

}
