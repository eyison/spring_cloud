package com.eyison.filters

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.exception.ZuulException
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants
import org.springframework.util.StringUtils

import javax.servlet.http.HttpServletRequest

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

class GroovyAgentFilter extends ZuulFilter {
    @Override
    String filterType() {
        return FilterConstants.PRE_TYPE
    }

    @Override
    int filterOrder() {
        return 1
    }

    @Override
    boolean shouldFilter() {
        return true
    }

    @Override
    Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (!isValidAgent(request)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("User-Agent无效！");
            return null;
        }

        return null
    }

    private boolean isValidAgent(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        if (StringUtils.isEmpty(agent)) {
            return false;
        }

        return true;
    }


}
