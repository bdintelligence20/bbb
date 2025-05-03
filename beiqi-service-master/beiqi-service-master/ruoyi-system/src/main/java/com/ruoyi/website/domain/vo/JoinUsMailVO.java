package com.ruoyi.website.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "招聘邮件发送实体")
public class JoinUsMailVO {

    @NotBlank(message = "Full Name cannot be empty")
    @ApiModelProperty("姓名")
    private String fullName;

    @NotBlank(message = "Nationality cannot be empty")
    @ApiModelProperty("国籍")
    private String nationality;

    @Email
    @NotBlank(message = "Email cannot be empty")
    @ApiModelProperty("邮箱")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @ApiModelProperty("电话")
    private String phone;

    @NotBlank(message = "Current country cannot be empty")
    @ApiModelProperty("现居国家")
    private String currCountry;

    @NotBlank(message = "Current company cannot be empty")
    @ApiModelProperty("当前在职公司")
    private String currCompany;

    @NotBlank(message = "Resume cannot be empty")
    @ApiModelProperty("简历URL")
    private String resumeUrl;

    @NotBlank(message = "Job Title cannot be empty")
    @ApiModelProperty("职称")
    private String jobTitle;

    @ApiModelProperty("工作区域名称")
    private String areaName;

    @ApiModelProperty("岗位类型名称")
    private String categoryName;


}
