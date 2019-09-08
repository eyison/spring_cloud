package com.eyison.controller;

import com.eyison.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        UserVo userVo = new UserVo();
        userVo.setUserName("name_" + userId);
        userVo.setUserId(userId);
        return ResponseEntity.ok().body(userVo);
    }

    @PostMapping(value = "/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file,
                                               @RequestParam(value = "userId") String userId) throws IOException {

        logger.info("uploadAvatar filename={},userId={}", file.getOriginalFilename(), userId);
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return ResponseEntity.ok().body(fileToSave.getAbsolutePath());
    }


}

