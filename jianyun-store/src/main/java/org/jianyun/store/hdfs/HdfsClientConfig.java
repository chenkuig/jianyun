package org.jianyun.store.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configurable
@Component
@Slf4j
public class HdfsClientConfig {
	@Resource
	private Environment environment;

	@Autowired
	private Configuration configuration;
    /**
     * 获取HbaseTemplate
     * @return HbaseTemplate
     */
    @Bean(name="hbaseTemplate")
    public HbaseTemplate getHbaseTemplate(){
    	 HbaseTemplate hbaseTemplate = new HbaseTemplate();
         hbaseTemplate.setConfiguration(configuration);
         hbaseTemplate.setAutoFlush(true);
         return hbaseTemplate;
    }

    @Bean(name="fileSystem")
    public FileSystem getFileSystem(){
        try {
            FileSystem fileSystem = FileSystem.newInstance(configuration);
            return fileSystem;
        }catch (Exception e){
            log.error(this.getClass().getName(), e);
        }
        return null;
    }
}
