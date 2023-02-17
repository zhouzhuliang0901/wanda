package wfc.service.log;

import org.apache.log4j.Priority;
import org.apache.log4j.spi.LocationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tw.tool.helper.LogHelper;

public class Log {

	private final static Logger logger = LoggerFactory
			.getLogger(LogHelper.class);

	private Log() {
	}

	@SuppressWarnings("deprecation")
	public static void log(Object message, Priority priority, Class<?> clazz) {
		LocationInfo info = new LocationInfo(new Throwable(), clazz.getName());
		String extra = "[" + info.getClassName() + "." + info.getMethodName()
				+ "," + info.getLineNumber() + "] ";
		if (priority == Priority.DEBUG) {
			logger.debug("{}", extra + message);
		} else if (priority == Priority.ERROR) {
			logger.error("{}", extra + message);
		} else if (priority == Priority.FATAL) {
			logger.trace("{}", extra + message);
		} else if (priority == Priority.INFO) {
			logger.info("{}", extra + message);
		} else if (priority == Priority.WARN) {
			logger.warn("{}", extra + message);
		}
	}

	private static void log(Object message, Priority priority) {
		log(message, priority, Log.class);
	}

	@SuppressWarnings("deprecation")
	public static void debug(Object message) {
		log(message, Priority.DEBUG);
	}

	public static void debug(Throwable message) {
		debug(Debug.show(message));
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

	@SuppressWarnings("deprecation")
	public static void info(Object message) {
		log(message, Priority.INFO);
	}

	public static void info(Throwable message) {
		info(Debug.show(message));
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

	@SuppressWarnings("deprecation")
	public static void warn(Object message) {
		log(message, Priority.WARN);
	}

	public static void warn(Throwable message) {
		warn(Debug.show(message));
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

	@SuppressWarnings("deprecation")
	public static void error(Object message) {
		log(message, Priority.ERROR);
	}

	public static void error(Throwable message) {
		error(Debug.show(message));
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

	@SuppressWarnings("deprecation")
	public static void fatal(Object message) {
		log(message, Priority.FATAL);
	}

	public static void fatal(Throwable message) {
		fatal(Debug.show(message));
	}

	public static void fatal(boolean message) {
		fatal(String.valueOf(message));
	}

	public static void fatal(char message) {
		fatal(String.valueOf(message));
	}

	public static void fatal(double message) {
		fatal(String.valueOf(message));
	}

	public static void fatal(float message) {
		fatal(String.valueOf(message));
	}

	public static void fatal(short message) {
		fatal(String.valueOf(message));
	}

	public static void fatal(int message) {
		fatal(String.valueOf(message));
	}

	public static void fatal(long message) {
		fatal(String.valueOf(message));
	}

}