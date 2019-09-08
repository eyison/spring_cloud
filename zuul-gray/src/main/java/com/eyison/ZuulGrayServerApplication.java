package com.eyison;

import com.eyison.filters.ZuulGrayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

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
public class ZuulGrayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGrayServerApplication.class, args);
    }

    @Bean
    public ZuulGrayFilter zuulGrayFilter() {
        return new ZuulGrayFilter();
    }
}
