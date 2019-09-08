package com.eyison.controller;

import com.eyison.service.TsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
public class TsController {


    @Value("${spring.application.name}")
    private String application;

    @Autowired
    private TsService tsService;


    @GetMapping("/addTsOk")
    public ResponseEntity addTsOk() {
        tsService.addTsOk();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/addTsCancel")
    public ResponseEntity addTsCancel() {
        tsService.addTsCancel();
        return ResponseEntity.ok().build();
    }


}
