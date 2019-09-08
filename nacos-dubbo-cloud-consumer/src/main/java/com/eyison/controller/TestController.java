package com.eyison.controller;

import com.eyison.service.EchoDubboService;
import com.eyison.service.EchoRestDubboService;
import com.eyison.service.EchoRestService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private EchoRestService echoRestService;

    @Reference(version = "1.0.0", protocol = "dubbo")
    private EchoDubboService echoDubboService;

    @Reference(version = "1.0.0", protocol = "dubbo")
    private EchoRestDubboService echoRestDubboDubboService;

    @Autowired
    private EchoRestDubboService echoRestDubboRestService;

    @GetMapping("/echoRest")
    String echoRest(String name) {

        return echoRestService.echo(name);
    }

    @GetMapping("/echoDubbo")
    String echoDubboService(String name) {

        return echoDubboService.echo(name);
    }

    @GetMapping("/echoRestDubboDubbo")
    String echoRestDubboDubbo(String name) {

        return echoRestDubboDubboService.echo(name);
    }

    @GetMapping("/echoRestDubboRest")
    String echoRestDubboRest(String name) {

        return echoRestDubboRestService.echo(name);
    }

}

