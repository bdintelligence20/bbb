package com.ruoyi.home.contact.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 国家（多语言
 *
 * @author liuzhuoming
 */
@Data
@TableName("country_lang")
@ApiModel(value = "CountryLang", description = "国家（多语言")
public class CountryLang {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("多语言 ID")
    private Integer id;
    @TableField("country_id")
    @ApiModelProperty("国家 ID")
    private Integer countryId;
    @TableField("lang")
    @ApiModelProperty("多语言类型")
    private String lang;

    @TableField("name")
    @ApiModelProperty("多语言名称")
    private String name;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", example = "2022-12-23 12:23:34")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", example = "2022-12-23 12:23:34")
    private Date updateTime;
}
