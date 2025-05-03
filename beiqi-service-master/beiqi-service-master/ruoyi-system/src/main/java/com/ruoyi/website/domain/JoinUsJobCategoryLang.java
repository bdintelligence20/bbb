package com.ruoyi.website.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
@ApiModel("招聘工作类型多语言实体")
public class JoinUsJobCategoryLang extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** ID */
    @ApiModelProperty("ID")
    private Long id;

    /** 工作类型ID */
    @Excel(name = "工作类型ID")
    private Long jobCategoryId;

    /** 名称 */
    @ApiModelProperty("名称")
    private String name;

    /** 语种 */
    @Excel(name = "语种")
    private String language;

}
