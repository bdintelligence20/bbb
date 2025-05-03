package com.ruoyi.home.contact.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 国家区域分类与国家代码
 *
 */
@Data
@TableName("country_codes")
@ApiModel(value = "countryCodes", description = "国家区域分类与国家代码")
public class CountryCodes {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @TableField("area")
    @ApiModelProperty("区域名称")
    private String area;

    @TableField("country_name")
    @ApiModelProperty("国家名称")
    private String countryName;

    @TableField("country_alpha_2")
    @ApiModelProperty("国家代码Alpha2")
    private String countryAlpha2;

    @TableField("country_alpha_3")
    @ApiModelProperty("国家代码Alpha3")
    private String countryAlpha3;

    @TableField("country_numeric")
    @ApiModelProperty("国家数字代码")
    private String countryNumeric;

    @TableField("country_english_name")
    @ApiModelProperty("国家英文名称")
    private String countryEnglishName;

}
