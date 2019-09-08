package com.eyison;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
 * @date Created in 2019/6/29 15:43
 * @copyright: Copyright (c) eyison
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("single")
public class OauthTest {

    @Test
    public void getToken() {

//        Map<String, String> params = new HashMap<>();
//        params.put("grant_type", "password");
//        params.put("client_id", clientId);
//        params.put("username", "admin");
//        params.put("password", "admin");
//        HttpTrace.Response response = RestAssured.given()
//                .auth().preemptive().basic(clientId, "secret")
//                .and().with().params(params).when()
//                .post("http://localhost:8081/spring-security-oauth-server/oauth/token");
    }


}
