package com.eyison.controller;

import com.eyison.model.T2;
import com.eyison.service.T2Service;
import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/t2")
public class T2Controller {


    @Autowired
    private T2Service t2Service;

    @Autowired
    OmegaContext omegaContext;


    @PostMapping
    public T2 save(@RequestBody T2 t2) {
        t2.setTxId(omegaContext.globalTxId());
        return t2Service.save(t2);
    }


}
