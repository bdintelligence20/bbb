package com.ruoyi.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 邮件工具
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private EmailDynamicPassword emailDynamicPassword;

    private TemplateEngine templateEngine;
    private ThreadPoolExecutorHandler threadPoolExecutorHandler;
    private final ApplicationContext applicationContext;

    /**
     * 邮件发送
     *
     * @param template 邮件模板类型
     * @param subject  邮件主题
     * @param fromMail 来源邮箱
     * @param toMail   发送邮箱
     * @param ccMails  抄送邮箱集合
     * @param kvs      邮件模板填充值（以 k-v 方式在 Thymeleaf 模板中做解析
     * @throws MessagingException 邮件发送异常（请求超时等
     */
    public void send(EmailTemplate template, String subject,
                     String fromMail, String toMail, List<String> ccMails,
                     Map<String, Object> kvs) throws MessagingException {
        MimeMessage message = mailFunctionSamePart(template, subject, fromMail, toMail, ccMails, kvs);
        //多线程发送邮件，防止前端请求超时
        //threadPoolExecutorHandler.getThreadPoolExecutor().execute(() -> javaMailSender.send(message));
        //2024年7月1日 bwl 修改 增加邮件发送记录,失败可以重新发送
        threadPoolExecutorHandler.getThreadPoolExecutor().execute(() -> {
            String errorMsg = "";
            Integer status = 0;
            try {
                javaMailSender.send(message);
            } catch (MailException e) {
                errorMsg = e.getMessage();
                status = 1;
            }
            //邮件发送完成后发送一个事件,来记录副本日志
            applicationContext.publishEvent(new EmailRecordEventBean(template, subject, fromMail, toMail, ccMails, kvs, errorMsg, status, true));
        });

    }

    public void sendContactUs(EmailTemplate template, String subject,
                              String fromMail, String toMail, List<String> ccMails,
                              Map<String, Object> kvs, InputStreamSource inputStreamSource) throws MessagingException {
        MimeMessage message = mailFunctionSamePartContactUs(template, subject, fromMail, toMail, ccMails, kvs, inputStreamSource);
        //多线程发送邮件，防止前端请求超时
        //threadPoolExecutorHandler.getThreadPoolExecutor().execute(() -> javaMailSender.send(message));
        //2024年7月1日 bwl 修改 增加邮件发送记录,失败可以重新发送
        threadPoolExecutorHandler.getThreadPoolExecutor().execute(() -> {
            String errorMsg = "";
            Integer status = 0;
            try {
                javaMailSender.send(message);
            } catch (MailException e) {
                errorMsg = e.getMessage();
                status = 1;
            }
            //邮件发送完成后发送一个事件,来记录副本日志
            applicationContext.publishEvent(new EmailRecordEventBean(template, subject, fromMail, toMail, ccMails, kvs, errorMsg, status, true));
        });

    }

    /**
     * 邮件重发用
     * @param template
     * @param subject
     * @param fromMail
     * @param toMail
     * @param ccMails
     * @param kvs
     * @throws MessagingException
     */
    public void retrySend(EmailTemplate template, String subject,
                          String fromMail, String toMail, List<String> ccMails,
                          Map<String, Object> kvs) throws MessagingException {
        MimeMessage message = mailFunctionSamePart(template, subject, fromMail, toMail, ccMails, kvs);
        javaMailSender.send(message);
    }

    public void retrySendContactUs(EmailTemplate template, String subject,
                                   String fromMail, String toMail, List<String> ccMails,
                                   Map<String, Object> kvs, InputStreamSource inputStreamSource) throws MessagingException {
        MimeMessage message = mailFunctionSamePartContactUs(template, subject, fromMail, toMail, ccMails, kvs, inputStreamSource);
        javaMailSender.send(message);
    }


    /**
     * 两个发送邮件方法的相同部分
     * @param template
     * @param subject
     * @param fromMail
     * @param toMail
     * @param ccMails
     * @param kvs
     * @return
     * @throws MessagingException
     */
    private MimeMessage mailFunctionSamePart(EmailTemplate template, String subject, String fromMail, String toMail, List<String> ccMails, Map<String, Object> kvs) throws MessagingException {
        //从字典中查询邮件发送密码
        javaMailSender.setPassword(emailDynamicPassword.getPassword());
        Context context = new Context();
        context.setVariables(kvs);
        String html = templateEngine.process(template.getTemplatePath(), context);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(fromMail);
        messageHelper.setTo(toMail);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        if (ccMails != null && !ccMails.isEmpty()) {
            String[] ccMailArray = ccMails.stream()
                    .filter(Objects::nonNull)
                    .distinct()
                    .toArray(String[]::new);
            messageHelper.setCc(ccMailArray);
        }
        return message;
    }

    private MimeMessage mailFunctionSamePartContactUs(EmailTemplate template, String subject, String fromMail, String toMail, List<String> ccMails, Map<String, Object> kvs, InputStreamSource inputStreamSource) throws MessagingException {
        //从字典中查询邮件发送密码
        javaMailSender.setPassword(emailDynamicPassword.getPassword());
        Context context = new Context();
        context.setVariables(kvs);
        String html = templateEngine.process(template.getTemplatePath(), context);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(fromMail);
        messageHelper.setTo(toMail);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        messageHelper.addAttachment("contactUs.xls", inputStreamSource);
        if (ccMails != null && !ccMails.isEmpty()) {
            String[] ccMailArray = ccMails.stream()
                    .filter(Objects::nonNull)
                    .distinct()
                    .toArray(String[]::new);
            messageHelper.setCc(ccMailArray);
        }
        return message;
    }


    @AllArgsConstructor
    @Getter
    public enum EmailTemplate {
        TEST_DRIVE("mail/testDrive"),
        SUBSCRIBE("mail/subscribe"),
        PUBLISH_AR("mail/publish_ar"),
        PUBLISH_ES("mail/publish_es"),
        PUBLISH_EN("mail/publish_en"),
        CONTACT_US("mail/contactUs"),
        MEDIA_CONTACT("mail/mediaContact"),
        JOIN_US("mail/joinUs"),

        RETRIEVE("mail/retrieve"),

        ;

        // resources/templates 目录下的模板路径（不包含后缀名
        private final String templatePath;
    }
}
