package cn.cjp.quartz.job.regist;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

import cn.cjp.quartz.job.AbstractJob;
import cn.cjp.quartz.job.Job;
import cn.cjp.quartz.job.SimpleJob;

/**
 * Job 注册 测试
 * 
 * @author Jinpeng Cui
 *
 */
@Configurable
@ComponentScan(value = { "cn.cjp.quartz.job" }, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Job.class) })
@Job
public class JobRegistTest extends AbstractJob {

	private static final Logger logger = Logger.getLogger(JobRegistTest.class);

	@Autowired
	ConfigurableListableBeanFactory beanFactory;

	@Override
	public void execute() {
		logger.info(this.getClass().getName());
	}

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JobRegistTest.class);
		System.out.println(Arrays.asList(ctx.getBeanDefinitionNames()));

		// 获取Job Bean
		AbstractJob job = ctx.getBean(SimpleJob.class);
		System.out.println(job);
		job = ctx.getBean(JobRegistTest.class);
		System.out.println(job);
		System.out.println(((JobRegistTest) job).beanFactory);

		System.out.println(ctx.getAutowireCapableBeanFactory().containsBean("beanFactory"));

		// 获取所有Job注解的类
		System.out.println(ctx.getBeansWithAnnotation(Job.class));

		ctx.close();
	}

}
