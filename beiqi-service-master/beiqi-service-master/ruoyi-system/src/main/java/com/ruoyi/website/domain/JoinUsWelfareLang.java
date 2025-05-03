package com.ruoyi.website.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
public class JoinUsWelfareLang extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 福利ID */
    @Excel(name = "福利ID")
    private Long welfareId;

    /** 标题 */
    @Excel(name = "标题")
    private String name;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imageUrl;

    /** 语种 */
    @Excel(name = "语种")
    private String language;

}
