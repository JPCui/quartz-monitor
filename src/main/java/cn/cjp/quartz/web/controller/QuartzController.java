package cn.cjp.quartz.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.cjp.quartz.dto.JobOutput;
import cn.cjp.quartz.exception.QuartzException;
import cn.cjp.quartz.service.JobService;

@Controller
@RequestMapping("/quartz")
public class QuartzController implements InitializingBean {

	@Autowired
	JobService jobService;
	
	@Autowired
	Scheduler scheduler;

	private List<String> registedJobs = null;
	
	@RequestMapping("/start")
	public ModelAndView start() throws SchedulerException {
		ModelAndView mv = this.findAll();
		if(!scheduler.isStarted()) {
			scheduler.start();
		}
		return mv;
	}

	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/list");

		List<JobOutput> list = jobService.getAllJobDetail();
		mv.addObject("list", list);

		mv.addObject("registedJobs", registedJobs);
		return mv;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(String jobGroup, String jobName, String triggerGroup, String triggerName)
			throws ClassNotFoundException, IOException, SchedulerException, QuartzException {
		ModelAndView mv = this.findAll();
		Map<String, Object> data = jobService.getJob(jobGroup, jobName, triggerGroup, triggerName);
		mv.addObject("data", data);
		return mv;
	}

	/**
	 * 
	 * @param jobGroup
	 * @param jobName
	 * @param jobDescription
	 * @param jobClass
	 * @param triggerGroup
	 * @param triggerName
	 * @param triggerDescription
	 * @param cronExpression
	 * @param startTime
	 * @return
	 * @throws SchedulerException
	 * @throws QuartzException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("/add")
	public ModelAndView add(String jobGroup, String jobName, String jobDescription, String jobClass,
			String triggerGroup, String triggerName, String triggerDescription, String cronExpression, String startTime)
			throws SchedulerException, ClassNotFoundException, QuartzException, IOException {
		// 验证非空
		if (StringUtils.isEmpty(jobGroup) || StringUtils.isEmpty(jobClass) || StringUtils.isEmpty(cronExpression)) {
			return this.findAll().addObject("errMsg", "确保必要参数(*)不为空");
		}

		jobService.add(jobGroup, jobName, jobDescription, jobClass, triggerGroup, triggerName, triggerDescription,
				cronExpression, startTime);
		return new ModelAndView("redirect:findAll");
	}

	@RequestMapping("/pauseTrigger")
	public ModelAndView pauseTrigger(String triggerName, String triggerGroup) throws SchedulerException {
		jobService.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
		return new ModelAndView("redirect:findAll");
	}

	@RequestMapping("/resumeTrigger")
	public ModelAndView resumeTrigger(String triggerName, String triggerGroup) throws SchedulerException {
		jobService.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
		return new ModelAndView("redirect:findAll");
	}

	@RequestMapping("/unscheduleJob")
	public ModelAndView unscheduleJob(String triggerName, String triggerGroup) throws SchedulerException {
		jobService.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroup));
		return new ModelAndView("redirect:findAll");
	}

	@RequestMapping("/deleteJob")
	public ModelAndView deleteJob(String jobName, String jobGroup) throws SchedulerException {
		jobService.deleteJob(JobKey.jobKey(jobName, jobGroup));
		return new ModelAndView("redirect:findAll");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		registedJobs = jobService.registedJobs();
	}

}
