package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.config.AwsWafConfig;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.IpAddrUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ThreadPoolExecutorHandler;
import com.ruoyi.system.domain.SysBlockIp;
import com.ruoyi.system.mapper.SysBlockIpMapper;
import com.ruoyi.system.service.SysBlockIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.wafv2.Wafv2Client;
import software.amazon.awssdk.services.wafv2.model.GetIpSetRequest;
import software.amazon.awssdk.services.wafv2.model.GetIpSetResponse;
import software.amazon.awssdk.services.wafv2.model.Scope;
import software.amazon.awssdk.services.wafv2.model.UpdateIpSetRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 被阻止 ip 管理
 *
 * @author liuzhuoming
 */
@Service
public class SysBlockIpServiceImpl implements SysBlockIpService {
    @Autowired
    private AwsWafConfig awsWafConfig;
    @Autowired
    private SysBlockIpMapper sysBlockIpMapper;
    @Autowired
    private ThreadPoolExecutorHandler threadPoolExecutorHandler;

    @Override
    public void editIpSet(String ipListStr) {
        List<String> ipList = Arrays.stream(ipListStr.trim().split("\n"))
                //筛选空白行
                .filter(StringUtils::isNotBlank)
                //去掉每行首尾空白
                .map(String::trim)
                .collect(Collectors.toList());
        long count = ipList.stream()
                //转换 ip/cidr 为 cidr
                .map(IpAddrUtil::completeCIDR)
                //筛选格式错误的输入
                .filter(Objects::isNull)
                .count();
        if (count > 0) {
            String illegalIps = ipList.stream()
                    .filter(ip -> IpAddrUtil.completeCIDR(ip) == null)
                    .collect(Collectors.joining(","));
            throw new ServiceException("提交的 IP 列表存在不合法的 IP 地址：" + illegalIps);
        }
        //ipv4 地址处理
        List<String> ipV4List = ipList.stream()
                //筛选 ipv4
                .filter(ip -> IpAddrUtil.isIpv4CIDR(Objects.requireNonNull(IpAddrUtil.completeCIDR(ip))))
                .collect(Collectors.toList());
        replaceIpSet(ipV4List, awsWafConfig.getIpv4());
        //ipv6 地址处理
        List<String> ipV6List = ipList.stream()
                //筛选 ipv6
                .filter(ip -> IpAddrUtil.isIpv6CIDR(Objects.requireNonNull(IpAddrUtil.completeCIDR(ip))))
                .collect(Collectors.toList());
        replaceIpSet(ipV6List, awsWafConfig.getIpv6());
    }

    private void updateOrInsertBlockIp(List<String> ipList, AwsWafConfig.IpSetConfig ipSetConfig) {
        LambdaQueryWrapper<SysBlockIp> sysBlockIpLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysBlockIpLambdaQueryWrapper
                .eq(SysBlockIp::getIpVersion, ipSetConfig.getVersion().getName())
                .eq(SysBlockIp::getIpSetId, ipSetConfig.getId());
        SysBlockIp sysBlockIpDb = sysBlockIpMapper.selectOne(sysBlockIpLambdaQueryWrapper);
        String ipListStr = String.join("\n", ipList);
        if (sysBlockIpDb == null) {
            SysBlockIp sysBlockIp = new SysBlockIp();
            sysBlockIp.setIpAddrs(ipListStr);
            sysBlockIp.setCreateTime(new Date());
            sysBlockIp.setIpVersion(ipSetConfig.getVersion().getName());
            sysBlockIp.setIpSetId(ipSetConfig.getId());
            //新增ip配置
            sysBlockIpMapper.insert(sysBlockIp);
        } else {
            //更新ip配置
            sysBlockIpDb.setIpAddrs(ipListStr);
            sysBlockIpDb.setUpdateTime(new Date());
            sysBlockIpMapper.updateById(sysBlockIpDb);
        }
    }

    private void replaceIpSet(List<String> ipList, AwsWafConfig.IpSetConfig ipSetConfig) {
        ipList = ipList.stream().distinct().collect(Collectors.toList());
        List<String> cidrList = ipList.stream()
                .map(IpAddrUtil::completeCIDR)
                .collect(Collectors.toList());
        String ipSetId = ipSetConfig.getId();
        String ipSetName = ipSetConfig.getName();
        if (awsWafConfig.isEnabled()) {
            AwsBasicCredentials awsBasicCredentials =
                    AwsBasicCredentials.create(awsWafConfig.getAccessKeyId(), awsWafConfig.getSecretAccessKey());
            try (Wafv2Client wafv2Client = Wafv2Client.builder()
                    .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                    .region(Region.of(awsWafConfig.getRegion()))
                    .build()) {
                GetIpSetRequest getIpSetRequest = GetIpSetRequest.builder()
                        .id(ipSetId)
                        .name(ipSetName)
                        .scope(Scope.REGIONAL)
                        .build();
                GetIpSetResponse getIpSetResponse = wafv2Client.getIPSet(getIpSetRequest);
                String lockToken = getIpSetResponse.lockToken();
                UpdateIpSetRequest updateIpSetRequest = UpdateIpSetRequest.builder()
                        .id(ipSetId)
                        .name(ipSetName)
                        .addresses(cidrList)
                        .scope(Scope.REGIONAL)
                        .lockToken(lockToken)
                        .build();
                wafv2Client.updateIPSet(updateIpSetRequest);
            }
        }
        updateOrInsertBlockIp(ipList, ipSetConfig);
    }

    @Override
    public String getBlockIps() {
        LambdaQueryWrapper<SysBlockIp> sysBlockIpLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<SysBlockIp> sysBlockIps = sysBlockIpMapper.selectList(sysBlockIpLambdaQueryWrapper);
        String ipAddrs = sysBlockIps.stream()
                .map(SysBlockIp::getIpAddrs)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("\n"));
        return sysBlockIps.isEmpty() ? "" : ipAddrs;
    }
}
