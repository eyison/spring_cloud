package com.eyison.service;

import com.eyison.model.T2;
import com.eyison.repository.T2Repository;
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
public class T2Service {

    @Autowired
    private T2Repository t2Repository;

    private Set<String> executedSet = ConcurrentHashMap.newKeySet();
    private Set<String> canceledSet = ConcurrentHashMap.newKeySet();

    @Compensable(compensationMethod = "cancel")
    @Transactional
    public T2 save(T2 t2) {

        if (executedSet.contains(t2.getTxId()) || canceledSet.contains(t2.getTxId())) {
            return t2Repository.findT1ByTxId(t2.getTxId());
        }

        Date now = new Date();
        t2.setD(now);
        t2.setState(1);
        t2 = t2Repository.save(t2);

        executedSet.add(t2.getTxId());
        return t2;

    }

    @Transactional
    public T2 cancel(T2 t2) {
        if (canceledSet.contains(t2.getTxId()) || !executedSet.contains(t2.getTxId())) {
            return t2Repository.findT1ByTxId(t2.getTxId());
        }
        T2 result = t2Repository.findT1ByTxId(t2.getTxId());
        if (result == null) {
            throw new IllegalStateException();
        }
        result.setState(-1);
        t2 = t2Repository.save(result);

        canceledSet.add(t2.getTxId());
        return t2;
    }


}
