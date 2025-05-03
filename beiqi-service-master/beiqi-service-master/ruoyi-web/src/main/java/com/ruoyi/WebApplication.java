package com.ruoyi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 门户系统启动程序
 *
 * @author thh
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@EnableAsync
@EnableCaching
@Slf4j
public class WebApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WebApplication.class, args);
        log.info("门户系统启动成功！");
    }
}
