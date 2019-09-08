### 常见问题
对于新接触Eureka的开发人员来说，一般会有几个困惑:
1. 为什么服务下线了，Eureka Server接口返回的信息还会存在。
2. 为什么服务上线了，Eureka Client不能及时获取到。
3. 为什么有时候会出现如下提示: <br/>
    EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE
NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOTBEING EXPIRED JUST TO BE SAFE

###解决方法
对于第一个问题， Eureka Server并不是强一致的，因此registry中会存留过期的实例信息, 这里头有几个原因: <br/>

* 应用实例异常挂掉，没能在挂掉之前告知Eureka Server要下线掉该服务实例信息。这个就需要依赖Eureka Server的EvictionTask 去剔除。
* 应用实例下线时有告知Eureka Server下线，但是由于Eureka Server的REST API有responsecache,因此需要等待缓存过期才能更新。
* Eureka Server由于开启并引入了SELF PRESERVATION模式，导致registry的信息不会因为过期而被剔除掉，直到退出SELF PRESERVATION模式。

针对Client下线没有通知Eureka Server的问题，可以调整EvictionTask的调度频率，比如下面配置将调度间隔从默认的60秒，调整为5秒:
<br /> `eureka.server.eviction-interval-timer-in-ms=5000`

针对response cache的问题，可以根据情况考虑关闭readOnlyCacheMap:
<br /> `eureka.server.use-read-only-response-cache=false`
或者调整readWriteCacheMap的过期时间:
<br /> `eureka.server.response-cache-auto-expiration-in-seconds=60`

针对SELF PRESERVATION的问题，在测试环境可以将enable-self-preservation设置为false:
<br /> `eureka.server.enable-self-preservation=false`
<br />关闭的话，则会提示: <br /> 
HE SELE PRESERVATION MODE IS TURNED OFF . THIS MAY NOT PROTECT INSTANCE EXPIRYIN CASE OF NETWORK/OTHER PROBLEMS .
<br />  或者: <br /> 
RENEWALS ARE LESSER THAN THE THRESHOLD. THE SELF PRESERVATION MODE IS TURNED OFF .THIS MAY NC PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS .

针对新服务上线，Eureka Client 获取不及时的问题，在测试环境，可以适当提高Client端拉取Server注册信息的频率，例如下面将默认的30秒改为5秒:
<br /> `eureka.client.registry-fetch-interval-seconds=5`

针对SELF PRESERVATION问题
<br /> 在实际生产过程中，经常会有网络抖动等问题造成服务实例与EurekaServer的心跳未能如期保持，但是服务实例本身是健康的，这个时候如果按照租约剔除机制剔除的话,会造成误判，如果大范围误判的话，可能会导致整个服务注册列表的大部分注册信息被删除，从而没有可用服务。Eureka 为了解决这个问题引入了SELF PRESERVATION机制，当最近一分钟接收到的续约的次数小于等于指定阈值的话，则关闭租约失效剔除，禁止定时任务剔除失效的实例，从而保护注册信息。对于开发测试环境，开启这个机制有时候反而会影响系统的持续集成，因此可以通过如下参数关闭该机制:
<br /> `eureka.server.enableSelfPreservation=false`
<br /> 在生产环境中，可以把renewalPercentThreshold及leaseRenewalIntervalInSeconds 参数调小一点，进而提高触发SELF PRESERVATION机制的门槛，比如: 
<br /> `eureka.instance.leaseRenewalIntervalInSeconds=10 ##默认30`
<br /> `eureka.server.renewalPercentThreshold=0.49 ##默认0.85`