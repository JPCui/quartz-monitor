package cn.cjp.weather.service;

import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.cjp.weather.api.WeatherApi;
import cn.cjp.weather.util.CityCodes;

@Component
public class WeatherService {

	private static final Logger LOGGER = Logger.getLogger(WeatherService.class);

	/**
	 * 
	 * @param city
	 * @return 当只匹配到一个城市，返回天气信息 String <br>
	 *         匹配到多个城市，返回所有城市 Collection <br>
	 *         否则，返回 null
	 * @throws IOException
	 */
	public Object index(String city) throws IOException {
		Collection<String> citys = CityCodes.searchCitys(city);
		LOGGER.info("match citys: " + citys);
		if (citys.size() > 0 && citys.contains(city)) {
			// return weather
			return WeatherApi.cityinfo(CityCodes.get(city));
		} else if (citys.size() == 0) {
			// return wrong code
			return null;
		} else {
			// return citys
			return citys;
		}
	}
	

}
