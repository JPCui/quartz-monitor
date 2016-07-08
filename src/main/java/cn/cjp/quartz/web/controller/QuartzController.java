package cn.cjp.quartz.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.cjp.quartz.dto.JobOutput;
import cn.cjp.quartz.manager.QuartzManager;

@Controller
@RequestMapping("/quartz")
public class QuartzController {
	
	@Autowired
	QuartzManager quartzManager;
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		List<JobOutput> list = quartzManager.getAllJobDetail();
		
		ModelAndView mv = new ModelAndView("/list");
		mv.addObject("list", list);
		return mv;
	}

}
