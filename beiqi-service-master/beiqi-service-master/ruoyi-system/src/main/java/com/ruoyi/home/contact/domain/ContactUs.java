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
 * 联系我们
 *
 * @author liuzhuoming
 */
@Data
@TableName("contact_us")
@ApiModel(value = "ContactUs", description = "联系我们")
public class ContactUs {
    @ApiModelProperty("ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("lang")
    @ApiModelProperty("语言类型")
    @NotBlank(message = "lang must not be blank")
    private String lang;

    @ApiModelProperty("联系电话")
    @TableField("telephone")
    @NotBlank(message = "telephone must not be blank")
    @Length(max = 100, message = "maximum telephone length is 100")
    private String telephone;
    @ApiModelProperty("联系邮箱")
    @TableField("email")
    @NotBlank(message = "email must not be blank")
    @Email(message = "illegal email format")
    @Length(max = 100, message = "maximum email length is 100")
    private String email;
    @ApiModelProperty("留言信息")
    @TableField("msg")
    @NotBlank(message = "message must not be blank")
    @Length(max = 5000, message = "maximum message length is 5000")
    private String msg;
    @ApiModelProperty("留言类型")
    @TableField("msg_type")
    @NotBlank(message = "message type must not be blank")
    @Length(max = 100, message = "maximum message type length is 100")
    private String msgType;
    @ApiModelProperty("国家")
    @TableField("country")
    @NotBlank(message = "country must not be blank")
    @Length(max = 100, message = "maximum country length is 100")
    private String country;
    @ApiModelProperty("性别")
    @TableField("gender")
    @NotBlank(message = "gender must not be blank")
    @Length(max = 100, message = "maximum gender length is 100")
    private String gender;
    @ApiModelProperty("姓名")
    @TableField("name")
    @NotBlank(message = "your name must not be blank")
    @Length(max = 100, message = "maximum name length is 100")
    private String name;

    @ApiModelProperty("车辆识别代码")
    @TableField("vin")
    @Length(max = 255, message = "maximum vin length is 255")
    private String vin;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private Date createTime;
    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;

    @TableField("country_iso_code")
    @ApiModelProperty(value = "国家ISO代码")
    private String countryIsoCode;
}
