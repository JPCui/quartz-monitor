package cn.cjp.quartz.job;

import org.apache.log4j.Logger;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.cjp.quartz.AbstractJob;

/**
 * same to {@link QuartzJobBean}
 * 
 * @author Jinpeng Cui
 * @see QuartzJobBean
 */
public class SimpleJob extends AbstractJob {

	private final static Logger logger = Logger.getLogger(SimpleJob.class);

	@Override
	public void execute() {
		logger.info("simple job running...");
	}

}
