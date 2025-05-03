package com.ruoyi.home.contact.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.config.EmailConfig;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.utils.EmailService;
import com.ruoyi.common.utils.json.JsonUtil;
import com.ruoyi.home.contact.domain.MediaContact;
import com.ruoyi.home.contact.mapper.MediaContactMapper;
import com.ruoyi.home.contact.service.MediaContactService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * 媒体联系
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
@Slf4j
public class MediaContactServiceImpl implements MediaContactService {
    private MediaContactMapper mediaContactMapper;
    private EmailConfig emailConfig;
    private EmailService emailService;

    @Override
    public void insert(MediaContact mediaContact) {
        mediaContact.setCreateTime(new Date());
        mediaContactMapper.insert(mediaContact);
        EmailConfig.MediaContactConfig mediaContactConfig = emailConfig.getMediaContact();

        if (mediaContactConfig.isEnabled()) {
            String fromEmail = mediaContactConfig.getFrom();
            String toEmail = mediaContactConfig.getTo();
            Map<String, Object> kvs = JsonUtil.toMap(mediaContact);
            try {
                emailService.send(EmailService.EmailTemplate.MEDIA_CONTACT, "BAIC国际官网媒体联系信息",
                        fromEmail, toEmail, Collections.emptyList(),
                        kvs);
            } catch (Exception e) {
                log.error("发送媒体联系失败：{}", e.getMessage());
            }
        }
    }

    @Override
    public TableDataInfoT<MediaContact> page(Integer pageNum, Integer pageSize, Date startTime, Date endTime) {
        LambdaQueryWrapper<MediaContact> contactUsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactUsLambdaQueryWrapper
                .lt(endTime != null, MediaContact::getCreateTime, endTime)
                .gt(startTime != null, MediaContact::getCreateTime, startTime)
                .orderByDesc(MediaContact::getCreateTime);
        Page<MediaContact> page = new Page<>(pageNum, pageSize);
        Page<MediaContact> pageRes = mediaContactMapper.selectPage(page, contactUsLambdaQueryWrapper);
        return new TableDataInfoT<>(pageRes);
    }
}
