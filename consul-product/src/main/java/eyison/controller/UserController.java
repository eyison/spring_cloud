package eyison.controller;

import eyison.vo.UserVo;
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
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping()
    public ResponseEntity<Void> saveUser(@RequestBody UserVo userVo) {

        logger.info("saveUser {}", userVo.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserVo userVo) {

        logger.info("updateUser {}", userVo.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId) {

        logger.info("queryUserById userId={}", userId);
        UserVo userVo = new UserVo();
        userVo.setUserName("name_" + userId);
        userVo.setUserId(userId);
        return ResponseEntity.ok().body(userVo);
    }

    @GetMapping(value = "/delay/{userId}")
    public ResponseEntity<UserVo> delayQueryUserById(@PathVariable("userId") Long userId) {

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
    public ResponseEntity<List<UserVo>> search(UserVo userVo) {

        logger.info("search userId={},userName={}", userVo.getUserId(), userVo.getUserName());

        UserVo user = new UserVo();
        user.setUserName("name_" + userVo.getUserName());
        user.setUserId(System.currentTimeMillis());

        return ResponseEntity.ok().body(Arrays.asList(user));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@RequestHeader("token") String token,
                                           @RequestParam(value = "userId") Long userId) {

        logger.info("deleteUser token={},userId={}", token, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file,
                                               @RequestParam(value = "userId") String userId) {

        logger.info("uploadAvatar filename={},userId={}", file.getOriginalFilename(), userId);
        String avatar = "http://img.eyison.com/234.jpg";

        return ResponseEntity.ok().body(avatar);
    }


}

