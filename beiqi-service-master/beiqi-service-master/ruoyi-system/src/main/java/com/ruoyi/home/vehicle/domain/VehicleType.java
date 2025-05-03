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
import java.util.List;

/**
 * 车辆类型
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_type")
@ApiModel(value = "VehicleType", description = "车辆类型")
public class VehicleType {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @TableField("sort")
    @ApiModelProperty("排序值")
    private Integer sort;
    @TableField(exist = false)
    @ApiModelProperty("是否启用 1启用 2隐藏")
    private Integer enable;

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
    private List<VehicleTypeLang> langs;
}
