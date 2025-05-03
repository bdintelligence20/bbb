package com.ruoyi.home.testdrive.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 试驾车型（多语言
 *
 * @author liuzhuoming
 */
@Data
@TableName("test_drive_vehicle_class_lang")
@ApiModel(value = "TestDriveVehicleClassLang", description = "试驾车型（多语言")
public class TestDriveVehicleClassLang {
    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("试驾车型 ID")
    @TableField("test_drive_vehicle_class_id")
    private Integer testDriveVehicleClassId;

    @ApiModelProperty("语言类型")
    @TableField("lang")
    private String lang;

    @TableField("name")
    @ApiModelProperty("名称")
    private String name;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
