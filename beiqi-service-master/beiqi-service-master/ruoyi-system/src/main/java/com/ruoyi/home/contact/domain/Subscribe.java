package com.ruoyi.home.contact.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 媒体订阅
 *
 * @author liuzhuoming
 */
@Data
@ApiModel(value = "Subscribe", description = "订阅")
@TableName("subscribe")
public class Subscribe {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;
    @TableField("lang")
    @ApiModelProperty("语言类型")
    @NotBlank(message = "lang must not be blank")
    private String lang;

    @TableField("first_name")
    @NotBlank(message = "first name must not be blank")
    @ApiModelProperty("First Name")
    @Length(max = 100, message = "maximum name length is 100")
    private String firstName;
    @TableField("last_name")
    @ApiModelProperty("Last Name")
    @NotBlank(message = "last name must not be blank")
    @Length(max = 100, message = "maximum name length is 100")
    private String lastName;
    @TableField("media_name")
    @ApiModelProperty("媒体名称")
    @NotBlank(message = "media name must not be blank")
    @Length(max = 100, message = "maximum media name length is 100")
    private String mediaName;
    @TableField("email")
    @Email(message = "illegal email format")
    @NotBlank(message = "email must not be blank")
    @ApiModelProperty("email")
    @Length(max = 100, message = "maximum email length is 100")
    private String email;

    @TableField("status")
    @ApiModelProperty("状态 0-已提交 1-已通过 2-已拒绝")
    private Integer status;
    @TableField("reason")
    @ApiModelProperty("被拒原因")
    private String reason;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @TableField("media_introduction")
    @ApiModelProperty("媒体介绍")
    @NotBlank(message = "media introduction must not be blank")
    @Length(max = 600, message = "maximum media introduction length is 600")
    private String mediaIntroduction;
}
