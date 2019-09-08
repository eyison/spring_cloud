package com.eyison.config;

import com.eyison.filters.GatewayRateLimitFilterByCpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


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

@Component
public class RouterConfig {


    @Autowired
    private GatewayRateLimitFilterByCpu gatewayRateLimitFilterByCpu;

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/users/delay/*")
                        .filters(f -> f.filter(gatewayRateLimitFilterByCpu))
                        .uri("http://127.0.0.1:8021/users/delay")
                        .id("cpu_rateLimit_route")
                ).build();
    }


}
