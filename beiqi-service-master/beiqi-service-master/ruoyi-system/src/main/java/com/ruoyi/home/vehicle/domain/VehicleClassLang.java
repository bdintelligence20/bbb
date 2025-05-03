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
 * 车型（多语言
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_class_lang")
@ApiModel(value = "VehicleClassLang", description = "车辆类型（多语言")
public class VehicleClassLang {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("多语言 ID")
    private Integer id;
    @TableField("vehicle_class_id")
    @ApiModelProperty("车辆类型 ID")
    private Integer vehicleClassId;
    @TableField("lang")
    @ApiModelProperty("多语言类型")
    @NotBlank(message = "多语言类型不能为空")
    private String lang;

    @TableField("title")
    @ApiModelProperty("多语言标题")
    @NotBlank(message = "多语言标题不能为空")
    private String title;
    @TableField("sub_title")
    @ApiModelProperty("多语言副标题")
    private String subTitle;
    @TableField("`desc`")
    @ApiModelProperty("多语言详情")
    private String desc;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;

    @TableField("specifications_pdf_title")
    @ApiModelProperty("车型配置标题")
    private String specificationsPDFTitle;
    @TableField("specifications_pdf_desc")
    @ApiModelProperty("车型配置详情")
    private String specificationsPDFDesc;

    @TableField("specifications_pdf_url")
    @ApiModelProperty("车型配置 PDF 文件链接")
    private String specificationsPDFUrl;

    @TableField("vr_view_url")
    @ApiModelProperty("VR 预览链接")
    private String vrViewUrl;

    @TableField("enable")
    @ApiModelProperty("是否启用 1启用 2隐藏")
    private Integer enable;
}
