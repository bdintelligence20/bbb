package com.ruoyi.common.utils;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送之后的事件响应载体
 * 统一日志记录用
 * template 邮件模板类型
 * subject  邮件主题
 * fromMail 来源邮箱
 * toMail   发送邮箱
 * ccMails  抄送邮箱集合
 * kvs      邮件模板填充值（以 k-v 方式在 Thymeleaf 模板中做解析
 *
 */
@Setter
@lombok.Getter
public class EmailRecordEventBean extends ApplicationEvent {

    private EmailService.EmailTemplate template;

    private String subject;

    private String fromMail;

    private String toMail;

    private List<String> ccMails;

    private Map<String, Object> kvs;

    private String errMsg;

    private Integer status;

    public EmailRecordEventBean(EmailService.EmailTemplate template,
                                String subject,
                                String fromMail,
                                String toMail,
                                List<String> ccMails,
                                Map<String, Object> kvs,
                                String errMsg,
                                Integer status,
                                Object source) {
        super(source);
        this.template = template;
        this.subject = subject;
        this.fromMail = fromMail;
        this.toMail = toMail;
        this.ccMails = ccMails;
        this.kvs = kvs;
        this.errMsg = errMsg;
        this.status = status;
    }

}
