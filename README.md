# jianyun

jianyun基于springboot构建，集成了mail，jasperreports报表，feign，redis, dubbo, kafka, hdfs, hbase, elaticjob, elasticSearch, mybaits常用的框架，方便后续使用，
快速开发，后面会持续集成一些常用的框架。
框架分了6个模块：
 <modules>
      <module>jianyun-extra</module>：对外提供的接口与服务
      <module>jianyun-store</module>：主数据等存储有关的模块
      <module>jianyun-utils</module>：配置有关的内容及常用的工具操作类
      <module>jianyun-api</module>：主数据模块接口服务
   	  <module>jianyun-logic</module>：主数据逻辑层
   	  <module>jianyun-producer</module>：对外的服务生产者，kafka
  </modules>
  
下面是基于spring.xml，hdfs与hbase的配置
1.在启动类上添加导入@ImportResource(locations = {"classpath:/hbase-spring.xml"})
2.hbase-spring.xml文件
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:hdp="http://www.springframework.org/schema/hadoop"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
    http://www.springframework.org/schema/hadoop http://www.springframework.org/schema/hadoop/spring-hadoop.xsd">

    <context:property-placeholder location="classpath:/hbase.properties"/>

    <hdp:configuration id="hadoopConfiguration">
        fs.defaultFS=hdfs://127.0.0.1:9000
    </hdp:configuration>
   <!-- <hdp:hbase-configuration configuration-ref="hadoopConfiguration" zk-quorum="${hbase.zk.host}" zk-port="${hbase.zk.port}" delete-connection="false"/>
-->
   <bean id="hbaseTemplate" class="org.springframework.data.hadoop.hbase.HbaseTemplate">
        <property name="configuration" ref="hadoopConfiguration"/>
    </bean>
</beans>
3.hbase.properties文件：
hbase.zk.host=localhost
hbase.zk.port=2181


sharding-sphere
#数据库建表sql(对ds0与ds1数据库分别创建t_cust0与t_cust1表)
CREATE TABLE `t_cust` ( 
  `cust_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `cust_name` varchar(10) DEFAULT NULL COMMENT '客户姓名',
  `cust_mobile` varchar(11) NOT NULL COMMENT '用户手机号',
  `gender` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`cust_id`),
  UNIQUE KEY `cust_mobile` (`cust_mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='客户信息表';
#DruidDataSourceAutoConfigure针对com.alibaba.druid.pool.DruidDataSource取消自动配置@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
#备注;sharding-sphere不能与分页插件pagehelper-spring-boot-starter及tk.mybatis兼容，测试时只能mark掉有关部分

