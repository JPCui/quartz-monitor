package cn.cjp.quartz.job;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 用于声明一个Job类
 * @author Jinpeng Cui
 *
 */
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface Job {

	public String group();
	
}
