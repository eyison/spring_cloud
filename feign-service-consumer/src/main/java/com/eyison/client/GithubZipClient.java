package com.eyison.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@Profile({"config"})
@FeignClient(name = "github-zip-client", url = "https://api.github.com")
public interface GithubZipClient {

    @GetMapping(value = "/search/repositories")
    ResponseEntity<byte[]> searchRepo(@RequestParam("q") String queryStr);

}
