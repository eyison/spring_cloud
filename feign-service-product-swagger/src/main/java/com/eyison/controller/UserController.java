package com.eyison.controller;

import com.eyison.vo.UserVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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
@Api(tags = {"用户管理"}, description = "用户管理相关接口")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping()
    @ApiOperation(value = "添加用户", notes = "添加用户接口")
    @ApiResponses({@ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 400, message = "参数不正确"),
            @ApiResponse(code = 500, message = "服务错误")})
    public ResponseEntity<Void> saveUser(@ApiParam(value = "用户信息参数") @RequestBody UserVo userVo) {

        logger.info("saveUser {}", userVo.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    @ApiOperation(value = "更新用户", notes = "更新用户信息接口")
    public ResponseEntity<Void> updateUser(@ApiParam(value = "用户信息参数") @RequestBody UserVo userVo) {

        logger.info("updateUser {}", userVo.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息接口", response = UserVo.class, responseContainer = "Map")
    public ResponseEntity<UserVo> queryUserById(@ApiParam(value = "用户ID", required = true) @PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        UserVo userVo = new UserVo();
        userVo.setUserName("name_" + userId);
        userVo.setUserId(userId);
        return ResponseEntity.ok().body(userVo);
    }

    @GetMapping(value = "/delay/{userId}")
    @ApiOperation(value = "延迟查询用户信息", notes = "延迟查询用户信息接口", response = UserVo.class, responseContainer = "Map")
    public ResponseEntity<UserVo> delayQueryUserById(@ApiParam(value = "用户ID", required = true) @PathVariable("userId") Long userId) {

        logger.info("delayQueryUserById userId={}", userId);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserVo userVo = new UserVo();
        userVo.setUserName("name_" + userId);
        userVo.setUserId(userId);
        return ResponseEntity.ok().body(userVo);
    }

    @GetMapping(value = "/_search")
    @ApiOperation(value = "搜索用户信息", notes = "搜索用户信息接口", response = UserVo.class, responseContainer = "List")
    public ResponseEntity<List<UserVo>> search(UserVo userVo) {

        logger.info("search userId={},userName={}", userVo.getUserId(), userVo.getUserName());

        UserVo user = new UserVo();
        user.setUserName("name_" + userVo.getUserName());
        user.setUserId(System.currentTimeMillis());

        return ResponseEntity.ok().body(Arrays.asList(user));
    }

    @DeleteMapping()
    @ApiOperation(value = "删除用户", notes = "删除用户信息接口")
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "token", required = true) @RequestHeader("token") String token,
                                           @ApiParam(value = "用户ID", required = true) @RequestParam(value = "userId") Long userId) {

        logger.info("deleteUser token={},userId={}", token, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/avatar")
    @ApiOperation(value = "上传用户头像", notes = "上传用户头像接口")
    public ResponseEntity<String> uploadAvatar(@ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file,
                                               @ApiParam(value = "用户ID", required = true) @RequestParam(value = "userId") String userId) {

        logger.info("uploadAvatar filename={},userId={}", file.getOriginalFilename(), userId);
        String avatar = "http://img.eyison.com/234.jpg";

        return ResponseEntity.ok().body(avatar);
    }


}

