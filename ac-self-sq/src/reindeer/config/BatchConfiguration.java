package reindeer.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

	/*
	 * 作业仓库,把job运行过程中产生的数据持久化到数据库 HikariDataSource 数据源,多个数据源的时候指定按名称注入
	 */
	@Bean
	public JobRepository jobRepository(
			@Qualifier("dataSource") DataSource dataSource,
			PlatformTransactionManager dataSourceTransactionManager)
			throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean
				.setTransactionManager(dataSourceTransactionManager);
		jobRepositoryFactoryBean.setDatabaseType(DatabaseType.fromMetaData(
				dataSource).name());
		return jobRepositoryFactoryBean.getObject();
	}

	/**
	 * JobBuilderFactory
	 * 
	 * @param jobRepository
	 *            JobRepository
	 * @return JobBuilderFactory
	 */
	@Bean
	JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
		return new JobBuilderFactory(jobRepository);
	}

	/**
	 * StepBuilderFactory
	 * 
	 * @param jobRepository
	 *            jobRepository
	 * @param dataSourceTransactionManager
	 *            dataSourceTransactionManager
	 * @return stepBuilderFactory
	 */
	@Bean
	StepBuilderFactory stepBuilderFactory(JobRepository jobRepository,
			DataSourceTransactionManager dataSourceTransactionManager) {
		return new StepBuilderFactory(jobRepository,
				dataSourceTransactionManager);
	}

	/**
	 * 作业调度器
	 */
	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository)
			throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		return jobLauncher;
	}

	/**
	 * 作业注册器
	 */
	@Bean
	public MapJobRegistry mapJobRegistry() {
		return new MapJobRegistry();
	}

	/***
	 * JobRegistryBeanPostProcessor
	 * 
	 * @param mapJobRegistry
	 *            MapJobRegistry
	 * @return JobRegistryBeanPostProcessor
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(
			MapJobRegistry mapJobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(mapJobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	/**
	 * 作业线程池
	 */
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(500);
		threadPoolTaskExecutor.setKeepAliveSeconds(30000);
		threadPoolTaskExecutor.setMaxPoolSize(1000);
		threadPoolTaskExecutor.setQueueCapacity(1024);
		return threadPoolTaskExecutor;
	}

}