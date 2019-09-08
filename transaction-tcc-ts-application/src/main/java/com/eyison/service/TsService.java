package com.eyison.service;

import com.atomikos.tcc.rest.ParticipantLink;
import com.atomikos.tcc.rest.Transaction;
import com.eyison.model.T1;
import com.eyison.model.T2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Value("${tcc.participant.t1Service}")
    private String t1ServiceUrlTpl;

    @Value("${tcc.participant.t2Service}")
    private String t2ServiceUrlTpl;

    @Autowired
    private TccCoordinatorClient tccCoordinatorClient;

    @Value("${tcc.transaction.timeoutInMs}")
    private long transactionTtimeoutInMs;

    @Autowired
    private RestTemplate restTemplate;


    public void addTsOk() {

        String txId = UUID.randomUUID().toString();
        long expireTime = System.currentTimeMillis() + transactionTtimeoutInMs;

        String t1ServiceUrl = String.format(t1ServiceUrlTpl, txId);
        String t2ServiceUrl = String.format(t2ServiceUrlTpl, txId);

        List<ParticipantLink> participantLinks = new ArrayList<>(2);
        participantLinks.add(new ParticipantLink(t1ServiceUrl, expireTime));
        participantLinks.add(new ParticipantLink(t2ServiceUrl, expireTime));
        Transaction transaction = new Transaction(participantLinks);

        try {
            T1 t1 = new T1();
            t1.setC1("c1_" + txId);
            restTemplate.postForEntity(t1ServiceUrl, t1, String.class);

            T2 t2 = new T2();
            t2.setC2("c2_" + txId);
            restTemplate.postForEntity(t2ServiceUrl, t2, String.class);

            tccCoordinatorClient.confirm(transaction);
        } catch (Exception e) {
            tccCoordinatorClient.cancel(transaction);
            String msg = e instanceof HttpStatusCodeException ? ((HttpStatusCodeException) e).getResponseBodyAsString() : e.getMessage();
            throw new RuntimeException(msg, e);
        }


    }

    public void addTsCancel() {

        String txId = UUID.randomUUID().toString();
        long expireTime = System.currentTimeMillis() + transactionTtimeoutInMs;

        String t1ServiceUrl = String.format(t1ServiceUrlTpl, txId);
        String t2ServiceUrl = String.format(t2ServiceUrlTpl, txId);

        List<ParticipantLink> participantLinks = new ArrayList<>(2);
        participantLinks.add(new ParticipantLink(t1ServiceUrl, expireTime));
        participantLinks.add(new ParticipantLink(t2ServiceUrl, expireTime));
        Transaction transaction = new Transaction(participantLinks);

        try {
            T1 t1 = new T1();
            t1.setC1("c1_" + txId);
            restTemplate.postForEntity(t1ServiceUrl, t1, String.class);

            T2 t2 = new T2();
            t2.setC2("c2_" + txId);
            restTemplate.postForEntity(t2ServiceUrl, t2, String.class);

            throw new RuntimeException("tcc test cancel");

        } catch (Exception e) {
            tccCoordinatorClient.cancel(transaction);
            String msg = e instanceof HttpStatusCodeException ? ((HttpStatusCodeException) e).getResponseBodyAsString() : e.getMessage();
            throw new RuntimeException(msg, e);
        }


    }


}
