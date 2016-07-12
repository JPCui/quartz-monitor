package cn.cjp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Cui
 *
 */
public class DateUtil {

	public static final String DEFAULT_DATEFORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @param field
	 *            {@link Calendar}
	 * @param amount
	 * @return
	 */
	public static Date add(int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date parse(String source, String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(source);
	}

}
