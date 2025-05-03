package com.ruoyi.system.service;

import com.ruoyi.system.domain.BlacklistIntercept;
import com.ruoyi.system.domain.BlacklistInterceptLog;

import java.util.List;

public interface IBlacklistInterceptService {

    /**
     * 新增黑名单
     *
     * @param blacklistIntercept 黑名单拦截
     * @return 结果
     */
    public boolean insertBlacklistIntercept(BlacklistIntercept blacklistIntercept);

    /**
     * 新增黑名单
     *
     * @param blacklistIntercept 黑名单拦截
     * @return 结果
     */
    public boolean delectBlacklistIntercept(BlacklistIntercept blacklistIntercept);

    List<BlacklistIntercept> selectBlacklistInterceptList(BlacklistIntercept blacklistIntercept);
}
