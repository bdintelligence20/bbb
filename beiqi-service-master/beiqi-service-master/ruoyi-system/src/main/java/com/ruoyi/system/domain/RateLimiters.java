package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 限流注解配置对象 rate_limiter
 * 
 * @author thh
 * @date 2023-09-08
 */
@Data
public class RateLimiters extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 限流时间,单位秒 */
    @Excel(name = "限流时间,单位秒")
    private int time;

    /** 限流次数 */
    @Excel(name = "限流次数")
    private int count;

    /** 方法名 */
    @Excel(name = "方法名")
    private String method;

    private String name;




    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("time", getTime())
            .append("count", getCount())
            .append("method", getMethod())
                .append("name", getName())
            .toString();
    }
}
