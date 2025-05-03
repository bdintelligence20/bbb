package com.ruoyi.home.vehicle.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 车型特性详情（相册）（多语言
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_characteristic_gallery_detail_lang")
@ApiModel(value = "VehicleCharacteristicGalleryDetailLang", description = "车型特性详情（相册）（多语言")
public class VehicleCharacteristicGalleryDetailLang {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("多语言 ID")
    private Integer id;
    @TableField("gallery_detail_id")
    @ApiModelProperty("相册详情 ID")
    private Integer galleryDetailId;
    @TableField("lang")
    @ApiModelProperty("多语言类型")
    private String lang;

    @TableField("title")
    @ApiModelProperty("多语言标题")
    @Length(max = 200, message = "标题长度不能超过200")
    private String title;
    @TableField("`desc`")
    @ApiModelProperty("多语言详情")
    private String desc;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", example = "2022-12-23 12:23:34")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", example = "2022-12-23 12:23:34")
    private Date updateTime;
}
