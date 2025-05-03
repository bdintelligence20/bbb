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
 * 媒体联系
 *
 * @author liuzhuoming
 */
@Data
@TableName("media_contact")
@ApiModel(value = "MediaContact", description = "媒体联系")
public class MediaContact {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;
    @TableField("lang")
    @ApiModelProperty("语言类型")
    @NotBlank(message = "lang must not be blank")
    private String lang;

    @ApiModelProperty("性别")
    @TableField("gender")
    @NotBlank(message = "gender must not be blank")
    @Length(max = 100, message = "maximum gender length is 100")
    private String gender;
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
    @Length(max = 100, message = "maximum email length is 100")
    @ApiModelProperty("email")
    private String email;

    @TableField("msg")
    @NotBlank(message = "message must not be blank")
    @ApiModelProperty("留言信息")
    @Length(max = 5000, message = "maximum message length is 100")
    private String msg;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
