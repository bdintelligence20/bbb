package com.ruoyi.home.contact.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

/**
 * 国家
 *
 * @author liuzhuoming
 */
@Data
@TableName("country_contact_url")
@ApiModel(value = "CountryContactUrl", description = "国家联系链接")
public class CountryContactUrl {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("联系链接 ID")
    private Integer id;
    @TableField("country_id")
    @ApiModelProperty("国家 ID")
    private Integer countryId;
    @TableField("sort")
    @ApiModelProperty("排序值")
    private Integer sort;

    @TableField("name")
    @ApiModelProperty("社交媒体名称")
    private String name;
    @TableField("url")
    @ApiModelProperty("社交媒体链接")
    @URL(message = "社交媒体链接不合法")
    private String url;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", example = "2022-12-23 12:23:34")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", example = "2022-12-23 12:23:34")
    private Date updateTime;
}
