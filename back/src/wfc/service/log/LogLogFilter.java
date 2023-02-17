package wfc.service.log;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

@SuppressWarnings("rawtypes")
public class LogLogFilter extends Filter {

	private String excludeLogNameRegex = "tw.tool.helper.LogHelper|tw.service.db.DBFactory|wfc.service.log.Log";

	public FilterReply decide(Object eventObject) {
		LoggingEvent event = (LoggingEvent) eventObject;
		if (event.getLoggerName().matches(this.excludeLogNameRegex))
			return FilterReply.DENY;
		return FilterReply.ACCEPT;
	}

	public String getExcludeLogNameRegex() {
		return this.excludeLogNameRegex;
	}

	public void setExcludeLogNameRegex(String excludeLogNameRegex) {
		this.excludeLogNameRegex = excludeLogNameRegex;
	}

}
