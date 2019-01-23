package com.neo.rpc.registry;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auther: cp.Chen
 * @Date: 2018/12/20 15:50
 * @Description: 服务注册
 */
@Component
public class ServiceRegistry {
    private static final Logger log = LoggerFactory.getLogger(ServiceRegistry.class);

    @Value("${rpc.registry.address}")
    private String zkAddress;

    private ZkClient zkClient;

    /**
     * "@PostConstruct"：该方法将在对象创建完毕后（即构造方法执行后）被 Spring 框架调用。
     * 在该方法中，我们使用 ZkClient 连接了 Zookeeper 服务器
     */
    @PostConstruct
    public void init(){
        // 创建 zookeeper 客户端
        zkClient = new ZkClient(zkAddress, Constant.ZK_SESSION_TIMEOUT, Constant.ZK_CONNECTION_TIMEOUT);
        log.info("connect to zookeeper");
    }

    /**
     * 实现服务注册功能
     * <p>我们首先创建了一个名为 registry 的根节点，应该节点是持久类型的，因为它有子节点。
     * <p>随后创建了一个包含服务名称的 service 节点，该节点仍然是持久的。
     * <p>最后创建了一个对应服务地址的 address 节点，该节点是临时类型的，因为它不再有子节点，而且当客户端与服务端断开连接后，Zookeeper
     * 的 “心中检测” 失败，此时将自动移出该节点。
     *
     * @param serviceName
     * @param serviceAddress
     */
    public void register(String serviceName, String serviceAddress) {
        // 创建 registry 节点（持久）
        String registryPath = Constant.ZK_REGISTRY_PATH;
        if (!zkClient.exists(registryPath)) {
            zkClient.createPersistent(registryPath);
            log.info("create registry node: {}", registryPath);
        }
        // 创建 service 节点（持久）
        String servicePath = registryPath + "/" + serviceName;
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath);
            log.info("create service node: {}", servicePath);
        }
        // 创建 address 节点（临时）
        String addressPath = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        log.info("create address node: {}", addressNode);
    }
}
