package com.eyison.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


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

@Configuration
public class TccAtomikosConfig {

    @Bean
    public AtomikosTccSpringAdapter atomikosTccSpringAdpater() {
        return new AtomikosTccSpringAdapter();
    }

    public static class AtomikosTccSpringAdapter {
        @PostConstruct
        public void start() {
            com.atomikos.icatch.config.Configuration.init();
        }

        @PreDestroy
        public void shutdown() {
            com.atomikos.icatch.config.Configuration.shutdown(false);
        }
    }
}
