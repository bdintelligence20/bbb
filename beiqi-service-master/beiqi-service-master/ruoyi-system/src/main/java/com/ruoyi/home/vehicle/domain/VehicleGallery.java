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
 * 车辆相册
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_gallery")
@ApiModel(value = "VehicleGallery", description = "车辆相册")
public class VehicleGallery {
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
