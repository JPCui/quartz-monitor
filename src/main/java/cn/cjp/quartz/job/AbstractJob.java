package cn.cjp.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstractJob implements Job {

	private final static Logger logger = Logger.getLogger(AbstractJob.class);

	public abstract void execute();

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.debug(context);
		this.execute();
	}

}
