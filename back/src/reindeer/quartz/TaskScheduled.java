package reindeer.quartz;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskScheduled {

	/**
	 * 触发时间
	 * 
	 * @return
	 */
	public String cron() default "0 0 23 * * ?";

}
