package com.revature.utils;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class LogUtil {
	public static void logException(Exception e, @SuppressWarnings("rawtypes") Class c) {
		Logger log = Logger.getLogger(c);
		log.error(e.getClass()+": "+e.getMessage());
		for(StackTraceElement s : e.getStackTrace()) {
			log.warn(s.getLineNumber()+": "+s.getClassName());
		}
	}
	public static void rollback(Exception e, Connection conn, @SuppressWarnings("rawtypes") Class c) {
		logException(e, c);
		try {
			conn.rollback();
		} catch (Exception e1) {
			logException(e1, c);
		}
	}
}
