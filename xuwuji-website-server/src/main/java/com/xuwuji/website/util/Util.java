package com.xuwuji.website.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Util {

	public static DateTimeFormatter getDateFormatter() {
		return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
	}

	public static String getDateTime(DateTime time) {
		String result = time.toString(getDateFormatter());
		return result;
	}

}
