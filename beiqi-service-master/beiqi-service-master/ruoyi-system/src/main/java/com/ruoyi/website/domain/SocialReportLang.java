package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel("社会责任内报告多语言实体")
public class SocialReportLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 报告ID */
    @ApiModelProperty("报告ID")
    private Long socialReportId;

    /** 标题 */
    @ApiModelProperty("标题")
    private String title;

    /** 图片地址 */
    @ApiModelProperty("图片地址")
    private String reportUrl;

    /** 语种 */
    @ApiModelProperty("语种")
    private String language;
}
