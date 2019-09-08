package com.eyison.controller;

import com.eyison.model.T1;
import com.eyison.service.T1Service;
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
@RequestMapping("/t1")
public class T1Controller {


    @Autowired
    private T1Service t1Service;

    @Autowired
    OmegaContext omegaContext;

    @PostMapping("/saveOK")
    public T1 saveOK(@RequestBody T1 t1) {
        t1.setTxId(omegaContext.globalTxId());
        return t1Service.save(t1);
    }

    @PostMapping("/saveFail")
    public T1 saveFail(@RequestBody T1 t1) {
        t1.setTxId(omegaContext.globalTxId());
        return t1Service.saveFail(t1);
    }


}
