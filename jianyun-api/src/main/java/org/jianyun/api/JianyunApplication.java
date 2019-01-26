package org.jianyun.api;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.util.CollectionUtils;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages={"org.jianyun"},exclude = {JtaAutoConfiguration.class,JtaAutoConfiguration.class})
@MapperScan(basePackages={"org.jianyun.store.mapper"})
@EnableDiscoveryClient
@EnableElasticsearchRepositories(basePackages = "org.jianyun.store.respfores")
@ComponentScan("io.shardingsphere.transaction.aspect")
@EnableAutoConfiguration(exclude={DruidDataSourceAutoConfigure.class})
public class JianyunApplication implements CommandLineRunner {
	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JianyunApplication.class, args);
        FileSystem fileSystem = context.getBean(FileSystem.class);
        System.out.println(fileSystem);
        HbaseTemplate hbaseTemplate =  context.getBean(HbaseTemplate.class);
        System.out.println(hbaseTemplate);
	}
    @Autowired
    FsShell shell;
    @Override
    public void run(String... args) {
        for(FileStatus fileStatus: shell.lsr()){
            System.out.println("....>"+fileStatus.getPath());
        }
        if (CollectionUtils.isEmpty(shell.lsr())){
            System.out.println("....>xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
    }
}

