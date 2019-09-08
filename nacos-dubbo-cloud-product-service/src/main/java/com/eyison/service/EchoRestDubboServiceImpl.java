package com.eyison.service;

import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@Service(version = "1.0.0", protocol = {"dubbo"})
public class EchoRestDubboServiceImpl implements EchoRestDubboService {

    private final Logger logger = LoggerFactory.getLogger(EchoRestDubboServiceImpl.class);

    @Override
    public String echo(String name) {

        logger.info("echo rest dubbo :{}", name);
        return "hello " + name;
    }
}
