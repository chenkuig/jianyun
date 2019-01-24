package org.jianyun.store.hdfs;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "hadhoop")
@Data
@EnableAutoConfiguration
@Component
public class HdfsBasicProperties {
	 private Map<String, String> config;
}
