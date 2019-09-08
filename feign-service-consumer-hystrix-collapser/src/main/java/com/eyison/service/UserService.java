package com.eyison.service;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import com.netflix.hystrix.HystrixCollapser.Scope;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

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
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserClient userClient;

    public ResponseEntity<UserVo> queryUserById(Long userId) {
        return userClient.queryUserById(userId);
    }

    @HystrixCollapser(batchMethod = "queryUsersByIds", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
    })
    public Future<UserVo> queryUserByIdCollapser(Long userId) {
        return null;
    }

    @HystrixCommand
    public List<UserVo> queryUsersByIds(List<Long> userIdList) {

        logger.info("queryUsersByIds size={}", userIdList.size());
        List<UserVo> userVoList = new ArrayList<>();
        for (Long userId : userIdList) {
            userVoList.add(userClient.queryUserById(userId).getBody());
        }

        return userVoList;
    }

    @HystrixCollapser(batchMethod = "queryUsersByIds", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
    })
    public UserVo queryUserByIdCollapserSync(Long userId) {
        return null;
    }


    @HystrixCollapser(batchMethod = "queryUsersByIdsGlobal", scope = Scope.GLOBAL, collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "10000")
    })
    public Future<UserVo> queryUserByIdCollapserGlobal(Long userId) {
        return null;
    }

    @HystrixCommand
    public List<UserVo> queryUsersByIdsGlobal(List<Long> userIdList) {

        logger.info("queryUsersByIdsGlobal size={}", userIdList.size());

        List<UserVo> userVoList = new ArrayList<>();
        for (Long userId : userIdList) {
            userVoList.add(userClient.queryUserById(userId).getBody());
        }

        return userVoList;
    }


}
