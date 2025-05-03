package com.ruoyi.system.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 黑名单拦截日志对象 blacklist_intercept_log
 * 
 * @author thh
 * @date 2023-09-06
 */
@Accessors(chain = true)
@Data
public class BlacklistInterceptLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */

    private Long id;

    /** 主机地址 */
    @Excel(name = "主机地址")
    private String ip;

    /** 请求参数 */
    @Excel(name = "请求参数")
    private String param;

    /** 方法名称 */
    @Excel(name = "方法名称")
    private String method;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setParam(String param) 
    {
        this.param = param;
    }

    public String getParam() 
    {
        return param;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ip", getIp())
            .append("param", getParam())
            .append("method", getMethod())
            .append("createTime", getCreateTime())
            .toString();
    }
}
