package com.eyison.config;

import com.eyison.service.ZuulRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class DynamicZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private ZuulRouteService zuulRouteService;

    @Bean
    public DynamicZuulRouteLocator routeLocator() {
        return new DynamicZuulRouteLocator(
                serverProperties.getServlet().getContextPath(), zuulProperties, zuulRouteService);
    }

    public static class DynamicZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

        private ZuulProperties zuulProperties;
        private ZuulRouteService zuulRouteService;

        public DynamicZuulRouteLocator(String servletPath, ZuulProperties zuulProperties, ZuulRouteService zuulRouteService) {
            super(servletPath, zuulProperties);
            this.zuulProperties = zuulProperties;
            this.zuulRouteService = zuulRouteService;
        }


        @Override
        public void refresh() {
            doRefresh();
        }

        @Override
        protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {

            LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
            routesMap.putAll(super.locateRoutes());
            routesMap.putAll(zuulRouteService.getVaidZuulRoute());
            LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
            routesMap.forEach((key, value) -> {
                String path = key;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
                if (StringUtils.hasText(zuulProperties.getPrefix())) {
                    path = zuulProperties.getPrefix() + path;
                    if (!path.startsWith("/")) {
                        path = "/" + path;
                    }
                }
                values.put(path, value);
            });
            return values;

        }
    }
}
