package com.ruoyi.home.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 车型
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_class")
@ApiModel(value = "VehicleClass", description = "车辆型号")
public class VehicleClass {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @TableField("vehicle_type_id")
    @ApiModelProperty("车辆类型 ID")
    private Integer vehicleTypeId;
    @TableField("sort")
    @ApiModelProperty("排序值")
    private Integer sort;

    @TableField("img_url")
    @ApiModelProperty("列表图片链接")
    private String imgUrl;

    @TableField("img_vehicle_url")
    @ApiModelProperty("车俩图片链接")
    private String imgVehicleUrl;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;

    @ApiModelProperty("多语言")
    @NotEmpty(message = "多语言不能为空")
    @NotNull(message = "多语言不能为 null")
    @TableField(exist = false)
    @Valid
    private List<VehicleClassLang> langs;

    @ApiModelProperty("车辆类型")
    @TableField(exist = false)
    private VehicleType type;
}
