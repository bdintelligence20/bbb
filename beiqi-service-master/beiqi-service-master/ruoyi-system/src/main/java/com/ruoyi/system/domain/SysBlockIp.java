package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 被阻止的 ip
 *
 * @author liuzhuoming
 */
@TableName("sys_block_ip")
@Data
public class SysBlockIp {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("ip_addrs")
    private String ipAddrs;
    @TableField("ip_set_id")
    private String ipSetId;
    @TableField("ip_version")
    private String ipVersion;
    
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}
