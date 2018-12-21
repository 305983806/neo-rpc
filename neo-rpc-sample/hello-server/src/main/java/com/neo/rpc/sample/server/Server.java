package com.neo.rpc.sample.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 17:34
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.neo.rpc")
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
