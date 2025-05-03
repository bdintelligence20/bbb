package com.ruoyi.common.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author liuzhuoming
 */
@Component
@Slf4j
@Getter
public class ThreadPoolExecutorHandler {
    private ThreadPoolExecutor threadPoolExecutor;


    @PostConstruct
    public void init() {
        log.info("executor-pool init");
        threadPoolExecutor = new ThreadPoolExecutor(
                10, 50,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000));
    }

    @PreDestroy
    public void destroy() {
        threadPoolExecutor.shutdown();
        log.warn("executor-pool destroy");
    }
}
