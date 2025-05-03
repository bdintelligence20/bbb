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
 * 区域
 *
 * @author liuzhuoming
 */
@Data
@TableName("area")
@ApiModel(value = "Area", description = "区域")
public class Area {
    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("sort")
    @ApiModelProperty("排序值")
    private Integer sort;

    @TableField("emails")
    @ApiModelProperty("收件邮箱，多个邮箱的话逗号分隔")
    private String emails;
    @TableField("type")
    @ApiModelProperty("类型 1-试驾预约")
    private Integer type;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;

    @TableField(exist = false)
    @NotEmpty(message = "多语言不能为空")
    @NotNull(message = "多语言不能为空")
    private List<AreaLang> langs;
}
