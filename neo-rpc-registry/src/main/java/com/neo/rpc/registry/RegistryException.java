package com.neo.rpc.registry;

import com.neo.rpc.common.RpcException;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 16:18
 * @Description:
 */
public class RegistryException extends RpcException {
    public RegistryException() {
        super();
    }

    public RegistryException(String message) {
        super(message);
    }

    public RegistryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistryException(Throwable cause) {
        super(cause);
    }

    protected RegistryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
