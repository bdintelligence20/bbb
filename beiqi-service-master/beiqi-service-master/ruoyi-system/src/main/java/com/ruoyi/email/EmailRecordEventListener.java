package com.ruoyi.email;

import com.ruoyi.common.utils.EmailRecordEventBean;
import com.ruoyi.common.utils.EmailService;
import com.ruoyi.common.utils.json.JsonUtil;
import com.ruoyi.email.domain.EmailRecord;
import com.ruoyi.email.service.IEmailRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 邮件发送记录事件监听器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailRecordEventListener {

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

    @Autowired
    private IEmailRecordService emailRecordService;

    /**
     * 监听publishEvent事件，并异步处理
     * 延时1秒执行
     * @param event
     */
    @Async
    @EventListener(EmailRecordEventBean.class)
    public void addEmailRecord(EmailRecordEventBean event) {
        scheduledExecutorService.schedule(() -> {
            String templateJsonString = JsonUtil.toJSONString(event.getTemplate());
            String subject = event.getSubject();
            String fromMail = event.getFromMail();
            String toMail = event.getToMail();
            String ccMailsJsonString = JsonUtil.toJSONString(event.getCcMails());
            String kvsJsonString = JsonUtil.toJSONString(event.getKvs());
            String errMsg = event.getErrMsg();
            Integer status = event.getStatus();
            EmailRecord emailRecord = new EmailRecord();
            emailRecord.setEmailTemplate(templateJsonString);
            emailRecord.setSubject(subject);
            emailRecord.setFromMail(fromMail);
            emailRecord.setToMail(toMail);
            emailRecord.setCcMails(ccMailsJsonString);
            emailRecord.setKvs(kvsJsonString);
            emailRecord.setErrMsg(errMsg);
            emailRecord.setStatus(status);
            emailRecord.setCreateTime(System.currentTimeMillis());
            emailRecordService.insertEmailRecord(emailRecord);
            log.info("邮件发送记录添加成功：{}", emailRecord);
        }, 1, TimeUnit.SECONDS);
    }
}
