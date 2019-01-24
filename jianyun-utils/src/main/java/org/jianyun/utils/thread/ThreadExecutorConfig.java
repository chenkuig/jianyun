/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jianyun.utils.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * <pre>
 * 名称: ThreadExecutorConfig
 * 描述: 线程池配置
 * </pre>
 * @author yto.net.cn
 * @since 1.0.0
 */
@Configuration
@EnableAsync
public class ThreadExecutorConfig {
     private static final Logger LOGGER = LoggerFactory.getLogger(ThreadExecutorConfig.class);

     @Value("${async.executor.thread.core_pool_size}")
     private int corePoolSize;
     @Value("${async.executor.thread.max_pool_size}")
     private int maxPoolSize;
     @Value("${async.executor.thread.queue_capacity}")
     private int queueCapacity;
     @Value("${async.executor.thread.name.prefix}")
     private String namePrefix;
     /**
      * 线程池初始化
      * @return executor
      */
     @Bean(name = "asyncServiceExecutor")
     public Executor asyncServiceExecutor() {
         LOGGER.info("start initialize asyncServiceExecutor");
         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
         //配置核心线程数
         executor.setCorePoolSize(corePoolSize);
         //配置最大线程数
         executor.setMaxPoolSize(maxPoolSize);
         //配置队列大小
         executor.setQueueCapacity(queueCapacity);
         //配置线程池中的线程的名称前缀
         executor.setThreadNamePrefix(namePrefix);
         // rejection-policy：当pool已经达到max size的时候，如何处理新任务
         // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
         //执行初始化
         executor.initialize();
         return executor;
     }

}
