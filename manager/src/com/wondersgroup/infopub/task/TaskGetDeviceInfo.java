package com.wondersgroup.infopub.task;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;
import com.wondersgroup.infopub.bean.InfopubOnoff;
import com.wondersgroup.infopub.bean.SetInfopubDeviceOnOff;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDaoExt;
import com.wondersgroup.infopub.dao.InfopubOnoffDaoExt;
import com.wondersgroup.infopub.util.InfoputConst;

import coral.base.app.AppContext;
import coral.base.quartz.Task;
import coral.base.quartz.TaskScheduled;

//@Component
//@TaskScheduled(cron = "0 0/5 * * * ?")
public class TaskGetDeviceInfo implements Task {
	private InfopubDeviceInfoDaoExt infopubDeviceInfoDaoExt;
	private InfopubOnoffDaoExt infopubOnoffDaoExt;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		infopubDeviceInfoDaoExt = (InfopubDeviceInfoDaoExt) AppContext
				.getBean("infopubDeviceInfoDaoExt");
		infopubOnoffDaoExt = (InfopubOnoffDaoExt) AppContext
				.getBean("infopubOnoffDaoExt");
		if (infopubDeviceInfoDaoExt != null && infopubOnoffDaoExt != null) {
			List<InfopubDeviceInfoExt> list = infopubDeviceInfoDaoExt.queryWithDeviceType(null,
					null);
			InfoputConst.deviceInfos = new ArrayList<SetInfopubDeviceOnOff>();
			for (InfopubDeviceInfoExt infopubDeviceInfoExt : list) {
				InfopubOnoff infoOnoff = infopubOnoffDaoExt
						.getTodayOnoff(infopubDeviceInfoExt.getStDeviceId());
				if (infoOnoff == null) {
					infoOnoff = infopubOnoffDaoExt
							.getWeekOnoff(infopubDeviceInfoExt.getStDeviceId());
				}
				if (infoOnoff != null) {
					SetInfopubDeviceOnOff setInfopubDeviceOnOff = new SetInfopubDeviceOnOff();
					setInfopubDeviceOnOff
							.setInfopubDeviceInfoExt(infopubDeviceInfoExt);
					setInfopubDeviceOnOff.setInfopubOnoff(infoOnoff);
					InfoputConst.deviceInfos.add(setInfopubDeviceOnOff);
				}
			}
		}
	}
}
