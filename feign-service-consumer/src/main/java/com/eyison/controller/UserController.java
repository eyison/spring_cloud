package com.eyison.controller;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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


    @PostMapping()
    public ResponseEntity<Void> saveUser(@RequestBody UserVo userVo) {

        logger.info("saveUser {}", userVo.toString());
        return userClient.saveUser(userVo);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserVo userVo) {

        logger.info("updateUser {}", userVo.toString());
        return userClient.updateUser(userVo);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        return userClient.queryUserById(userId);
    }

    @GetMapping(value = "/_search")
    public ResponseEntity<List<UserVo>> search(@RequestParam(value = "userId") Long userId,
                                               @RequestParam(value = "userName") String userName) {

        logger.info("search userId={},userName={}", userId, userName);

        UserVo user = new UserVo();
        user.setUserName(userName);
        user.setUserId(userId);

        return userClient.search(user);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@RequestHeader String token,
                                           @RequestParam(value = "userId") Long userId) {

        logger.info("deleteUser token={},userId={}", token, userId);
        return userClient.deleteUser(token, userId);
    }

    @PostMapping(value = "/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file,
                                               @RequestParam(value = "userId") Long userId) {

        logger.info("uploadAvatar filename={},userId={}", file.getOriginalFilename(), userId);
        return userClient.uploadAvatar(file, userId);
    }


}

