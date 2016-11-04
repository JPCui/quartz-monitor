package cn.cjp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Cui 资源文件工具类（properties）
 */
public class PropertiesUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	private Map<String, String> params = new HashMap<>();

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public PropertiesUtil(String resource) throws IOException {
		Properties prop = null;
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		if (stream == null) {
			stream = Thread.class.getResourceAsStream(resource);
		}
		if (stream == null) {
			stream = Thread.class.getClassLoader().getResourceAsStream(stripped);
		}
		if (stream == null) {
			throw new IOException(resource + " not found");
		}

		prop = new Properties();
		// 转码
		prop.load(new InputStreamReader(stream, "UTF-8"));

		for (Object key : prop.keySet()) {
			params.put(key.toString(), prop.getProperty(key.toString()));
		}

	}

	public String getValue(String key) {
		return params.get(key);
	}

	public int getInt(String key, int defaultValue) {
		int value = defaultValue;
		try {
			String s = params.get(key);
			if (s != null) {
				value = Integer.parseInt(params.get(key));
			}
		} catch (NumberFormatException e) {
		}
		return value;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public static void main(String[] args) throws IOException {
		PropertiesUtil propertiesUtil;
		try {
			propertiesUtil = new PropertiesUtil("./wx.properties");
			logger.info("\n" + propertiesUtil.params);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}