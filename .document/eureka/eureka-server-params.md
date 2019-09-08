### Eureka Server 核心参数

##### 基础参数
| 参数 | 默认值 | 说 明 |
| --- | --- | --- |
| eureka.server.enable-self-preservation | true | 是否开启自我保护模式 |
| eureka.server.renewal-percent-threshold | 0.85 | 指定每分钟需要收到的续约次数的阈值 |
| eureka.instance.registry.expected-number-of-clients-sending-renews | 1 | 指定每分钟需要收到的续约次数值，实际该值在其中被写死为count*2,另外也会被更新 |
| eureka.server.renewal-threshold-update-interval-ms | 15分钟 | 指定updateRenewalThreshold定时任务的调度频率,来动态更新expectedNumber OfRenewsPerMin及number OfRenewsPerMinThreshold值 |
| eureka.server.eviction-interval-timer-in-ms | 60*1000 | 过期的实例指定EvictionTask 定时任务的调度频率，用于剔除 |


##### Eureka Server response cache参数
Eureka Server 为了提升自身REST API接口的性能，提供了两个缓存: 一个是基于ConcurrentMap的readOnlyCacheMap， 一个是基于Guava Cache的readWriteCacheMap。 
 
| 参 数 | 默认值 | 说 明 |
| --- | --- | --- |
| eureka.server.use-read-only-response-cache | true | 是否使用只读的response-cache |
| eureka.server.response-cache-update-interval-ms |  30*1000 | 设置CacheUpdateTask的调度时间间隔，用于从readWrite-CacheMap更新数据到readOnlyCacheMap。仅仅在eureka.server.use-read-only-response-cache 为true的时候才生效|
| eureka.server.response-cache-auto-expiration-in-seconds | 180 | 设置readWriteCacheMap的expireAfterWrite参数，指定写入多长时间后过期 |


##### Eureka Server peer相关参数
| 参 数 | 默认值 |  说 明 |
| --- | --- | --- |
| eureka.server.peer-eureka-nodes-update-interval-ms | 10分钟 | 指定peersUpdateTask调度的时间间隔，用于从配置文件刷新peerEurekaNodes节点的配置信息(eureka.client.serviceUrl相关zone的配置') |
| eureka.server.peer-eureka-status-refresh-time-interval-ms | 30* 1000 | 指定更新peer nodes状态信息的时间间隔|



##### Eureka Server http相关参数
| 参 数 | 默认值 | 说 明 |
| --- | --- | --- |
| eureka.server.peer-node-connect-timeout-ms | 200 | 连接超时时间 |
| eureka.server.peer-node-read-timeout-ms | 200 | 读超时时间 | 
| eureka.server.peer-node-total-connections | 1000 | 连接池最大活动连接数(MaxTotal') |
| eureka.server.peer-node-total-connections-per-host | 500 | 每个host能使用的最大连接数('DefaultMaxPerRoute') | 
| eureka.server.peer-node-connection-idle-timeout-seconds | 30 | 连接池中连接的空闲时间('connectionIdleTimeout) |




