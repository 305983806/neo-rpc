package com.neo.rpc.common;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 16:17
 * @Description:
 */
public class RpcException extends RuntimeException {
    public RpcException() {
        super();
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(Throwable cause) {
        super(cause);
    }

    protected RpcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
