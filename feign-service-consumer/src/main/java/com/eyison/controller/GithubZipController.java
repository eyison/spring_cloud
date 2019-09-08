package com.eyison.controller;

import com.eyison.client.GithubZipClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
@RequestMapping("githubzip")
@RestController
public class GithubZipController {

    @Autowired
    private GithubZipClient githubZipClient;

    @GetMapping(value = "/_search")
    public ResponseEntity<byte[]> searchGithubRepo(@RequestParam("q") String q) {
        return githubZipClient.searchRepo(q);
    }

}
