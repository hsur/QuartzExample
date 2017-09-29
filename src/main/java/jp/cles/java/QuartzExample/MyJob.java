package jp.cles.java.QuartzExample;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJob implements Job{
	private static final Logger log = LoggerFactory.getLogger(MyJob.class);
	public MyJob() throws Exception {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		log.info(jobKey + " は " + new Date() + " に起動されました");
	}
}
