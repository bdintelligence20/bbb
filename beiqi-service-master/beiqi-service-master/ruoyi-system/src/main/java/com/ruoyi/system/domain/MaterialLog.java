package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * materialLog对象 material_log
 * 
 * @author thh
 * @date 2023-11-15
 */
@Data
public class MaterialLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 操作人id */
    @Excel(name = "操作人id")
    private Long userId;

    /** 操作人ip地址 */
    @Excel(name = "操作人ip地址")
    private String ip;

    /** 请求方式 */
    @Excel(name = "请求方式")
    private String method;

    /** 接口地址 */
    @Excel(name = "接口地址")
    private String path;

    /** 链接参数 */
    @Excel(name = "链接参数")
    private String urlParameter;

    /** 请求体参数 */
    @Excel(name = "请求体参数")
    private String bodyParameter;

    /** 接口返回值 */
    @Excel(name = "接口返回值")
    private String responseData;

    /** 异常返回 */
    @Excel(name = "异常返回")
    private String exceptionResult;

    @Excel(name = "操作时间")
    private Date createTime;

    @Excel(name = "素材类型")
    private String type;

    @Excel(name = "车型")
    private String model;

    @Excel(name = "下载链接")
    private String downloadLink;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getIp() 
    {
        return ip;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setUrlParameter(String urlParameter) 
    {
        this.urlParameter = urlParameter;
    }

    public String getUrlParameter() 
    {
        return urlParameter;
    }
    public void setBodyParameter(String bodyParameter) 
    {
        this.bodyParameter = bodyParameter;
    }

    public String getBodyParameter() 
    {
        return bodyParameter;
    }
    public void setResponseData(String responseData) 
    {
        this.responseData = responseData;
    }

    public String getResponseData() 
    {
        return responseData;
    }
    public void setExceptionResult(String exceptionResult) 
    {
        this.exceptionResult = exceptionResult;
    }

    public String getExceptionResult() 
    {
        return exceptionResult;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("ip", getIp())
            .append("method", getMethod())
            .append("path", getPath())
            .append("urlParameter", getUrlParameter())
            .append("bodyParameter", getBodyParameter())
            .append("responseData", getResponseData())
            .append("exceptionResult", getExceptionResult())
            .toString();
    }
}
