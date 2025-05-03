package com.ruoyi.email.mapper;

import com.ruoyi.email.domain.EmailRecord;

import java.util.List;

/**
 * 邮件发送副本Mapper接口
 *
 * @author thh
 * @date 2024-07-02
 */
public interface EmailRecordMapper {
    /**
     * 查询邮件发送副本
     *
     * @param id 邮件发送副本主键
     * @return 邮件发送副本
     */
    public EmailRecord selectEmailRecordById(Long id);

    /**
     * 查询邮件发送副本列表
     *
     * @param emailRecord 邮件发送副本
     * @return 邮件发送副本集合
     */
    public List<EmailRecord> selectEmailRecordList(EmailRecord emailRecord);

    /**
     * 新增邮件发送副本
     *
     * @param emailRecord 邮件发送副本
     * @return 结果
     */
    public int insertEmailRecord(EmailRecord emailRecord);

    /**
     * 修改邮件发送副本
     *
     * @param emailRecord 邮件发送副本
     * @return 结果
     */
    public int updateEmailRecord(EmailRecord emailRecord);

    /**
     * 删除邮件发送副本
     *
     * @param id 邮件发送副本主键
     * @return 结果
     */
    public int deleteEmailRecordById(Long id);

    /**
     * 批量删除邮件发送副本
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmailRecordByIds(Long[] ids);

    List<EmailRecord> selectEmailRecordListByLimit(EmailRecord emailRecord);
}
