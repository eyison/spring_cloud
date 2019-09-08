package com.eyison.service;

import com.eyison.model.T1;
import com.eyison.repository.T1Repository;
import org.apache.servicecomb.pack.omega.context.OmegaContext;
import org.apache.servicecomb.pack.omega.transaction.annotations.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

    @Autowired
    OmegaContext omegaContext;

    private Set<String> executedSet = ConcurrentHashMap.newKeySet();
    private Set<String> canceledSet = ConcurrentHashMap.newKeySet();

    @Compensable(compensationMethod = "cancel")
    @Transactional
    public T1 save(T1 t1) {

        if (executedSet.contains(t1.getTxId()) || canceledSet.contains(t1.getTxId())) {
            return t1Repository.findT1ByTxId(t1.getTxId());
        }

        Date now = new Date();
        t1.setD(now);
        t1.setState(1);
        t1 = t1Repository.save(t1);

        executedSet.add(t1.getTxId());
        return t1;

    }

    @Compensable(compensationMethod = "cancel")
    @Transactional
    public T1 saveFail(T1 t1) {

        if (executedSet.contains(t1.getTxId()) || canceledSet.contains(t1.getTxId())) {
            return t1Repository.findT1ByTxId(t1.getTxId());
        }

        Date now = new Date();
        t1.setD(now);
        t1.setState(1);
        t1 = t1Repository.save(t1);

        executedSet.add(t1.getTxId());

        throw new RuntimeException("sega test t1 saveFail");

    }

    @Transactional
    public T1 cancel(T1 t1) {
        if (canceledSet.contains(t1.getTxId()) || !executedSet.contains(t1.getTxId())) {
            return t1Repository.findT1ByTxId(t1.getTxId());
        }
        T1 result = t1Repository.findT1ByTxId(t1.getTxId());
        if (result == null) {
            throw new IllegalStateException();
        }
        result.setState(-1);
        t1 = t1Repository.save(t1);

        canceledSet.add(t1.getTxId());
        return t1;
    }


}
