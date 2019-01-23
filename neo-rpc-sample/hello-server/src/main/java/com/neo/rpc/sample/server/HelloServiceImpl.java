package com.neo.rpc.sample.server;

import com.neo.rpc.sample.api.api.HelloService;
import com.neo.rpc.sample.api.bean.Book;
import com.neo.rpc.server.RpcService;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 17:33
 * @Description: RPC 接口实现
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String name) {
        return "hello " + name;
    }

    @Override
    public Book get() {
        return new Book("轻量级微服务架构（下册）", "黄勇");
    }
}
