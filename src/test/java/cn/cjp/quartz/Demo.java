package cn.cjp.quartz;

import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.cjp.quartz.dto.JobOutput;
import cn.cjp.quartz.job.SimpleJob;
import cn.cjp.quartz.manager.QuartzManager;

public class Demo {
	
	static FileSystemXmlApplicationContext ctx;

	static QuartzManager quartzManager;

	static {
		ctx = new FileSystemXmlApplicationContext("classpath:./spring-quartz.xml");
		quartzManager = ctx.getBean(QuartzManager.class);
	}

	public static void main(String[] args) throws InterruptedException, SchedulerException {

		Thread.sleep(10_000L);
		{

			JobDetail job1 = newJobBuilder().build();

			quartzManager.updateJobDetail(job1);

			List<JobOutput> list = quartzManager.getAllJobDetail();
			System.out.println(list);
		}

		Thread.sleep(10_000L);
		ctx.close();
	}

	public static JobBuilder newJobBuilder() {
		JobBuilder jobBuilder = JobBuilder.newJob(SimpleJob.class);
		jobBuilder.storeDurably();
		jobBuilder.withIdentity("name", "group");
		return jobBuilder;
	}

	public static ScheduleBuilder<CronTrigger> newSchduleBuilder() {
		ScheduleBuilder<CronTrigger> scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
		return scheduleBuilder;
	}

	public static TriggerBuilder<CronTrigger> newTriggerBuilder() {
		TriggerBuilder<CronTrigger> builder = TriggerBuilder.newTrigger().withSchedule(newSchduleBuilder());
		builder.withIdentity(TriggerKey.triggerKey("triggerName", "triggerGroup"));
		return builder;
	}

}
