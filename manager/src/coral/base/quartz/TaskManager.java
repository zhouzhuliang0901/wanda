package coral.base.quartz;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = false)
public class TaskManager {

	@Autowired(required = false)
	private List<Task> taskList;

	@Resource(name = "quartzScheduler")
	private Scheduler scheduler;

	@PostConstruct
	public void init() {
		try {
			if (taskList != null) {
				for (int i = 0; i < taskList.size(); i++) {
					Task task = taskList.get(i);
					String nameSuffix = task.getClass().getName();
					TriggerKey triggerKey = new TriggerKey("trigger_"
							+ nameSuffix, "DEFAULT");
					Trigger exitTrigger = scheduler.getTrigger(triggerKey);
					if (exitTrigger != null)
						continue;
					TaskScheduled scheduledCron = task.getClass()
							.getAnnotation(TaskScheduled.class);

					JobKey jobKey = new JobKey("job_" + nameSuffix, "DEFAULT");
					JobDetail jobDetail = JobBuilder.newJob(task.getClass())
							.requestRecovery(true).withIdentity(jobKey).build();
					CronTrigger cronTrigger = TriggerBuilder
							.newTrigger()
							.withIdentity(triggerKey)
							.forJob(jobKey)
							.withSchedule(
									CronScheduleBuilder
											.cronSchedule(scheduledCron.cron()))
							.build();
					scheduler.scheduleJob(jobDetail, cronTrigger);
				}
				scheduler.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
