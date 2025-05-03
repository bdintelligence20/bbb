package com.ruoyi.system.service;

/**
 * 被阻止 ip 管理
 *
 * @author liuzhuoming
 */
public interface SysBlockIpService {

    void editIpSet(String ipListStr);

    String getBlockIps();
}
