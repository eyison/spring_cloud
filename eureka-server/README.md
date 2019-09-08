# eureka-server

#### 开启https生成证书
 
生成server端证书
 > keytool -genkeypair -alias server -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore server.p12 -validity 3650

    密钥库口令:torepasswd 
 
导出server端证书
 > keytool -export -alias server -file server.crt --keystore server.p12    
   
生成client端证书
  > keytool -genkeypair -alias client -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore client.p12 -validity 3650
    
    密钥库口令:torepasswd  
  
导出client端证书
  > keytool -export -alias client -file client.crt --keystore client.p12
  
将server.crt导入client.p12密钥库中中，使Client端信任Server端的证书
   > keytool -import -alias server -file server.crt --keystore client.p12
    
将client.crt导入server.p12密钥库中中，使Server端信任的证书  
  > keytool -import -alias client -file client.crt --keystore server.p12
  
  
    