package com.xuwuji.website.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextUtil {
	private static ApplicationContext ctx = null;

	static {
		setCtx(new ClassPathXmlApplicationContext("applicationContext.xml"));
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}

	public static void setCtx(ApplicationContext ctx) {
		ContextUtil.ctx = ctx;
	}
}
