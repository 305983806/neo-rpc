package com.neo.rpc.sample.api.api;

import com.neo.rpc.sample.api.bean.Book;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 17:30
 * @Description:
 */
public interface HelloService {
    String say(String name);
    Book get();
}
