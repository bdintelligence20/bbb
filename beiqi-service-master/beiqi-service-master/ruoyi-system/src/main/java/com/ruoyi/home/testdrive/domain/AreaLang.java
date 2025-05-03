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
 * 区域（多语言
 *
 * @author liuzhuoming
 */
@Data
@TableName("area_lang")
@ApiModel(value = "AreaLang", description = "区域（多语言")
public class AreaLang {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;
    @TableField("area_id")
    @ApiModelProperty("区域 ID")
    private Integer areaId;
    @TableField("lang")
    @ApiModelProperty("语言类型")
    private String lang;

    @TableField("name")
    @ApiModelProperty("多语言名称")
    private String name;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;
}
