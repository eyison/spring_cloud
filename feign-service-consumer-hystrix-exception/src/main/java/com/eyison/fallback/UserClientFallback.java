package com.eyison.fallback;

import com.eyison.client.UserClient;
import com.eyison.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

@Component
public class UserClientFallback implements UserClient {


    private final Logger logger = LoggerFactory.getLogger(UserClientFallback.class);

    @Override
    public ResponseEntity<UserVo> queryUserById(Long userId) {

        logger.warn("queryUserById fallback userId={}", userId);
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<UserVo> queryUserById404(Long userId) {
        logger.warn("queryUserById404 fallback userId={}", userId);
        return ResponseEntity.badRequest().build();
    }


}
