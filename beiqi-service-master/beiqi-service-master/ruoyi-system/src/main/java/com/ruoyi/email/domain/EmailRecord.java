package com.ruoyi.email.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件发送副本对象 email_record
 *
 * @author thh
 * @date 2024-07-02
 */
@Data
public class EmailRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 邮件模板类型JSON */
    @Excel(name = "邮件模板类型JSON")
    private String emailTemplate;

    /** 邮件主题 */
    @Excel(name = "邮件主题")
    private String subject;

    /** 来源邮箱 */
    @Excel(name = "来源邮箱")
    private String fromMail;

    /** 发送邮箱 */
    @Excel(name = "发送邮箱")
    private String toMail;

    /** 抄送邮箱集合JSON */
    @Excel(name = "抄送邮箱集合JSON")
    private String ccMails;

    /** 邮件模板填充值JSON */
    @Excel(name = "邮件模板填充值JSON")
    private String kvs;

    /** 0发送成功1发送失败 */
    @Excel(name = "0发送成功1发送失败")
    private Integer status;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTimestamp;

    /** 创建时间 */
    private Long createTime;

    /** 错误内容 */
    @Excel(name = "错误内容")
    private String errMsg;

    /** 上次重试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上次重试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastRetryTimestamp;

    /** 重试次数 */
    @Excel(name = "重试次数")
    private Long retryNumbers;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("emailTemplate", getEmailTemplate())
                .append("subject", getSubject())
                .append("fromMail", getFromMail())
                .append("toMail", getToMail())
                .append("ccMails", getCcMails())
                .append("kvs", getKvs())
                .append("status", getStatus())
                .append("createTime", getCreateTime())
                .append("createTimestamp", getCreateTimestamp())
                .append("errMsg", getErrMsg())
                .append("lastRetryTimestamp", getLastRetryTimestamp())
                .append("retryNumbers", getRetryNumbers())
                .toString();
    }
}
