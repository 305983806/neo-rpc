package com.neo.rpc.sample.client;

import com.neo.rpc.client.RpcClient;
import com.neo.rpc.sample.api.api.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 17:37
 * @Description:
 */
@SpringBootApplication(scanBasePackages = "com.neo.rpc")
public class Client {
    @Autowired
    private RpcClient rpcClient;

    @PostConstruct
    public void run() {
        HelloService helloService = rpcClient.create(HelloService.class);
        System.out.println(helloService.say("cp"));
        System.out.println(helloService.get().toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }
}
