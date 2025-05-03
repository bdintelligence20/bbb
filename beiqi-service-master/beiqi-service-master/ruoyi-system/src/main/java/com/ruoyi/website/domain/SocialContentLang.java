package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel("社会责任内容多语言实体")
public class SocialContentLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 内容ID */
    @ApiModelProperty("内容ID")
    private Long socialContentId;

    /** 语种 */
    @ApiModelProperty("语种")
    private String language;

    /** 内容 */
    @ApiModelProperty("内容")
    private String content;

}
