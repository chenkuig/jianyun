package org.jianyun.store.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: chenkui
 * @Date: 2019/1/24 13:31
 * @Description:
 */
@Configurable
@Component
@Slf4j
public class HdfsConfig {
    private HdfsBasicProperties properties;
    @Resource
    private Environment environment;
    HdfsConfig(HdfsBasicProperties properties){
        this.properties = properties;
    }
    @Bean(name="configuration")
    public Configuration configuration() {
        Configuration configuration = HBaseConfiguration.create();
        Map<String, String> config = properties.getConfig();
        Set<String> keySet = config.keySet();
        for (String key : keySet) {
            configuration.set(key, config.get(key));
        }
        return configuration;
    }
}
