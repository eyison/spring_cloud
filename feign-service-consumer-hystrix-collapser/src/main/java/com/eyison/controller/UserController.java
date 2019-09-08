package com.eyison.controller;

import com.eyison.client.UserClient;
import com.eyison.service.UserService;
import com.eyison.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
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
    private UserService userService;

    @Autowired
    private UserClient userClient;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        return userService.queryUserById(userId);
    }

    @GetMapping(value = "/queryUserByIdCollapser")
    public ResponseEntity<List<UserVo>> queryUserByIdCollapser() throws ExecutionException, InterruptedException {

        Future<UserVo> userVoFuture1 = userService.queryUserByIdCollapser(11L);
        Future<UserVo> userVoFuture2 = userService.queryUserByIdCollapser(12L);
        Future<UserVo> userVoFuture3 = userService.queryUserByIdCollapser(13L);
        Future<UserVo> userVoFuture4 = userService.queryUserByIdCollapser(13L);
        List<UserVo> list = new ArrayList<>();
        list.add(userVoFuture1.get());
        list.add(userVoFuture2.get());
        list.add(userVoFuture3.get());
        list.add(userVoFuture4.get());

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/queryUserByIdCollapserSync")
    public ResponseEntity<List<UserVo>> queryUserByIdCollapserSync() {

        UserVo userVo1 = userService.queryUserByIdCollapserSync(21L);
        UserVo userVo2 = userService.queryUserByIdCollapserSync(22L);
        UserVo userVo3 = userService.queryUserByIdCollapserSync(23L);
        UserVo userVo4 = userService.queryUserByIdCollapserSync(23L);
        List<UserVo> list = new ArrayList<>();
        list.add(userVo1);
        list.add(userVo2);
        list.add(userVo3);
        list.add(userVo4);

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/queryUserByIdCollapserGlobal1")
    public ResponseEntity<List<UserVo>> queryUserByIdCollapserGlobal1() throws ExecutionException, InterruptedException {

        Future<UserVo> userVoFuture1 = userService.queryUserByIdCollapserGlobal(31L);
        Future<UserVo> userVoFuture2 = userService.queryUserByIdCollapserGlobal(32L);
        Future<UserVo> userVoFuture3 = userService.queryUserByIdCollapserGlobal(33L);
        List<UserVo> list = new ArrayList<>();
        list.add(userVoFuture1.get());
        list.add(userVoFuture2.get());
        list.add(userVoFuture3.get());

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/queryUserByIdCollapserGlobal2")
    public ResponseEntity<List<UserVo>> queryUserByIdCollapserGlobal2() throws ExecutionException, InterruptedException {

        Future<UserVo> userVoFuture1 = userService.queryUserByIdCollapserGlobal(32L);
        Future<UserVo> userVoFuture2 = userService.queryUserByIdCollapserGlobal(23L);
        Future<UserVo> userVoFuture3 = userService.queryUserByIdCollapserGlobal(34L);
        List<UserVo> list = new ArrayList<>();
        list.add(userVoFuture1.get());
        list.add(userVoFuture2.get());
        list.add(userVoFuture3.get());

        return ResponseEntity.ok(list);
    }


}

