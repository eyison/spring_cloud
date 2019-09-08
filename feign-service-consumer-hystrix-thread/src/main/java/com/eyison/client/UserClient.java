package com.eyison.client;

import com.eyison.vo.UserVo;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "users/{userId}")
    ResponseEntity<UserVo> queryUserById(@PathVariable("userId") Long userId);

    @Headers("token: {token}")
    @DeleteMapping("users")
    ResponseEntity<Void> deleteUser(@RequestHeader("token") String token,
                                    @RequestParam(value = "userId") Long userId);


}
