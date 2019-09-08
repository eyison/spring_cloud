package com.eyison.service;

import com.eyison.model.T1;
import com.eyison.repository.T1Repository;
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
public class T1Service {

    @Autowired
    private T1Repository t1Repository;

    public void save(T1 t1) {

        Date now = new Date();
        t1.setD(now);

        t1Repository.save(t1);
    }


    public T1 findByTxId(String txId) {
        return t1Repository.findT1ByTxId(txId);
    }

    public void delete(Integer id) {
        t1Repository.deleteById(id);
    }

}
