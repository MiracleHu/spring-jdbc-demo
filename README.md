# spring-jdbc-demo

##Java Spring Framework

* 1.基本的DAO接口层的配置
* 2.Spring如何通过JDBC来连接数据库
* 3.通过Bean的配置来实现接口的使用

##数据库初始化

```
CREATE TABLE `customer` (
  `CUST_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `AGE` int(10) unsigned NOT NULL,
  PRIMARY KEY (`CUST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```