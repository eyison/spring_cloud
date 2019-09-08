CREATE TABLE `PROPERTIES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `KEY` text,
  `VALUE` text,
  `APPLICATION` text,
  `PROFILE` text,
  `LABEL` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

BEGIN;
INSERT INTO `PROPERTIES` VALUES ('1', 'server.port', '8761', 'eureka-server', 'single', 'master'), ('2', 'spring.application.name', 'eureka-server', 'eureka-server', 'single', 'master'), ('3', 'spring.profiles.active', 'single', 'eureka-server', 'single', 'master'), ('4', 'eureka.instance.preferIpAddress', 'true', 'eureka-server', 'single', 'master'), ('5', 'eureka.client.registerWithEureka', 'false', 'eureka-server', 'single', 'master'), ('6', 'eureka.client.fetchRegistry', 'false', 'eureka-server', 'single', 'master'), ('7', 'eureka.client.serviceUrl.defaultZone', 'http://localhost:8761/eureka/', 'eureka-server', 'single', 'master'), ('8', 'eureka.server.evictionIntervalTimerInMs', '3000', 'eureka-server', 'single', 'master'), ('9', 'logging.level.com.netflix', 'debug', 'eureka-server', 'single', 'master'), ('10', 'spring.jackson.serialization.FAIL_ON_EMPTY_BEANS', 'false', 'eureka-server', 'single', 'master'), ('11', 'server.port', '8081', 'eureka-client', 'single', 'master'), ('12', 'spring.application.name', 'eureka-client', 'eureka-client', 'single', 'master'), ('13', 'spring.profiles.active', 'single', 'eureka-client', 'single', 'master'), ('14', 'eureka.instance.preferIpAddress', 'true', 'eureka-client', 'single', 'master'), ('15', 'eureka.client.fetchRegistry', 'true', 'eureka-client', 'single', 'master'), ('16', 'eureka.client.serviceUrl.defaultZone', 'http://localhost:8761/eureka/', 'eureka-client', 'single', 'master'), ('17', 'eureka.server.evictionIntervalTimerInMs', '3000', 'eureka-client', 'single', 'master'), ('18', 'eureka.instance.hostname', 'localhost', 'eureka-client', 'single', 'master'), ('19', 'eureka.instance.leaseExpirationDurationInSeconds', '10', 'eureka-client', 'single', 'master'), ('20', 'eureka.instance.leaseRenewalIntervalInSeconds', '3', 'eureka-client', 'single', 'master'), ('21', 'eureka.client.eurekaServerReadTimeoutSeconds', '10', 'eureka-client', 'single', 'master'), ('22', 'logging.level.com.netflix', 'debug', 'eureka-client', 'single', 'master'), ('23', 'spring.jackson.serialization.FAIL_ON_EMPTY_BEANS', 'false', 'eureka-client', 'single', 'master');
COMMIT;



