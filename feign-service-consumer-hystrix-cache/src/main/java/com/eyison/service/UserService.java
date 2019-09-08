package com.eyison.service;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserClient userClient;

    public ResponseEntity<UserVo> queryUserById(Long userId) {
        return userClient.queryUserById(userId);
    }

    @CacheResult
    @HystrixCommand
    public ResponseEntity<UserVo> queryUserByIdCache(Long userId) {
        return userClient.queryUserById(userId);
    }

    @CacheResult
    @HystrixCommand(commandKey = "queryUserByIdCachekey")
    public ResponseEntity<UserVo> queryUserByIdCachekey(@CacheKey Long userId) {
        return userClient.queryUserById(userId);
    }

    @CacheRemove(commandKey = "queryUserByIdCachekey")
    @HystrixCommand
    public void queryUserByIdRemoveCachekey(@CacheKey Long userId) {

    }


}
