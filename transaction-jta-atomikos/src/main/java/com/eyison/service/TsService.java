package com.eyison.service;

import com.eyison.model.t1.T1;
import com.eyison.model.t2.T2;
import com.eyison.repository.t1.T1Repository;
import com.eyison.repository.t2.T2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
public class TsService {

    @Autowired
    private T1Repository t1Repository;

    @Autowired
    private T2Repository t2Repository;


    @Transactional
    public void saveOk(String c1, String c2) {

        Date now = new Date();
        T1 t1 = new T1();
        t1.setC1(c1);
        t1.setD(now);

        T2 t2 = new T2();
        t2.setC2(c2);
        t2.setD(now);

        t1Repository.save(t1);
        t2Repository.save(t2);
    }

    @Transactional
    public void saveRollback(String c1, String c2) {

        Date now = new Date();
        T1 t1 = new T1();
        t1.setC1(c1);
        t1.setD(now);

        T2 t2 = new T2();
        t2.setC2(c2);
        t2.setD(now);

        t1Repository.save(t1);
        t2Repository.save(t2);

        throw new RuntimeException("test jta rollback");
    }


}
