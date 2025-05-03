package com.ruoyi.home.contact.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 新闻邮件推送日志表
 *
 * @author liuzhuoming
 */
@Data
@TableName("news_mail_push_log")
public class NewsMailPushLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("news_id")
    private Integer newsId;
    @TableField("lang")
    private String lang;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}
