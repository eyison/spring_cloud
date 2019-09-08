package com.eyison.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.autoconfigure.RefreshEndpointAutoConfiguration;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * <p></p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author zl
 * @version 1.0
 * @email 815438426@qq.com
 * @copyright: Copyright (c) eyison
 */


@ConditionalOnBean({RefreshEndpoint.class})
@ConditionalOnProperty("spring.cloud.config.refreshInterval")
@AutoConfigureAfter(RefreshEndpointAutoConfiguration.class)
@Configuration
public class ConfigClientRefreshConfig implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ConfigClientRefreshConfig.class);

    @Value("${spring.cloud.config.refreshInterval}")
    private long refreshInterval;

    @Autowired
    private RefreshEndpoint refreshEndpoint;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        final long interval = getRefreshIntervalInMilliseconds();

        logger.info(String.format("config refresh task with %s second delay", refreshInterval));
        scheduledTaskRegistrar.addFixedDelayTask(new IntervalTask(() -> {
            refreshEndpoint.refresh();
        }, interval, interval));
    }

    private long getRefreshIntervalInMilliseconds() {
        return refreshInterval * 1000;
    }

    @Bean
    @ConditionalOnBean({ContextRefresher.class})
    @ConditionalOnMissingBean
    public RefreshEndpoint refreshEndpoint(ContextRefresher contextRefresher) {
        return new RefreshEndpoint(contextRefresher);
    }

    @ConditionalOnMissingBean(ScheduledAnnotationBeanPostProcessor.class)
    @EnableScheduling
    @Configuration
    protected static class EnableSchedulingConfig {

    }


}
