package com.eyison.service;

import com.eyison.model.ZuulRouteEntity;
import com.eyison.repository.ZuulRouteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
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

@Service
public class ZuulRouteService {

    @Autowired
    private ZuulRouteRepository zuulRouteRepository;

    public Map<String, ZuulProperties.ZuulRoute> getVaidZuulRoute() {

        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();

        ZuulRouteEntity zuulRouteExample = new ZuulRouteEntity();
        zuulRouteExample.setEnabled(true);
        Example<ZuulRouteEntity> example = Example.of(zuulRouteExample);

        List<ZuulRouteEntity> list = zuulRouteRepository.findAll(example);
        for (ZuulRouteEntity zuulRouteEntity : list) {
            if (StringUtils.isEmpty(zuulRouteEntity.getPath())) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            BeanUtils.copyProperties(zuulRouteEntity, zuulRoute);
            routes.put(zuulRoute.getPath(), zuulRoute);
        }

        return routes;
    }

}
