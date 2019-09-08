package com.eyison.controller;

import com.eyison.service.GrpcClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class GrpcClientController {

    private static final Logger log = LoggerFactory.getLogger(GrpcClientController.class);

    @Autowired
    private GrpcClientService grpcClientService;

    @RequestMapping("/grpc")
    public String printMessage(@RequestParam String text) {
        return grpcClientService.sendMessage(text);
    }
}