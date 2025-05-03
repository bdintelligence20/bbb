package com.ruoyi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * EMAIL 配置
 *
 * @author liuzhuoming
 */
@Component
@ConfigurationProperties(prefix = "email")
@Data
public class EmailConfig {
    private TestDriveConfig testDrive;
    private SubscribeConfig subscribe;
    private PublishConfig publish;
    private ContactUsConfig contactUs;
    private MediaContactConfig mediaContact;
    private JoinUsConfig joinUs;
    private RetrieveConfig retrieve;

    /**
     * 找回密码
     */
    @Data
    public static class RetrieveConfig{
        private boolean enabled;
        private String from;
    }

    /**
     * 试驾申请
     */
    @Data
    public static class TestDriveConfig {
        private boolean enabled;
        private String from;
        private List<String> cc;
    }

    /**
     * 订阅申请
     */
    @Data
    public static class SubscribeConfig {
        private boolean enabled;
        private String from;
        private String to;
    }


    /**
     * 订阅发布
     */
    @Data
    public static class PublishConfig {
        private boolean enabled;
        private String from;
    }

    /**
     * 联系我们
     */
    @Data
    public static class ContactUsConfig {
        private boolean enabled;
        private String from;
        private String to;
    }

    /**
     * 媒体联系
     */
    @Data
    public static class MediaContactConfig {
        private boolean enabled;
        private String from;
        private String to;
    }

    /**
     * 招聘
     */
    @Data
    public static class JoinUsConfig {
        private boolean enabled;
        private String from;
        private String to;
        private String addr;
    }
}
