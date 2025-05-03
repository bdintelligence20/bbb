package com.ruoyi.website.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

@Data
public class JoinUsWelfare extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer sort;

    /** 是否展示（0显示 1隐藏） */
    @Excel(name = "是否展示", readConverterExp = "0=显示,1=隐藏")
    private Integer status;

}
