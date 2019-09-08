package com.eyison.service;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@Service(version = "1.0.0", protocol = {"dubbo"})
public class EchoDubboServiceImpl implements EchoDubboService {

    private final Logger logger = LoggerFactory.getLogger(EchoDubboServiceImpl.class);

    @Override
    public String echo(String name) {

        logger.info("echo dubbo :{}", name);
        return "hello " + name;
    }
}
