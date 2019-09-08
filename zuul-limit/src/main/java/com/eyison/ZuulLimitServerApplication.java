package com.eyison;

import io.github.bucket4j.grid.GridBucketState;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;

//import org.apache.ignite.Ignite;
//import org.apache.ignite.IgniteCache;
//import org.apache.ignite.Ignition;
//import org.apache.ignite.configuration.IgniteConfiguration;

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

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulLimitServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulLimitServerApplication.class, args);
    }

    private Ignite ignite;

    @Bean
    @Qualifier("RateLimit")
    public IgniteCache<String, GridBucketState> cache() {
        ignite = Ignition.getOrStart(new IgniteConfiguration());
        return ignite.getOrCreateCache("rateLimit");
    }

    @PreDestroy
    public void destroy() {
        ignite.destroyCache("rateLimit");
    }

}
