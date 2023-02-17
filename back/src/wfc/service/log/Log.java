package wfc.service.log;

import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.ecosystem.reindeer.log.LocationInfo;
import tw.tool.helper.DebugHelper;

public class Log {

	private static final String thisClassName = Log.class.getName();

	private static final Logger logger = LoggerFactory.getLogger(Log.class);
	
	public static void log(Object message, Priority priority, Class<?> clazz) {
		LocationInfo info = new LocationInfo(new Throwable(), clazz.getName());
		String extra = "[" + info.getClassName() + "." + info.getMethodName()
				+ "," + info.getLineNumber() + "] ";
		System.out.println(String.valueOf(extra) + message);
		logger.debug(String.valueOf(extra) + message);
	}

	@SuppressWarnings("unused")
	private static void log(Object message, Priority priority) {
		logger.debug(String.valueOf(message));
	}
	
	public static void debug(Object message) {
		logger.debug("{}", String.valueOf(getExtraMsg(new Throwable(), null))
				+ message);
	}

	public static void debug(Throwable message) {
		debug(DebugHelper.show(message));
	}

	public static void debug(boolean message) {
		debug(String.valueOf(message));
	}

	public static void debug(char message) {
		debug(String.valueOf(message));
	}

	public static void debug(double message) {
		debug(String.valueOf(message));
	}

	public static void debug(float message) {
		debug(String.valueOf(message));
	}

	public static void debug(short message) {
		debug(String.valueOf(message));
	}

	public static void debug(int message) {
		debug(String.valueOf(message));
	}

	public static void debug(long message) {
		debug(String.valueOf(message));
	}

	public static void info(Object message) {
		logger.info("{}", String.valueOf(getExtraMsg(new Throwable(), null))
				+ message);
	}

	public static void info(Throwable message) {
		info(DebugHelper.show(message));
	}

	public static void info(boolean message) {
		info(String.valueOf(message));
	}

	public static void info(char message) {
		info(String.valueOf(message));
	}

	public static void info(double message) {
		info(String.valueOf(message));
	}

	public static void info(float message) {
		info(String.valueOf(message));
	}

	public static void info(short message) {
		info(String.valueOf(message));
	}

	public static void info(int message) {
		info(String.valueOf(message));
	}

	public static void info(long message) {
		info(String.valueOf(message));
	}

	public static void warn(Object message) {
		logger.warn("{}", String.valueOf(getExtraMsg(new Throwable(), null))
				+ message);
	}

	public static void warn(Throwable message) {
		warn(DebugHelper.show(message));
	}

	public static void warn(boolean message) {
		warn(String.valueOf(message));
	}

	public static void warn(char message) {
		warn(String.valueOf(message));
	}

	public static void warn(double message) {
		warn(String.valueOf(message));
	}

	public static void warn(float message) {
		warn(String.valueOf(message));
	}

	public static void warn(short message) {
		warn(String.valueOf(message));
	}

	public static void warn(int message) {
		warn(String.valueOf(message));
	}

	public static void warn(long message) {
		warn(String.valueOf(message));
	}

	public static void error(Object message) {
		logger.error("{}", String.valueOf(getExtraMsg(new Throwable(), null))
				+ message);
	}

	public static void error(Throwable message) {
		error(DebugHelper.show(message));
	}

	public static void error(boolean message) {
		error(String.valueOf(message));
	}

	public static void error(char message) {
		error(String.valueOf(message));
	}

	public static void error(double message) {
		error(String.valueOf(message));
	}

	public static void error(float message) {
		error(String.valueOf(message));
	}

	public static void error(short message) {
		error(String.valueOf(message));
	}

	public static void error(int message) {
		error(String.valueOf(message));
	}

	public static void error(long message) {
		error(String.valueOf(message));
	}

	public static void trace(Object message) {
		logger.trace("{}", String.valueOf(getExtraMsg(new Throwable(), null))
				+ message);
	}

	public static void trace(Throwable message) {
		trace(DebugHelper.show(message));
	}

	public static void trace(boolean message) {
		trace(String.valueOf(message));
	}

	public static void trace(char message) {
		trace(String.valueOf(message));
	}

	public static void trace(double message) {
		trace(String.valueOf(message));
	}

	public static void trace(float message) {
		trace(String.valueOf(message));
	}

	public static void trace(short message) {
		trace(String.valueOf(message));
	}

	public static void trace(int message) {
		trace(String.valueOf(message));
	}

	public static void trace(long message) {
		trace(String.valueOf(message));
	}

	public static String getExtraMsg(Throwable throwable, String className) {
		LocationInfo info = getLogClassInfo(throwable, className);
		String extra = "[" + info.getClassName() + "." + info.getMethodName()
				+ "," + info.getLineNumber() + "] - ";
		return extra;
	}

	public static LocationInfo getLogClassInfo(Throwable throwable,
			String className) {
		if (throwable == null)
			throwable = new Throwable();
		if (className == null)
			className = thisClassName;
		LocationInfo info = new LocationInfo(throwable, className);
		return info;
	}
}