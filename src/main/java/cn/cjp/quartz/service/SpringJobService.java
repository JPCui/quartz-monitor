package cn.cjp.quartz.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 测试SpringJob的Service
 * @author Jinpeng Cui
 *
 */
@Service
public class SpringJobService {

	private static final Logger logger = Logger.getLogger(SpringJobService.class);

	public void run() {
		logger.info("service is running.");
	}

}
