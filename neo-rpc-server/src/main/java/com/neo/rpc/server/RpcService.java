package com.neo.rpc.server;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 16:25
 * @Description: RPC 服务注解（标注在服务实现类上）
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface RpcService {
    /**
     * 服务接口类
     * @return
     */
    Class<?> value();
}
