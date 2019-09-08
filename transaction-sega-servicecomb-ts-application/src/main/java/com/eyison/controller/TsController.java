package com.eyison.controller;

import com.eyison.model.T1;
import com.eyison.model.T2;
import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.servicecomb.pack.omega.context.annotations.SagaStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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
@RequestMapping("/ts")
public class TsController {

    @Value("${omega.t1OkService}")
    private String t1OkServiceUrl;

    @Value("${omega.t1FailService}")
    private String t1FailServiceUrl;

    @Value("${omega.t2Service}")
    private String t2ServiceUrl;

    @Autowired
    private RestTemplate omegaRestTemplate;

    @Autowired
    OmegaContext omegaContext;


    @SagaStart
    @GetMapping("/saveOk")
    public ResponseEntity saveOk() {
        Date now = new Date();
        T1 t1 = new T1();
        t1.setC1("c1_sega_" + now.getTime());
        omegaRestTemplate.postForEntity(t1OkServiceUrl, t1, String.class);
        T2 t2 = new T2();
        t2.setC2("c2_sega_" + now.getTime());
        omegaRestTemplate.postForEntity(t2ServiceUrl, t2, String.class);
        return ResponseEntity.ok().build();
    }

    @SagaStart
    @GetMapping("/saveFail")
    public ResponseEntity saveFail() {
        Date now = new Date();
        T2 t2 = new T2();
        t2.setC2("c2_sega_" + now.getTime());
        omegaRestTemplate.postForEntity(t2ServiceUrl, t2, String.class);
        T1 t1 = new T1();
        t1.setC1("c1_sega_" + now.getTime());
        omegaRestTemplate.postForEntity(t1FailServiceUrl, t1, String.class);
        return ResponseEntity.ok().build();
    }


}
