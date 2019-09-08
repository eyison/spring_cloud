package com.eyison.config;

import com.eyison.filters.MonitorGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


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

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        ZonedDateTime time1 = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        ZonedDateTime time2 = LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault());
        return builder.routes()
                .route("sogou_route", r -> r.path("/sogou")
                        .uri("http://www.sogou.com")
                )
//                .route("sogou_after_route", r -> r.after(time1)
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_before_route", r -> r.before(time2)
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_between_route", r -> r.between(time1,time2)
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_cookie_route", r -> r.cookie("which", "sogou")
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_header_route", r -> r.header("X-Request-Id", "sogou")
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_host_route", r -> r.host("www.sogou.com")
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_method_route", r -> r.method("GET")
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_query_route", r -> r.query("en","sogou")
//                        .uri("http://www.sogou.com")
//                )
//                .route("sogou_remoteaddr_route", r -> r.remoteAddr("127.0.0.1")
//                        .uri("http://www.sogou.com")
//                )
                .route("sogou_add_request_header_route", r ->
                        r.path("/addHeader").filters(f -> f.addRequestHeader("X-Request-Id", "sogou"))
                                .uri("http://www.sogou.com"))
                .route("sogou_add_request_parameter_route", r ->
                        r.path("/addParameter").filters(f -> f.addRequestParameter("t", String.valueOf(System.currentTimeMillis())))
                                .uri("http://www.sogou.com"))
                .route("sogou_rewritepath_route", r ->
                        r.path("/rewritepath/**").filters(f -> f.rewritePath("/rewritepath/(?<segment>.*)", "/$\\{segment}"))
                                .uri("http://www.sogou.com"))
                .route("sogou_add_response_header_route", r ->
                        r.path("/addResponse").filters(f -> f.addResponseHeader("X-Response-Foo", "Bar"))
                                .uri("http://www.sogou.com"))
                .route("sogou_stripPrefix_route", r ->
                        r.path("/sogou/stripPrefix/**").filters(f -> f.stripPrefix(2))
                                .uri("http://www.sogou.com"))
                .route("sogou_retry_route", r -> r.path("/sogou/retry")
                        .filters(f -> f.retry(config -> config.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
                        .uri("http://www.sogou.com"))
                .route("sogou_monitor_filter", r -> r.path("/sogou/monitor")
                        .filters(f -> f.filter(new MonitorGatewayFilter()))
                        .uri("http://www.sogou.com"))
                .build();
    }


}
