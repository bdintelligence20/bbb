package com.ruoyi.website.service;

import com.ruoyi.website.domain.JoinUsMail;
import com.ruoyi.website.domain.vo.JoinUsMailVO;

import java.util.List;


public interface IJoinUsMailService 
{
    /**
     * 查询招聘邮件发送记录
     * 
     * @param id 招聘邮件发送记录主键
     * @return 招聘邮件发送记录
     */
    public JoinUsMail selectJoinUsMailById(Long id);

    /**
     * 查询招聘邮件发送记录列表
     * 
     * @param joinUsMail 招聘邮件发送记录
     * @return 招聘邮件发送记录集合
     */
    public List<JoinUsMail> selectJoinUsMailList(JoinUsMail joinUsMail);

    /**
     * 新增招聘邮件发送记录
     * 
     * @param joinUsMail 招聘邮件发送记录
     * @return 结果
     */
    public int insertJoinUsMail(JoinUsMailVO joinUsMail);

    /**
     * 修改招聘邮件发送记录
     * 
     * @param joinUsMail 招聘邮件发送记录
     * @return 结果
     */
    public int updateJoinUsMail(JoinUsMail joinUsMail);

    /**
     * 批量删除招聘邮件发送记录
     * 
     * @param ids 需要删除的招聘邮件发送记录主键集合
     * @return 结果
     */
    public int deleteJoinUsMailByIds(Long[] ids);

    /**
     * 删除招聘邮件发送记录信息
     * 
     * @param id 招聘邮件发送记录主键
     * @return 结果
     */
    public int deleteJoinUsMailById(Long id);
}
