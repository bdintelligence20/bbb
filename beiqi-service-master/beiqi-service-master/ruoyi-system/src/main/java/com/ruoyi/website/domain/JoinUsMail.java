package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "招聘邮件实体")
public class JoinUsMail extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long id;

    @ApiModelProperty("姓名")
    private String fullName;

    @ApiModelProperty("国籍")
    private String nationality;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("现居国家")
    private String currCountry;

    @ApiModelProperty("当前在职公司")
    private String currCompany;

    @ApiModelProperty("简历URL")
    private String resumeUrl;

    @ApiModelProperty("工作区域名称")
    private String areaName;

    @ApiModelProperty("岗位类型名称")
    private String categoryName;

    @ApiModelProperty("职称")
    private String jobTitle;
}
