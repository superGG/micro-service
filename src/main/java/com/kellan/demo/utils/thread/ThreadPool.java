package com.kellan.demo.utils.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池
 * @author Kellan_Song
 * @createTime 2019年4月16日
 */
public class ThreadPool {
	
	private static ThreadPoolTaskExecutor threadPoolTaskExecutor = null;
	
    public static ThreadPoolTaskExecutor getThreadPool() {
    	if (threadPoolTaskExecutor == null) {
    		synchronized (ThreadPool.class) {
				if (threadPoolTaskExecutor == null) {
					threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
					//最小线程数
					threadPoolTaskExecutor.setCorePoolSize(500);
					//最大线程数
					threadPoolTaskExecutor.setMaxPoolSize(2000);
					//空闲线程存活时间
					threadPoolTaskExecutor.setKeepAliveSeconds(200);
					//队列中最大线程
					threadPoolTaskExecutor.setQueueCapacity(2000);
					//初始化
					threadPoolTaskExecutor.initialize();
				}
			}
    	}
        return threadPoolTaskExecutor;
    }

}
