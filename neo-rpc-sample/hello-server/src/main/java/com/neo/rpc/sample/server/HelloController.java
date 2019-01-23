package com.neo.rpc.sample.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/25 09:11
 * @Description:
 */
@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/test")
    public String test() {
        return "Hello cp!";
    }
}
