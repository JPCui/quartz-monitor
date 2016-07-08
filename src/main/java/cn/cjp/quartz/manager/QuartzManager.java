package cn.cjp.quartz.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import cn.cjp.quartz.dto.JobOutput;
import cn.cjp.quartz.dto.TriggerOutput;

/**
 * Quartz管理
 * 
 * @author Jinpeng Cui
 *
 */
public class QuartzManager {
	
	private final static Logger logger = Logger.getLogger(QuartzManager.class);

	public final static String DATE_PATTERN = "yyyy-dd-MM hh:mm:ss";

	private Scheduler scheduler;
	
	public QuartzManager() {
		logger.info(getClass() + " init.");
	}

	/**
	 * 修改trigger,修改qrtz_triggers表数据
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @param trigger
	 * @return
	 */
	public boolean updateTrigger(String triggerName, String triggerGroup, Trigger trigger) {
		boolean flag = false;
		try {
			scheduler.rescheduleJob(new TriggerKey(triggerName, triggerGroup), trigger);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void scheduleJob(String jobName, String jobGroup, Trigger trigger) throws SchedulerException {
		JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
		scheduleJob(jobDetail, trigger);
	}

	public void scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
		HashSet<Trigger> triggers = new HashSet<>();
		triggers.add(trigger);
		scheduler.scheduleJob(jobDetail, triggers, true);
	}

	/**
	 * 修改Cron Trigger，涉及表qrtz_cron_triggers，更改执行计划等
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @param cronTrigger
	 * @return
	 */
	public boolean updateCronTrigger(String triggerName, String triggerGroup, CronTrigger cronTrigger) {
		boolean flag = false;
		try {
			scheduler.rescheduleJob(new TriggerKey(triggerName, triggerGroup), cronTrigger);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 添加或修改jobDetail，但并不运行
	 * 
	 * @param jobDetail
	 * @return
	 */
	public boolean updateJobDetail(JobDetail jobDetail) {
		boolean flag = false;
		try {
			scheduler.addJob(jobDetail, true);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 删除Job,直接从数据库中删除，关联表数据也删除
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @param jobName
	 * @param jobGroup
	 */
	public boolean deleteJob(String triggerName, String triggerGroup, String jobName, String jobGroup) {
		boolean flag = false;
		try {
			scheduler.pauseTrigger(new TriggerKey(triggerName, triggerGroup));// 停止触发器
			scheduler.unscheduleJob(new TriggerKey(triggerName, triggerGroup));// 移除触发器
			scheduler.deleteJob(new JobKey(jobName, jobGroup));// 删除任务
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean deleteJob(String jobName, String jobGroup) throws SchedulerException {
		scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
		return scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
	}

	/**
	 * 暂停某个触发器
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @return
	 */
	public boolean pauseTrigger(String triggerName, String triggerGroup) {
		boolean flag = false;
		try {
			// 暂停触发器
			scheduler.pauseTrigger(new TriggerKey(triggerName, triggerGroup));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 恢复已暂停的触发器
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @return
	 */
	public boolean resumeTrigger(String triggerName, String triggerGroup) {
		boolean flag = false;
		try {
			// 暂停触发器
			scheduler.resumeTrigger(new TriggerKey(triggerName, triggerGroup));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 立即执行JOB
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	public boolean runAJobNow(String jobName, String jobGroup) {
		boolean flag = false;
		try {
			// 立即执行JOB
			scheduler.triggerJob(new JobKey(jobName, jobGroup));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 暂停所有触发器
	 * 
	 * @return
	 */
	public boolean pauseAllTrigger() {
		boolean flag = false;
		try {
			scheduler.standby();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 启动所有触发器
	 * 
	 * @return
	 */
	public boolean startAllTrigger() {
		boolean flag = false;
		try {
			if (scheduler.isInStandbyMode()) {
				scheduler.start();
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 停止所有触发器,停止后无法启动，无法调用start方法
	 * 
	 * @return
	 */
	public boolean shutdownTrigger() {
		boolean flag = false;
		try {
			scheduler.shutdown();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 返回所有jobDetail
	 * 
	 * @return
	 */
	public List<JobOutput> getAllJobDetail() {
		List<JobOutput> list = new ArrayList<JobOutput>();
		try {
			Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
			for (JobKey jobKey : jobKeys) {
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);

				JobOutput jobOutput = new JobOutput();
				// job组
				jobOutput.setGroup(jobKey.getGroup());
				// job名称
				jobOutput.setName(jobKey.getName());
				// job业务描述
				jobOutput.setDescription(jobDetail.getDescription());
				// 以下返回该任务的trigger信息
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				// 执行计划表达式
				String cronExpression = null;
				// // 触发器状态编码
				// TriggerState triggerState = null;
				// // 触发器描述
				// String triggerDesc = null;
				// 触发器名称
				String triggerName = null;
				// 触发器组名
				String triggerGroup = null;
				for (Trigger trigger : triggers) {
					cronExpression = ((CronTrigger) trigger).getCronExpression();
					triggerName = trigger.getKey().getName();
					triggerGroup = trigger.getKey().getGroup();

					TriggerOutput triggerOutput = new TriggerOutput();
					triggerOutput.setCronExpression(cronExpression);
					triggerOutput.setStartTime(trigger.getStartTime());
					triggerOutput.setPreviousFireTime(trigger.getPreviousFireTime());
					triggerOutput.setName(triggerName);
					triggerOutput.setGroup(triggerGroup);
					triggerOutput.setDescription(trigger.getDescription());
					triggerOutput.setState(parseTriggerState(scheduler.getTriggerState(trigger.getKey())));
					jobOutput.getTriggers().add(triggerOutput);
				}
				// DELETED -1 COMPLETE 2 PAUSED 1 PAUSED_BLOCKED 1 ERROR 3
				// BLOCKED 4 否则 返回0
				/*
				 * None：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除
				 * COMPLETE：触发器完成，但是任务可能还正在执行中 PAUSED：暂停状态 BLOCKED：线程阻塞状态
				 * NORMAL:正常状态 BLOCKED：线程阻塞状态
				 */
				HashMap<String, String> targerMap = new HashMap<String, String>();
				for (Object e : jobDetail.getJobDataMap().entrySet()) {
					Map.Entry<?, ?> entry = (Entry<?, ?>) e;
					targerMap.put(entry.getKey().toString(), entry.getValue().toString());
				}
				// 任务实现类
				jobOutput.setTargetObject(jobDetail.getJobClass());
				list.add(jobOutput);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String parseTriggerState(TriggerState triggerState) {
		String triggerDesc = null;
		switch (triggerState) {
		case NORMAL:
			triggerDesc = "正常";
			break;
		case PAUSED:
			triggerDesc = "暂停";
			break;
		case COMPLETE:
			triggerDesc = "已完成";
			break;
		case ERROR:
			triggerDesc = "异常";
			break;
		case BLOCKED:
			triggerDesc = "阻塞";
			break;
		case NONE:
			triggerDesc = "已删除";
			break;
		default:
			break;
		}
		return triggerDesc;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
