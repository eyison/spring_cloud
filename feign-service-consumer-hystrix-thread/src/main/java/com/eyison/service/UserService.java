package com.eyison.service;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

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


    public void deleteUser(Long userId) {
        String token = RequestContextHolder.currentRequestAttributes().getAttribute("token", RequestAttributes.SCOPE_REQUEST).toString();
        logger.info("deleteUser token={},userId={},threadId={}", token, userId, Thread.currentThread().getId());
        userClient.deleteUser(token, userId);
    }

    @HystrixCommand
    public void deleteUserCommand(Long userId) {
        String token = RequestContextHolder.currentRequestAttributes().getAttribute("token", RequestAttributes.SCOPE_REQUEST).toString();
        logger.info("deleteUserCommand token={},userId={},threadId={}", token, userId, Thread.currentThread().getId());
        userClient.deleteUser(token, userId);
    }


}
