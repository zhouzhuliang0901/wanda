package com.wondersgroup.common.operator.task;

import java.sql.Timestamp;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.wondersgroup.common.operator.TaskOpFactory;

import reindeer.base.utils.AppContext;
import reindeer.quartz.Task;
import reindeer.quartz.TaskScheduled;
import wfc.service.log.Log;

//早晨2.30更新
@Component
@TaskScheduled(cron = "0 30 2 * * ?")
public class NightTask implements Task{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			TaskOpFactory factory = (TaskOpFactory) AppContext.getBean("taskOpFactory");
			Timestamp current = new Timestamp(System.currentTimeMillis());
			factory.execNightOp(current);
		} catch (Exception e) {
			Log.error(e);
		}
	}
}
