package com.eyison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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

@SpringBootApplication
@EnableDiscoveryClient
public class NacosDubboCloudProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDubboCloudProductApplication.class, args);
    }


}
