package com.eyison;

import com.eyison.config.RequestAttributeHystrixConcurrencyStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
@EnableFeignClients
@EnableHystrix
public class FeignServiceConsumerHystrixThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServiceConsumerHystrixThreadApplication.class, args);
    }

    @Bean
    public RequestAttributeHystrixConcurrencyStrategy requestAttributeHystrixConcurrencyStrategy() {
        return new RequestAttributeHystrixConcurrencyStrategy();
    }

}
