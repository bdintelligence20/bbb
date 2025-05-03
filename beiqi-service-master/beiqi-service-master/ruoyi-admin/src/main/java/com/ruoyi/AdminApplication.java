package com.ruoyi;

import com.ruoyi.common.config.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 后台管理系统启动程序
 *
 * @author thh
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@Slf4j
@EnableAsync
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        log.info("后台管理系统启动成功！");
    }
}
