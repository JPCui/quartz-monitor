package cn.cjp.weather.web.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.cjp.weather.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	WeatherService weatherService;

	@RequestMapping({ "", "/index" })
	@ResponseBody
	public ModelAndView index(@RequestParam(required = false, defaultValue = "北京") String city) throws IOException {
		Object result = weatherService.index(city);
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", result);
		if (result instanceof Collection) {
			mv.addObject("code", 1);
		} else if (result == null) {
			mv.addObject("code", 0);
		} else {
			mv.addObject("code", 2);
		}
		return mv;
	}

}