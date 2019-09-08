package com.eyison.controller;

import com.eyison.service.TsService;
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
public class TsController {

    @Autowired
    private TsService tsService;


    @GetMapping("saveOk")
    public void saveOk(String c1, String c2) {
        tsService.saveOk(c1, c2);
    }


    @GetMapping("saveRollback")
    public void saveRollback(String c1, String c2) {
        tsService.saveRollback(c1, c2);
    }

}
