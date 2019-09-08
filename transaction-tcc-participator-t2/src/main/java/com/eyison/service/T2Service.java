package com.eyison.service;

import com.eyison.model.T2;
import com.eyison.repository.T2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class T2Service {

    @Autowired
    private T2Repository t2Repository;

    public void save(T2 t2) {

        Date now = new Date();
        t2.setD(now);

        t2Repository.save(t2);
    }


    public T2 findByTxId(String txId) {
        return t2Repository.findT1ByTxId(txId);
    }

    public void delete(Integer id) {
        t2Repository.deleteById(id);
    }

}
