# neo-rpc
为了解决分布上式应用程序之间的 RPC 调用问题，我们决定自行开发一套 RPC 框架。这款 RPC 框架需要做到足够轻量级，首先功能必须够用且用法必须简单，其次性能必须高且稳定性必须有保障。我们会将 Netty 作为主要的技术造型，同时也会用到一些其他相关的开源技术。

## 架构设计
对于这款分布式 RPC 框架而言，我们希望它具备 以下基本特性。
- 具备高性能、高并发、高可用能力；
为了满足第一点特性，我们可通过 Netty 实现高性能与高并发，因为 Netty 提供了 NIO（Non-blocking I/O，非阻塞输入/输出）支持，具备异步通信与事件驱动特性，可用来开发 RPC 框架的服务端与客户端。    
- 客户端无需知道服务端的具体信息；
我们使用 ZooKeeper 来注册服务端的配置信息（包括服务名称、主机名、端口号等），在客端通过 RPC 来调用服务端之前，需要先从 ZooKeeper 这个“服务注册中心”中获取服务端的配置信息（该操作称为“服务发现”），进而通过主机名与端口号去连接相应的服务端，随后通过调用本地接口来执行 RPC 请求的远程调用，最终获取服务端的响应结果。更重要的是，ZooKeeper 与服务端建立了长连接，可定时进行“心跳检测”，确保服务端是否正常运行，当进行服务发现时只会获取正常运行的服务端配置信息。因此，使用 ZooKeeper 也能同时满足高可用要求与以上第二点特性。  
- 客户端与服务端均可面向接口编程。
与 gRPC 这类 RPC 框架类似，我们希望首先能定义出 RPC 接口，并基于这个接口来开发服务端与客户端。与就是说，在我们自行开发的 RPC 框架中同样能做到面向接口编程，这是第三点特性。    
