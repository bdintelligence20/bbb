package com.ruoyi.home.testdrive.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 试驾预约申请
 *
 * @author liuzhuoming
 */
@Data
@TableName("test_drive_commit")
public class TestDriveCommit {
    public static final String TELE = "^\\+[ \\d]{1,}$";
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;
    @TableField("area_id")
    @ApiModelProperty("区域 ID")
    @NotNull(message = "area must not be null")
    private Integer areaId;
    @TableField("lang")
    @ApiModelProperty("语言类型")
    @NotBlank(message = "lang must not be blank")
    private String lang;

    @TableField("name")
    @ApiModelProperty("预约人姓名")
    @NotEmpty(message = "your name must not be empty")
    @Length(max = 100, message = "maximum name length is 100")
    private String name;
    @TableField("gender")
    @ApiModelProperty("性别")
    @NotEmpty(message = "your gender must not be empty")
    @Length(max = 10, message = "maximum gender length is 10")
    private String gender;
    @TableField("telephone")
    @ApiModelProperty("手机号码")
    @NotEmpty(message = "your telephone must not be empty")
    @Length(max = 20, message = "maximum telephone length is 20")
    @Pattern(regexp = TELE,message = "please enter a valid phone number")
    private String telephone;
    @TableField("mailbox")
    @ApiModelProperty("邮箱")
    @Email
    @NotEmpty(message = "your mailbox must not be empty")
    @Length(max = 100, message = "maximum mailbox length is 100")
    private String mailbox;

    @TableField("test_drive_vehicle_class_id")
    @ApiModelProperty("试驾车型 ID")
    @NotNull(message = "test drive vehicle must not be null")
    private Integer testDriveVehicleClassId;

    @TableField("status")
    @ApiModelProperty("申请状态 0-已提交 1-申请通过 2-申请被拒")
    @Null
    private Integer status;
    @TableField("reason")
    @ApiModelProperty("申请被拒原因")
    @Null
    private String reason;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @TableField(exist = false)
    @ApiModelProperty("区域名称")
    private String areaName;
    @TableField(exist = false)
    @ApiModelProperty("试驾车型名称")
    private String testDriveVehicleClassName;
}
