package com.ruoyi.website.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class NewsIndexShowLocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    private Integer sort;

    private Integer enable;
}
