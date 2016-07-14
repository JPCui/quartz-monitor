package cn.cjp.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 
 * @author Jinpeng Cui
 *
 */
public class SpringJobBeanFactory extends SpringBeanJobFactory implements ApplicationContextAware {

	private static final Logger logger = Logger.getLogger(SpringJobBeanFactory.class);

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		logger.info(jobInstance);
		// 把Job交给Spring来管理，这样Job就能使用由Spring产生的Bean了
		try {
			applicationContext.getAutowireCapableBeanFactory().autowireBean(jobInstance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(jobInstance);
		return jobInstance;
	}

}
