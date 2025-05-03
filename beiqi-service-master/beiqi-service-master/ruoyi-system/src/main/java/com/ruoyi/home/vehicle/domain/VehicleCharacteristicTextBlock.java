package com.ruoyi.home.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 车型文本块
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_characteristic_text_block")
@ApiModel(value = "VehicleCharacteristicTextBlock", description = "车型特性（文本块）")
@Validated
public class VehicleCharacteristicTextBlock {
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

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", example = "2022-12-23 12:23:34")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", example = "2022-12-23 12:23:34")
    private Date updateTime;

    @ApiModelProperty("多语言")
    @TableField(exist = false)
    @Valid
    private List<VehicleCharacteristicTextBlockLang> langs;
}
