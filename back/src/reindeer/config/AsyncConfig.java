package reindeer.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import tw.tool.helper.LogHelper;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

	@Bean
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
		// 设置核心线程数
		threadPool.setCorePoolSize(10);
		// 设置最大线程数
		threadPool.setMaxPoolSize(100);
		// 线程池所使用的缓冲队列
		threadPool.setQueueCapacity(10);
		// 等待任务在关机时完成--表明等待所有线程执行完
		threadPool.setWaitForTasksToCompleteOnShutdown(true);
		// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
		threadPool.setAwaitTerminationSeconds(60);
		// 线程名称前缀
		threadPool.setThreadNamePrefix("CollectTask-");
		// 初始化线程
		threadPool.initialize();
		return threadPool;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new CollectExceptionHandler();
	}

	/**
	 * 自定义异常处理类
	 * 
	 */
	class CollectExceptionHandler implements AsyncUncaughtExceptionHandler {

		@Override
		public void handleUncaughtException(Throwable arg0, Method arg1,
				Object... arg2) {
			LogHelper.error(arg0);
		}

	}
}
