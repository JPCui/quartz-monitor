package cn.cjp.quartz.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.cjp.quartz.service.SpringJobService;

/**
 * 交由Spring管理的Job
 * 
 * @author Jinpeng Cui
 *
 */
@Job
public class SimpleSpringJob extends AbstractJob {

	private static final Logger logger = Logger.getLogger(SimpleSpringJob.class);

	@Autowired
	SpringJobService springJobService;

	public SimpleSpringJob() {
		logger.info(springJobService);
	}

	@Override
	public void execute() {
		springJobService.run();
	}

}
