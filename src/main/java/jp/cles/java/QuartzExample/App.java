package jp.cles.java.QuartzExample;

import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App
{
    public static void main( String[] args ) throws Exception
    {
    	App app = new App();
		app.run();
    }

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(App.class);

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail job = newJob(MyJob.class)
				.withIdentity("MyJob1", "Group1")
				.build();

		CronTrigger tr = newTrigger()
				.withIdentity("Trigger1", "Group1")
				.withSchedule(cronSchedule("0/5 * * * * ?"))
				.build();
		Date ft = sched.scheduleJob(job, tr);

		log.info(job.getKey() + " は " + ft + " に実行するようにセットアップされました。\n右の cron 定義に従って繰り返し実行されます: "
				+ tr.getCronExpression());

		sched.start();
	}

}
