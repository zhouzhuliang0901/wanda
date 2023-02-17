package com.wondersgroup.statistics.util;

import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wondersgroup.statistics.web.SelmStatisticsController;
import com.wondersgroup.statistics.web.SelmStatisticsDayController;


public class ScheduleListener implements ServletContextListener {
    private static ScheduledExecutorService service4Check;
    
    
    //开启任务
    @Override
	public void contextInitialized(ServletContextEvent event) {		
 
    	CheckSchedule();
	}
    
	private  void CheckSchedule() {
		service4Check=Executors.newScheduledThreadPool(1);
		service4Check.scheduleWithFixedDelay(new CheckTask(), 1000, 6*1000,
                   TimeUnit.MILLISECONDS);//每3分钟执行一次
 
	}
    public class CheckTask extends TimerTask{
        @Override
	    public void run() {
        	System.out.println("开始执行---------------------------------------------");
        }
    }
    
    //关闭任务
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
