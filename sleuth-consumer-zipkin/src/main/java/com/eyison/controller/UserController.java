package com.eyison.controller;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
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

@RequestMapping("users")
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserClient userClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExecutorService executorService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        return userClient.queryUserById(userId);
    }

    @GetMapping(value = "/rest/{userId}")
    public ResponseEntity<UserVo> restQueryUserById(@PathVariable("userId") Long userId) {

        logger.info("restQueryUserById userId={}", userId);

        UserVo userVo = restTemplate.getForObject("http://localhost:8021/users/" + userId, UserVo.class);
        return ResponseEntity.ok().body(userVo);
    }

    @GetMapping(value = "/thread/{userId}")
    public ResponseEntity<UserVo> threadQueryUserById(@PathVariable("userId") Long userId) throws ExecutionException, InterruptedException {

        logger.info("threadQueryUserById userId={}", userId);

        Future<ResponseEntity<UserVo>> future = executorService.submit(() -> userClient.queryUserById(userId));

        while (true) {
            if (future.isDone()) {
                return future.get();
            }
        }
    }

    @GetMapping(value = "/show/propagation")
    public ResponseEntity<String> showPropagation() {
        return userClient.showPropagation();
    }


}

