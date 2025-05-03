package com.ruoyi.website.service.impl;

import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.config.EmailConfig;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.EmailService;
import com.ruoyi.common.utils.json.JsonUtil;
import com.ruoyi.website.domain.JoinUsMail;
import com.ruoyi.website.domain.vo.JoinUsMailVO;
import com.ruoyi.website.mapper.JoinUsMailMapper;
import com.ruoyi.website.service.IJoinUsMailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@AllArgsConstructor
public class JoinUsMailServiceImpl implements IJoinUsMailService
{
    @Autowired
    private JoinUsMailMapper joinUsMailMapper;

//    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private EmailService emailService;


    /**
     * 查询招聘邮件发送记录
     * 
     * @param id 招聘邮件发送记录主键
     * @return 招聘邮件发送记录
     */
    @Override
    public JoinUsMail selectJoinUsMailById(Long id)
    {
        return joinUsMailMapper.selectJoinUsMailById(id);
    }

    /**
     * 查询招聘邮件发送记录列表
     * 
     * @param joinUsMail 招聘邮件发送记录
     * @return 招聘邮件发送记录
     */
    @Override
    public List<JoinUsMail> selectJoinUsMailList(JoinUsMail joinUsMail) {
        return joinUsMailMapper.selectJoinUsMailList(joinUsMail);
    }

    /**
     * 新增招聘邮件发送记录
     * @return 结果
     */
    @Override
    public int insertJoinUsMail(JoinUsMailVO joinUsMailVO) {
        if (ObjectUtil.isEmpty(joinUsMailVO)){
            throw new ServiceException("非法参数");
        }

        JoinUsMail joinUsMail = new JoinUsMail();
        BeanUtils.copyProperties(joinUsMailVO, joinUsMail);
        joinUsMail.setCreateTime(DateUtils.getNowDate());
        int i = joinUsMailMapper.insertJoinUsMail(joinUsMail);
        EmailConfig.JoinUsConfig joinUsConfig = emailConfig.getJoinUs();

        if (joinUsConfig.isEnabled()){
            String fromEmail = joinUsConfig.getFrom();
            String toEmail = joinUsConfig.getTo();
            joinUsMailVO.setResumeUrl(joinUsConfig.getAddr() + joinUsMailVO.getResumeUrl());
            Map<String, Object> map = JsonUtil.toMap(joinUsMailVO);
            try {
                emailService.send(EmailService.EmailTemplate.JOIN_US, "BAIC国际官网用户求职信息",
                        fromEmail, toEmail, null,
                        map);
            } catch (Exception e) {
                log.error("发送招聘邮件失败：{}", e.getMessage());
            }
        }
        return i;
    }


    /**
     * 修改招聘邮件发送记录
     * 
     * @param joinUsMail 招聘邮件发送记录
     * @return 结果
     */
    @Override
    public int updateJoinUsMail(JoinUsMail joinUsMail)
    {
        joinUsMail.setUpdateTime(DateUtils.getNowDate());
        return joinUsMailMapper.updateJoinUsMail(joinUsMail);
    }

    /**
     * 批量删除招聘邮件发送记录
     * 
     * @param ids 需要删除的招聘邮件发送记录主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsMailByIds(Long[] ids)
    {
        return joinUsMailMapper.deleteJoinUsMailByIds(ids);
    }

    /**
     * 删除招聘邮件发送记录信息
     * 
     * @param id 招聘邮件发送记录主键
     * @return 结果
     */
    @Override
    public int deleteJoinUsMailById(Long id)
    {
        return joinUsMailMapper.deleteJoinUsMailById(id);
    }
}
