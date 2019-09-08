package com.eyison.controller;

import com.eyison.client.GithubClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("github")
@RestController
public class GithubController {

    @Autowired
    private GithubClient githubClient;


    @GetMapping(value = "/_search")
    public ResponseEntity<String> searchGithubRepo(@RequestParam("q") String q) {
        return githubClient.searchRepo(q);
    }

}
