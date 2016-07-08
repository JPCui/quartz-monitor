package cn.cjp.quartz.dto;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;

public class JobOutput {
	
	private String name;
	
	private String group;
	
	private String description;
	
	private List<TriggerOutput> triggers = new ArrayList<>();
	
	private Class<? extends Job> targetObject;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TriggerOutput> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<TriggerOutput> triggers) {
		this.triggers = triggers;
	}

	public Class<? extends Job> getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(Class<? extends Job> targetObject) {
		this.targetObject = targetObject;
	}

}
