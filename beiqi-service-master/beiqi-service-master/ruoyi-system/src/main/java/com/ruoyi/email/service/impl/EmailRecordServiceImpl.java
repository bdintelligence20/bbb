package com.ruoyi.email.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.EmailService;
import com.ruoyi.common.utils.json.JsonUtil;
import com.ruoyi.email.domain.EmailRecord;
import com.ruoyi.email.mapper.EmailRecordMapper;
import com.ruoyi.email.service.IEmailRecordService;
import com.ruoyi.home.task.HomeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.utils.Threads.sleep;

/**
 * 邮件发送副本Service业务层处理
 *
 * @author thh
 * @date 2024-07-02
 */
@Service
public class EmailRecordServiceImpl implements IEmailRecordService {
    @Autowired
    private EmailRecordMapper emailRecordMapper;
    @Autowired
    private EmailService emailService;

    /**
     * 查询邮件发送副本
     *
     * @param id 邮件发送副本主键
     * @return 邮件发送副本
     */
    @Override
    public EmailRecord selectEmailRecordById(Long id) {
        return emailRecordMapper.selectEmailRecordById(id);
    }

    /**
     * 查询邮件发送副本列表
     *
     * @param emailRecord 邮件发送副本
     * @return 邮件发送副本
     */
    @Override
    public List<EmailRecord> selectEmailRecordList(EmailRecord emailRecord) {
        return emailRecordMapper.selectEmailRecordList(emailRecord);
    }

    /**
     * 新增邮件发送副本
     *
     * @param emailRecord 邮件发送副本
     * @return 结果
     */
    @Override
    public int insertEmailRecord(EmailRecord emailRecord) {
        //emailRecord.setCreateTime(DateUtils.getNowDate());
        return emailRecordMapper.insertEmailRecord(emailRecord);
    }

    /**
     * 修改邮件发送副本
     *
     * @param emailRecord 邮件发送副本
     * @return 结果
     */
    @Override
    public int updateEmailRecord(EmailRecord emailRecord) {
        return emailRecordMapper.updateEmailRecord(emailRecord);
    }

    /**
     * 批量删除邮件发送副本
     *
     * @param ids 需要删除的邮件发送副本主键
     * @return 结果
     */
    @Override
    public int deleteEmailRecordByIds(Long[] ids) {
        return emailRecordMapper.deleteEmailRecordByIds(ids);
    }

    /**
     * 删除邮件发送副本信息
     *
     * @param id 邮件发送副本主键
     * @return 结果
     */
    @Override
    public int deleteEmailRecordById(Long id) {
        return emailRecordMapper.deleteEmailRecordById(id);
    }


    /**
     * 重试失败的邮件
     */
    //@Scheduled(fixedDelay = 5000)
    public void failedEmailRetry() {
        EmailRecord emailRecord = new EmailRecord();
        emailRecord.setStatus(1);
        //发送失败的邮件体
        List<EmailRecord> emailRecords = emailRecordMapper.selectEmailRecordListByLimit(emailRecord);
        for (EmailRecord record : emailRecords) {
            EmailService.EmailTemplate template = JsonUtil.toObject(record.getEmailTemplate(), EmailService.EmailTemplate.class);
            List<String> ccMails = JsonUtil.toObjectList(record.getCcMails(), String.class);
            Map<String, Object> kvs = JsonUtil.toObject(record.getKvs(), HashMap.class);
            if (record.getEmailTemplate().equals("\"PUBLISH_EN\"")) {
                kvs.put("newsPublishTime", new Date((Long) kvs.get("newsPublishTime")));
            }
            try {
                if (record.getEmailTemplate().equals("\"CONTACT_US\"")) {
                    emailService.retrySendContactUs(template, record.getSubject(), record.getFromMail(), record.getToMail(), ccMails, kvs, HomeTask.generateExcelStream((List<Map<String, String>>) kvs.get("data")));
                    //尝试成功,更新状态为成功
                    record.setStatus(0);
                    record.setErrMsg("重试成功");
                } else {
                    emailService.retrySend(template, record.getSubject(), record.getFromMail(), record.getToMail(), ccMails, kvs);
                    //尝试成功,更新状态为成功
                    record.setStatus(0);
                    record.setErrMsg("重试成功");
                }
            } catch (Exception e) {
                record.setRetryNumbers((record.getRetryNumbers() != null ? record.getRetryNumbers() : 0) + 1);
                record.setErrMsg("重试失败，原因：" + e.getMessage());
                record.setStatus(1);
            }
            //每执行一条邮件,就更新一次数据库
            record.setLastRetryTimestamp(DateUtils.getNowDate());
            emailRecordMapper.updateEmailRecord(record);
            sleep(2000);
        }
    }
}
