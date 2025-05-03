package com.ruoyi.common.config;

import com.ruoyi.common.utils.IpAddrUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AWS WAF 配置
 *
 * @author liuzhuoming
 */
@Component
@ConfigurationProperties(prefix = "aws.waf")
@Data
public class AwsWafConfig {
    private boolean enabled;
    private String region;
    private String accessKeyId;
    private String secretAccessKey;
    private IpSetConfig ipv4;
    private IpSetConfig ipv6;

    @Data
    public static class IpSetConfig {
        private String id;
        private String name;
        private IpAddrUtil.IpVersion version;
    }
}
