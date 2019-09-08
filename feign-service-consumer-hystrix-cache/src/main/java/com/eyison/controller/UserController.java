package com.eyison.controller;

import com.eyison.client.UserClient;
import com.eyison.commands.UserServiceCacheCommand;
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

    @GetMapping(value = "/cache/{userId}")
    public ResponseEntity<UserVo> queryUserByIdCache(@PathVariable("userId") Long userId) {

        logger.info("queryUserByIdCache userId={}", userId);
        userService.queryUserByIdCache(userId);
        return userService.queryUserByIdCache(userId);
    }

    @GetMapping(value = "/cache2/{userId}")
    public ResponseEntity<UserVo> queryUserByIdCache2(@PathVariable("userId") Long userId) {

        logger.info("queryUserByIdCache2 userId={}", userId);
        userService.queryUserByIdCache(userId);
        userService.queryUserByIdCache(userId);
        return userService.queryUserByIdCache(userId + 3837);
    }

    @GetMapping(value = "/cachekey/{userId}")
    public ResponseEntity<UserVo> queryUserByIdCachekey(@PathVariable("userId") Long userId) {

        logger.info("queryUserByIdCachekey userId={}", userId);
        userService.queryUserByIdCachekey(userId);
        userService.queryUserByIdCachekey(userId);
        userService.queryUserByIdRemoveCachekey(userId);
        userService.queryUserByIdCachekey(userId);
        return userService.queryUserByIdCachekey(userId);
    }

    @GetMapping(value = "/command/{userId}")
    public ResponseEntity<UserVo> queryUserByIdCommand(@PathVariable("userId") Long userId) {
        UserServiceCacheCommand userServiceCacheCommand = new UserServiceCacheCommand(userId, userClient);
        userServiceCacheCommand.execute();

        UserServiceCacheCommand userServiceCacheCommand2 = new UserServiceCacheCommand(userId, userClient);
        userServiceCacheCommand2.execute();

        UserServiceCacheCommand.cleanCache(userId);

        UserServiceCacheCommand userServiceCacheCommand3 = new UserServiceCacheCommand(userId, userClient);
        return userServiceCacheCommand3.execute();

    }


}

