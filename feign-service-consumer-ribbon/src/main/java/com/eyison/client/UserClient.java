package com.eyison.client;

import com.eyison.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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


}
