### Eureka Client 核心参数

##### 基础参数
| 参数 | 默认值 | 说明 |
| --- | --- | --- |
| eureka.client.availability-zones |  | 告知Client有哪些region及availability-zones，支持配置修改运行时生效 |
| eureka.client.filter-only-up-instances | true | 是否过滤出InstanceStatus 为UP的实例 | 
| eureka.client.region | us-east-1 | 指定该应用实例所在的region, AWS datacenters 适用 |
| eureka.client.register-with-eureka | true | 是否将该应用实例注册到Eureka Server |
| eureka.client.prefer-same-zone-eureka | true | 是否优先使用与该应用实例处于相同zone的Eureka Server |
| eureka.client.on-demand-update-status-change | true | 是否将本地实例状态的更新通过ApplicationInfoManager实时触发同步(有请求流控限制)到Eureka Server |
| eureka.instance.metadata-map | | 指定应用实例的元数据信息 |
| eureka.instance.prefer-ip-address | false | 是否优先使用ip地址来替代hostname作为实例的hostName字段值 |
| eureka.instance.lease-expiration-duration-in-seconds | 90 | 指定EurekaClient间隔多久需要向EurekaServer发送心跳来告知Eureka Server该实例还存活 |


##### Eureka Client定时任务参数
| 参 数 | 默认值 | 说 明 |
| ---  | --- | --- |
| eureka.client.cache-refresh-executor-thread-pool-size | 2 | 刷新缓存的CacheRefreshThread的线程池大小 |
| eureka.client.cache-refresh-executor-exponential-back-off-bound | 10 | 调度任务执行超时时下次的调度的延时时间 |
| eureka.client.heartbeat-executor-thread-pool-size | 2 | 心跳线程HeartbeatThread的线程池大小 |
| eureka.client.heartbeat-executor-exponential-back-off-bound | 10 | 调度任务执行超时时下次的调度的延时时间 |
| eureka.client.registry-fetch-interval-seconds | 30 | CacheRefreshThread线程的调度频率 |
| eureka.client.eureka-service-url-poll-interval-seconds | 5*60 | AsyncResolver.updateTask刷新Eureka Server地址的时间间隔 |
| eureka.client.initial-instance-info-replication-interval-seconds | 40 | InstanceInfoReplicator将实例信息变更同步到Eureka Server的初始延时时间 |
| eureka.client.instance-info-replication-interval-seconds | 30 | InstanceInfoReplicator将实例信息变更同步到Eureka Server的时间间隔 |
| eureka.instance.lease-renewal-interval-in-seconds | 30 | Eureka Client向Eureka Server 发送心跳的时间间隔 |


##### Eureka Client http相关参数
| 参 数 | 默认值 | 说 明 |
| ---  | --- | --- |
| eureka.client.eureka-server-connect-timeout-seconds | 5 | 连接超时时间 | 
| eureka.client.eureka-server-read-timeout-seconds | 8 | 读超时时间 | 
| eureka.client.eureka-server-total-connections | 200 | 连接池最大活动连接数('MaxTotal' ) |
| eureka.client.eureka-server-total-connections-per-host | 50 | 每个host能使用的最大连接数('DefaultMax-PerRoute' ) |
| eureka.client.eureka-connection-idle-timeout-seconds | 30 | 连接他中连接的空闲时间('connectionIdleTimeout' ) |