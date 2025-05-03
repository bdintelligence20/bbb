package com.ruoyi.website.domain.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * materialLog对象 material_log
 * 
 * @author thh
 * @date 2023-11-15
 */
@Data
public class MaterialLogVO extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 操作人id */
    private Long userId;

    @Excel(name = "操作人")
    private String userName;

    /** 操作人ip地址 */
    @Excel(name = "操作人ip地址")
    private String ip;

    /** 请求方式 */
    private String method;

    /** 接口地址 */
    private String path;

    /** 链接参数 */
    private String urlParameter;

    /** 请求体参数 */
    private String bodyParameter;

    /** 接口返回值 */
    private String responseData;

    /** 异常返回 */
    private String exceptionResult;

    private Date createTime;

    @Excel(name = "素材类型")
    private String type;

    private String model;

    @Excel(name = "车型")
    private String title;

    @Excel(name = "下载链接")
    private String downloadLink;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String time;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
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
