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
 * 图文规格
 *
 * @author liuzhuoming
 */
@Data
@TableName("vehicle_specification_img_and_text_lang")
@ApiModel(value = "VehicleSpecificationImgAndTextLang", description = "车辆特性-图文轮播（多语言")
public class VehicleSpecificationImgAndTextLang {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("多语言 ID")
    private Integer id;
    @TableField("img_and_text_id")
    @ApiModelProperty("图文 ID")
    private Integer imgAndTextId;
    @TableField("lang")
    @ApiModelProperty("多语言类型")
    private String lang;

    @TableField("title")
    @ApiModelProperty("多语言标题")
    @Length(max = 200, message = "标题长度不能超过200")
    private String title;
    @ApiModelProperty("多语言详情")
    @TableField("`desc`")
    private String desc;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", example = "2022-12-23 12:23:34")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", example = "2022-12-23 12:23:34")
    private Date updateTime;
}
