package com.eyison.commands;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
public class UserServiceCommand extends HystrixCommand<ResponseEntity<UserVo>> {

    private final Logger logger = LoggerFactory.getLogger(UserServiceCommand.class);

    private Long userId;

    private UserClient userClient;

    public UserServiceCommand(Long userId, UserClient userClient) {
        super(HystrixCommandGroupKey.Factory.asKey("userServiceCommand"));
        this.userId = userId;
        this.userClient = userClient;
    }

    @Override
    protected ResponseEntity<UserVo> run() throws Exception {

        if (userId % 5 == 0) {
            throw new HystrixBadRequestException("HystrixBadRequestException error");
        } else if (userId % 2 == 0) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("IllegalArgumentException error");
            throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.COMMAND_EXCEPTION, UserServiceCommand.class, "HystrixRuntimeException error", illegalArgumentException, illegalArgumentException.getCause());
        } else if (userId % 3 == 0) {
            throw new HystrixTimeoutException();
        } else if (userId % 7 == 0) {
            return userClient.queryUserById404(userId);
        }

        return userClient.queryUserById(userId);
    }

    @Override
    protected ResponseEntity<UserVo> getFallback() {
        logger.warn("userServiceCommand getFallback userId={}", userId);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }


}
