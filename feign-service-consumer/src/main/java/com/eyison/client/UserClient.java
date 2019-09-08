package com.eyison.client;

import com.eyison.vo.UserVo;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
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


@FeignClient(name = "feign-service-product")
public interface UserClient {

    @PostMapping("users")
    ResponseEntity<Void> saveUser(@RequestBody UserVo userVo);

    @PutMapping("users")
    ResponseEntity<Void> updateUser(@RequestBody UserVo userVo);

    @GetMapping(value = "users/{userId}")
    ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId);

    @GetMapping(value = "users/_search")
    ResponseEntity<List<UserVo>> search(@SpringQueryMap UserVo userVo);

    @Headers("token: {token}")
    @DeleteMapping("users")
    ResponseEntity<Void> deleteUser(@RequestHeader("token") String token,
                                    @RequestParam(value = "userId") Long userId);

    @PostMapping(value = "users/avatar", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadAvatar(@RequestPart("file") MultipartFile file,
                                        @RequestParam(value = "userId") Long userId);


}
