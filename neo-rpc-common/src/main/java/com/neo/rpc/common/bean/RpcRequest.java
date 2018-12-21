package com.neo.rpc.common.bean;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 15:05
 * @Description: 封装 RPC 请求
 */
public class RpcRequest {
    /**
     * {@link RpcRequest} 的请求ID
     */
    private String requestId;

    /**
     * {@link RpcRequest} 请求的接口名称
     */
    private String interfaceName;

    /**
     * {@link RpcRequest} 请求的方法名称
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数对象
     */
    private Object[] parameters;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
