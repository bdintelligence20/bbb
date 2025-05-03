package com.ruoyi.home.contact.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.config.EmailConfig;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.EmailService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.json.JsonUtil;
import com.ruoyi.home.contact.domain.NewsMailPushLog;
import com.ruoyi.home.contact.domain.Subscribe;
import com.ruoyi.home.contact.mapper.NewsMailPushLogMapper;
import com.ruoyi.home.contact.mapper.SubscribeMapper;
import com.ruoyi.home.contact.service.SubscribeService;
import com.ruoyi.website.domain.vo.NLangVO;
import com.ruoyi.website.domain.vo.NewsVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 订阅
 *
 * @author liuzhuoming
 */
@AllArgsConstructor
@Service
@Slf4j
public class SubscribeServiceImpl implements SubscribeService {
    private SubscribeMapper subscribeMapper;
    private EmailConfig emailConfig;
    private EmailService emailService;
    private NewsMailPushLogMapper newsMailPushLogMapper;

    @Override
    public void insert(Subscribe subscribe) {
        subscribe.setStatus(0);
        subscribe.setCreateTime(new Date());
        subscribeMapper.insert(subscribe);
        EmailConfig.SubscribeConfig subscribeConfig = emailConfig.getSubscribe();
        if (subscribeConfig.isEnabled()) {
            String fromEmail = subscribeConfig.getFrom();
            String toEmail = subscribeConfig.getTo();
            Map<String, Object> kvs = JsonUtil.toMap(subscribe);
            try {
                emailService.send(EmailService.EmailTemplate.SUBSCRIBE, "BAIC国际官网媒体订阅信息",
                        fromEmail, toEmail, Collections.emptyList(),
                        kvs);
            } catch (Exception e) {
                log.error("发送订阅申请失败：{}", e.getMessage());
            }
        }
    }

    @Override
    public TableDataInfoT<Subscribe> page(Integer pageName, Integer pageSize, Integer status, String keyword) {
        Page<Subscribe> page = new Page<>(pageName, pageSize);
        LambdaQueryWrapper<Subscribe> subscribeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        subscribeLambdaQueryWrapper
                .eq(status != null, Subscribe::getStatus, status)
                .and(StringUtils.isNotEmpty(keyword), e ->
                        e.like(Subscribe::getMediaName, keyword)
                                .or()
                                .like(Subscribe::getFirstName, keyword)
                                .or()
                                .like(Subscribe::getLastName, keyword)
                )
                .orderByDesc(Subscribe::getCreateTime);
        Page<Subscribe> pageResult = subscribeMapper.selectPage(page, subscribeLambdaQueryWrapper);
        return new TableDataInfoT<>(pageResult.getTotal(), pageResult.getRecords());
    }

    @Override
    public void updateStatus(Integer id, Integer status, String reason) {
        Subscribe subscribeDb = subscribeMapper.selectById(id);
        if (subscribeDb == null) throw new ServiceException("订阅不存在");
//        if (subscribeDb.getStatus() != 0) throw new RuntimeException("订阅已被处理过了");
        if (status != 1 && status != 2) throw new ServiceException("传入了错误的状态");
        Subscribe subscribe = new Subscribe();
        subscribe.setId(id);
        subscribe.setStatus(status);
        subscribe.setReason(reason);
        subscribeMapper.updateById(subscribe);
    }

    @Override
    public void sendNewsEmailToAvailableSubscribers(NewsVO newsVO) {
        EmailConfig.PublishConfig publishConfig = emailConfig.getPublish();
        if (publishConfig.isEnabled()) {
            String fromEmail = publishConfig.getFrom();
            LambdaQueryWrapper<Subscribe> subscribeLambdaQueryWrapper = new LambdaQueryWrapper<>();
            subscribeLambdaQueryWrapper.eq(Subscribe::getStatus, 1);
            List<Subscribe> subscribes = subscribeMapper.selectList(subscribeLambdaQueryWrapper);

            Map<String, List<Subscribe>> langSubscribeListMap = subscribes.stream()
                    .collect(Collectors.groupingBy(Subscribe::getLang));

            //根据语言分组发送邮件
            for (Map.Entry<String, List<Subscribe>> subscribeEntry : langSubscribeListMap.entrySet()) {
                String lang = subscribeEntry.getKey();
                List<Subscribe> subscribeList = subscribeEntry.getValue();

                //添加邮件发送状态判断
                LambdaQueryWrapper<NewsMailPushLog> newsMailPushLogLambdaQueryWrapper = new LambdaQueryWrapper<>();
                newsMailPushLogLambdaQueryWrapper
                        .eq(NewsMailPushLog::getLang, lang)
                        .eq(NewsMailPushLog::getNewsId, newsVO.getId());
                NewsMailPushLog newsMailPushLog = newsMailPushLogMapper.selectOne(newsMailPushLogLambdaQueryWrapper);
                //如果已发送过则不再发送邮件
                if (newsMailPushLog != null) {
                    return;
                }


                if (lang == null) {
                    log.error("发送订阅发布失败：对应语言[{}]下的新闻不存在", lang);
                    continue;
                }

                NLangVO nLangVO = null;
                for (NLangVO nLangVOIterator : newsVO.getNewsLangs()) {
                    if (Objects.equals(lang, nLangVOIterator.getLanguage())) nLangVO = nLangVOIterator;
                }

                if (nLangVO == null) {
                    log.error("发送订阅发布失败：对应语言[{}]下的新闻不存在", lang);
                    continue;
                }
                if (nLangVO.getEnable() != 1) {
                    log.error("发送订阅发布失败：对应语言[{}]下的新闻未发布", lang);
                    continue;
                }

                String newsTitle = nLangVO.getTitle();
                String newsSummarize = nLangVO.getSummarize();

                Set<String> toMails = subscribeList.stream()
                        .map(Subscribe::getEmail)
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());

                new Thread(() -> {
                    for (String toMail : toMails) {
                        Map<String, Object> kvs = new HashMap<>();
                        kvs.put("newsId", newsVO.getId());
                        kvs.put("newsTitle", newsTitle);
                        kvs.put("newsSummarize", newsSummarize);
                        kvs.put("newsPublishTime", newsVO.getPublishTime() == null ? new Date() : newsVO.getPublishTime());

                        try {
                            switch (lang.toLowerCase(Locale.ROOT)) {
                                case "en":
                                    emailService.send(EmailService.EmailTemplate.PUBLISH_EN, "[BAIC News Update][" + newsTitle + "]",
                                            fromEmail, toMail, Collections.emptyList(),
                                            kvs);
                                    break;
                                case "es":
                                    emailService.send(EmailService.EmailTemplate.PUBLISH_ES, "[Actualización de noticias BAIC][" + newsTitle + "]",
                                            fromEmail, toMail, Collections.emptyList(),
                                            kvs);
                                    break;
                                case "ar":
                                    emailService.send(EmailService.EmailTemplate.PUBLISH_AR, String.format("[تحديث أخبار بايك][%s]", newsTitle),
                                            fromEmail, toMail, Collections.emptyList(),
                                            kvs);
                                    break;
                                default:
                            }
                        } catch (Exception e) {
                            log.error("发送订阅发布失败：{}", e.getMessage());
                        }
                    }
                }).start();

                NewsMailPushLog newsMailPushLogInsert = new NewsMailPushLog();
                newsMailPushLogInsert.setNewsId(newsVO.getId().intValue());
                newsMailPushLogInsert.setLang(lang);
                newsMailPushLogInsert.setCreateTime(new Date());
                newsMailPushLogMapper.insert(newsMailPushLogInsert);
            }
        }
    }
}
