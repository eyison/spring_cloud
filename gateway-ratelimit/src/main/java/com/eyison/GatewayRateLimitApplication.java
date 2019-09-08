package com.eyison;

import com.eyison.config.RemoteAddressKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
public class GatewayRateLimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayRateLimitApplication.class, args);
    }


    @Bean(name = RemoteAddressKeyResolver.BEAN_NAME)
    public RemoteAddressKeyResolver remoteAddrKeyResolver() {
        return new RemoteAddressKeyResolver();
    }
}
