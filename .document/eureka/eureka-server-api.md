
###Eureka的REST操作
[官方文档](https://github.com/Netflix/eureka/wiki/Eureka-REST-operations)

appID是应用程序的名称，instanceID是与实例关联的唯一ID。

默认是xml格式的数据，如果以json的数据格式交互，添加请求头即可：
```$xslt
Content-Type:application/json
Accept:application/json
```

#####操作
1. 注册新的应用实例 <br/>
    `POST /eureka/apps/{appID}`
    > curl -X 'POST' -i -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client -d '{"instance":{"instanceId":"172.18.20.40:eureka-client:8081","hostName":"172.18.20.40","app":"EUREKA-client","ipAddr":"172.18.20.40","status":"UP","overriddenStatus":"UNKNOWN","port":{"$":8081,"@enabled":"true"},"securePort":{"$":443,"@enabled":"false"},"countryId":1,"dataCenterInfo":{"@class":"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo","name":"MyOwn"},"leaseInfo":{"renewalIntervalInSecs":3,"durationInSecs":10,"registrationTimestamp":1561047684923,"lastRenewalTimestamp":1561047995666,"evictionTimestamp":0,"serviceUpTimestamp":1561047684406},"metadata":{"management.port":"8081","jmx.port":"55091"},"homePageUrl":"http://172.18.20.40:8081/","statusPageUrl":"http://172.18.20.40:8081/actuator/info","healthCheckUrl":"http://172.18.20.40:8081/actuator/health","vipAddress":"eureka-client","secureVipAddress":"eureka-client","isCoordinatingDiscoveryServer":"false","lastUpdatedTimestamp":"1561047684923","lastDirtyTimestamp":"1561047684355","actionType":"ADDED"}}'

2. 注销应用实例 <br/>
    `DELETE /eureka/apps/{appID}/{instanceID}`
    > curl -X 'DELETE' -i -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client/172.18.20.40:eureka-client:8081

3. 发送应用实例心跳 <br/>
    `PUT /eureka/apps/{appID}/{instanceID}`
    > curl -X 'PUT' -i -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client/172.18.20.40:eureka-client:8081

4. 查询所有实例 <br/>
    `GET /eureka/apps`
    > curl -X 'GET' -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps

5. 查询所有appID实例 <br/>
    `GET /eureka/apps/{appID}`
    > curl -X 'GET' -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client

6. 查询一个appID下的一个具体的实例 <br/>
    `GET /eureka/apps/{appID}/{instanceID}`
    > curl -X 'GET' -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client/172.18.20.40:eureka-client:8081

7. 查询一个具体的实例 <br/>
    `GET /eureka/instances/{instanceID}`
    > curl -X 'GET' -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/instances/172.18.20.40:eureka-client:8081

8. 下线某个实例 <br/>
    `PUT /eureka/apps/{appID}/{instanceID}/status?value=OUT_OF_SERVICE`
    > curl -X 'PUT' -i -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client/172.18.20.40:eureka-client:8081/status?value=OUT_OF_SERVICE

9. 将实例移回服务中(移除覆盖) <br/>
    `DELETE /eureka/apps/{appID}/{instanceID}/status?value=UP` <br/>
    UP是可选的，它被用作由于删除覆盖而导致的回退状态的建议 
    > curl -X 'DELETE' -i -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client/172.18.20.40:eureka-client:8081/status?value=UP

10. 更新元数据 <br/>
    `PUT /eureka/apps/{appID}/{instanceID}/metadata?key=value`
    > curl -X 'PUT' -i -H 'Content-Type:application/json' -H 'Accept:application/json' http://localhost:8761/eureka/apps/eureka-client/172.18.20.40:eureka-client:8081/metadata?profile=canary

11. 查询特定vip地址下的所有实例 <br/>
    
    `GET /eureka/vips/{vipAddress}`
    > curl -X 'GET'  http://localhost:8761/eureka/vips/eureka-client

12. 查询特定安全vip地址下的所有实例 <br/>
    `GET /eureka/svips/{svipAddress}`
    > curl -X 'GET'  http://localhost:8761/eureka/svips/eureka-client


