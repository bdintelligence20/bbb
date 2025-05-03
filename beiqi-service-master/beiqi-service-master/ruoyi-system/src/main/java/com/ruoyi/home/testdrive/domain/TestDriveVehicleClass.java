package com.ruoyi.home.testdrive.domain;

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
 * 试驾车型
 *
 * @author liuzhuoming
 */
@Data
@TableName("test_drive_vehicle_class")
@ApiModel(value = "TestDriveVehicleClass", description = "试驾车型")
public class TestDriveVehicleClass {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("车型图片链接")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty("排序值")
    @TableField("sort")
    private Integer sort;
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private Date updateTime;

    @TableField(exist = false)
    @ApiModelProperty("多语言")
    @NotEmpty
    @NotNull
    private List<TestDriveVehicleClassLang> langs;
}
