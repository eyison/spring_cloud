package com.eyison.commands;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

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

public class UserServiceCacheCommand extends HystrixCommand<ResponseEntity<UserVo>> {

    private final Logger logger = LoggerFactory.getLogger(UserServiceCacheCommand.class);

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("CommandKey");


    private Long userId;

    private UserClient userClient;

    public UserServiceCacheCommand(Long userId, UserClient userClient) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCacheCommand")).andCommandKey(GETTER_KEY));
        this.userId = userId;
        this.userClient = userClient;
    }


    @Override
    protected ResponseEntity<UserVo> run() throws Exception {
        return userClient.queryUserById(userId);
    }

    @Override
    protected String getCacheKey() {
        return userId.toString();
    }

    public static void cleanCache(Long userId) {
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(userId.toString());
    }


}
