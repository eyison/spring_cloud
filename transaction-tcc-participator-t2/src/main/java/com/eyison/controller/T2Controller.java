package com.eyison.controller;

import com.eyison.model.T2;
import com.eyison.service.T2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class T2Controller extends TccParticipantorController<T2> {


    @Value("${spring.application.name}")
    private String application;

    @Autowired
    private T2Service t2Service;


    @Override
    public String getParticipantName() {
        return application;
    }

    @Override
    public ResponseEntity executeTry(String txId, T2 body) {

        body.setTxId(txId);
        body.setState(0);
        try {
            t2Service.save(body);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

    }

    @Override
    public ResponseEntity executeCancel(String txId) {
        T2 t2 = t2Service.findByTxId(txId);
        if (t2 == null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        t2.setState(-1);
        t2Service.save(t2);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity executeConfirm(String txId) {
        T2 t2 = t2Service.findByTxId(txId);
        if (t2 == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        t2.setState(1);
        t2Service.save(t2);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
