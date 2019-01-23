package com.neo.rpc.sample.server;

import com.neo.rpc.server.RpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 17:34
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.neo.rpc")
public class Server {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Server.class, args);
        ctx.getBean(RpcServer.class).start();
    }
}
