package com.eyison.controller;

import com.eyison.client.UserClient;
import com.eyison.commands.UserServiceCommand;
import com.eyison.vo.UserVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@RequestMapping("users")
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserClient userClient;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        return userClient.queryUserById(userId);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping(value = "/hystrix/{userId}")
    public ResponseEntity<UserVo> hystrixQueryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);

        if (userId < 0) {
            throw new IllegalArgumentException();
        }

        return userClient.queryUserById(userId);
    }

    public ResponseEntity<UserVo> fallback(Long userId) {
        logger.warn("queryUserById fallback userId={}", userId);
        return ResponseEntity.badRequest().build();
    }


    @GetMapping(value = "/404/{userId}")
    public ResponseEntity<UserVo> queryUserById404(@PathVariable("userId") Long userId) {

        logger.info("queryUserById404 userId={}", userId);
        return userClient.queryUserById404(userId);
    }


    @GetMapping("/userServiceCommand/{userId}")
    public ResponseEntity<UserVo> userServiceCommand(@PathVariable("userId") Long userId) {
        logger.info("badRequestCommand userId={}", userId);
        return new UserServiceCommand(userId, userClient).execute();
    }


}

