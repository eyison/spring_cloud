package com.eyison.controller;

import com.eyison.service.UserService;
import com.eyison.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

@RequestMapping("users")
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        return userService.queryUserById(userId);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@RequestHeader("token") String token,
                                           @RequestParam(value = "userId") Long userId) {

        logger.info("deleteUser token={},userId={},threadId={}", token, userId, Thread.currentThread().getId());
        RequestContextHolder.currentRequestAttributes().setAttribute("token", token, RequestAttributes.SCOPE_REQUEST);
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/command")
    public ResponseEntity<Void> deleteUserCommand(@RequestHeader("token") String token,
                                                  @RequestParam(value = "userId") Long userId) {

        logger.info("deleteUserCommand token={},userId={},threadId={}", token, userId, Thread.currentThread().getId());
        RequestContextHolder.currentRequestAttributes().setAttribute("token", token, RequestAttributes.SCOPE_REQUEST);
        userService.deleteUserCommand(userId);
        return ResponseEntity.ok().build();
    }


}

