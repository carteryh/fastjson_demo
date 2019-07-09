package com.fastjson.config.base;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Environment env;

	/** Set the ThreadPoolExecutor's core pool size. */
	private int corePoolSize = 10;
	/** Set the ThreadPoolExecutor's maximum pool size. */
	private int maxPoolSize = 25;
	/** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
	private int queueCapacity = 50;

	private int keepAliveSeconds = 600;

	@Bean
	public Executor mySimpleAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MySimpleExecutor-");
		executor.initialize();
		return executor;
	}

	@Bean
	public Executor myAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MyExecutor-");

		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

	@Bean
	public TaskExecutor taskExecutor() {
		corePoolSize = env.getProperty("threadPoolTaskExecutor.corePoolSize", Integer.class, corePoolSize);
		maxPoolSize = env.getProperty("threadPoolTaskExecutor.maxPoolSize", Integer.class, maxPoolSize);
		queueCapacity = env.getProperty("threadPoolTaskExecutor.queueCapacity", Integer.class, queueCapacity);
		keepAliveSeconds = env.getProperty("threadPoolTaskExecutor.keepAliveSeconds", Integer.class, keepAliveSeconds);

		logger.info("=========初始化AsyncExecutor=========");
		logger.info("corePoolSize:{}", corePoolSize);
		logger.info("maxPoolSize:{}", maxPoolSize);
		logger.info("queueCapacity:{}", queueCapacity);
		logger.info("keepAliveSeconds:{}", keepAliveSeconds);
		
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("TaskExecutor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
}