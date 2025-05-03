package com.ruoyi.home.contact.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 国家
 *
 * @author liuzhuoming
 */
@Data
@TableName("country")
@ApiModel(value = "Country", description = "国家")
public class Country {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("多语言 ID")
    private Integer id;
    @TableField("sort")
    @ApiModelProperty("排序值")
    private Integer sort;

    @TableField("area_id")
    @ApiModelProperty("区域 ID")
    @NotNull(message = "国家所属区域不能为空")
    private Integer areaId;
    @TableField("flag_img_url")
    @ApiModelProperty("国旗图片链接")
    private String flagImgUrl;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", example = "2022-12-23 12:23:34")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", example = "2022-12-23 12:23:34")
    private Date updateTime;

    @TableField(exist = false)
    @ApiModelProperty("多语言")
    private List<CountryLang> langs;

    @TableField(exist = false)
    @ApiModelProperty("社交媒体链接")
    @Valid
    private List<CountryContactUrl> urls;
}
