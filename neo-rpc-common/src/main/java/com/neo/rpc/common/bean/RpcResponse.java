package com.neo.rpc.common.bean;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 15:14
 * @Description:
 */
public class RpcResponse {
    /**
     * {@link RpcRequest} 对应的请求ID
     */
    private String requestId;

    /**
     * 异常信息
     */
    private Exception exception;

    /**
     * 响应结果
     */
    private Object result;

    /**
     * 是否带有异常
     * @return boolean
     */
    public boolean hasException() {
        return exception != null;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
