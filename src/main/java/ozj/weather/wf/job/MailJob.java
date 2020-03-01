package ozj.weather.wf.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import ozj.weather.wf.core.MailThread;
import ozj.weather.wf.domain.Task;
import ozj.weather.wf.domain.WeatherResponse;
import ozj.weather.wf.service.MailService;
import ozj.weather.wf.service.TaskService;
import ozj.weather.wf.service.WeatherDataService;

import java.util.Date;

/**
 * 复杂任务
 * @author ozj
 * @date 2020-02-27
 */
public class MailJob extends QuartzJobBean {

    @Autowired
    private TaskService taskService;

    @Autowired
    private MailService mailService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String triggerName = jobExecutionContext.getTrigger().getJobKey().getName();
        String triggerGroup = jobExecutionContext.getTrigger().getJobKey().getGroup();
        Task task = taskService.findByTriggerNameAndGroup(triggerName,triggerGroup);
        WeatherResponse weatherResponse = weatherDataService.getDataByCityName(task.getCityName());
        System.out.println(new Date() + "任务开始------------------------------------");
        MailThread mailThread = new MailThread(task.getOwn().getEmail(),"天气预报",weatherResponse.toString());
        mailThread.run();
        System.out.println(new Date() + "任务结束------------------------------------");
    }
}
