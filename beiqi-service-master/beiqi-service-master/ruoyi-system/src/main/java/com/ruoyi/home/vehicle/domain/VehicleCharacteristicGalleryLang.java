package com.ruoyi.home.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 车型特性（相册）（多语言
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_characteristic_gallery_lang")
@ApiModel(value = "VehicleCharacteristicGalleryLang", description = "车型特性（相册）（多语言")
public class VehicleCharacteristicGalleryLang {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("多语言 ID")
    private Integer id;
    @TableField("gallery_id")
    @ApiModelProperty("相册 ID")
    private Integer galleryId;
    @TableField("lang")
    @ApiModelProperty("多语言类型")
    private String lang;

    @TableField("title")
    @ApiModelProperty("多语言标题")
    private String title;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", example = "2022-12-23 12:23:34")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", example = "2022-12-23 12:23:34")
    private Date updateTime;
}
