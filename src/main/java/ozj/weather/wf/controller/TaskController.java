package ozj.weather.wf.controller;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ozj.weather.wf.core.Response;
import ozj.weather.wf.domain.Task;
import ozj.weather.wf.domain.User;
import ozj.weather.wf.job.MailJob;
import ozj.weather.wf.service.QuartzService;
import ozj.weather.wf.service.TaskService;
import ozj.weather.wf.service.TokenService;
import ozj.weather.wf.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试控制类
 *
 * @author ozj
 * @Date 2020-02-27
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TokenService tokenService;

    private static final String PAUSE = "pause";

    private static final String REMOVE = "remove";

    private static final String RESUME = "resume";

    /**
     * 添加任务并执行该任务
     * @param triggerName
     * @param triggerGroup
     * @param cron
     * @param request
     * @return
     * @throws SchedulerException
     */
    @PostMapping("/add")
    public Object addTask(@RequestParam(value = "triggerName") String triggerName, @RequestParam(value = "triggerGroup") String triggerGroup,
                          @RequestParam(value = "cron") String cron, @RequestParam(value = "cityId",defaultValue = "0") String cityId,
                          @RequestParam(value = "cityName") String cityName, HttpServletRequest request) throws SchedulerException {
        User own = tokenService.getUserFromRequest(request);
        Task task = taskService.findByTriggerNameAndGroup(triggerName,triggerGroup);
        if (task != null){
            return new Response("failed","该任务已存在。");
        }
        task = new Task(own,triggerName,triggerGroup,cityId,cityName);
        taskService.creatTask(task);
        //cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        //根据name 和group获取当前trigger 的身份
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        CronTrigger triggerOld = null;
        Map<String,String> param = new HashMap<>();
        param.put("jobName","ozj123");
        param.put("jobGroup","27");
        param.put("description","测试param");
        quartzService.addJob(MailJob.class, triggerName, triggerGroup, cron, param);
        try {
            //获取 触发器的信息
            triggerOld = (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if (triggerOld == null) {
            //将job加入到jobDetail中
            JobDetail jobDetail = JobBuilder.newJob(MailJob.class).withIdentity(triggerName, triggerGroup).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName,triggerGroup).withSchedule(cronScheduleBuilder).build();
            //执行任务
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            System.out.println("当前job已存在--------------------------------------------");
        }
        return task;
    }

    /**
     * 暂停，删除，恢复任务
     * @param triggerName
     * @param triggerGroup
     * @param type
     * @param request
     * @return
     */
    @PostMapping("/control")
    public Response pause(@RequestParam(value = "triggerName") String triggerName , @RequestParam(value = "triggerGroup") String triggerGroup,
                        String type ,HttpServletRequest request) {
        User user = tokenService.getUserFromRequest(request);
        if (user == null){
            return new Response("failed","你的token已过期,请重新登录。");
        }
        Task task = null;
        if (triggerName != null && triggerGroup != null){
            task = taskService.findByTriggerNameAndGroup(triggerName,triggerGroup);
        }
        if (task == null){
            return new Response("failed","不存在对应的task");
        } else if (task.getOwn().getId() != user.getId()){
            return new Response("failed","该任务并不是你的哦。");
        }
        try {
            TriggerKey key = new TriggerKey(triggerName,triggerGroup);
            switch (type) {
                case PAUSE:
                    scheduler.pauseTrigger(key);
                    break;
                case REMOVE:
                    quartzService.deleteJob(triggerName,triggerGroup);
                    break;
                case RESUME:
                    scheduler.resumeTrigger(key);
                    break;
                default:
                    return new Response("failed", "请选择你的操作。");
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return new Response("failed","出现未知错误。");
        }
        return new Response("success",type + "任务成功。",task);
    }

    /**
     * 修改任务时间
     * @param triggerName
     * @param triggerGroup
     * @param cron
     * @param request
     * @return
     */
    @PatchMapping("/change")
    public Response changeCron(@RequestParam(value = "triggerName") String triggerName, @RequestParam(value = "triggerGroup") String triggerGroup,
                               @RequestParam(value = "cron") String cron, HttpServletRequest request){
        //cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        quartzService.updateJob(triggerName, triggerGroup, cron, null);
        return new Response("success","成功");
    }

    /**
     * 查看所有的任务
     * @param request
     * @return
     */
    @GetMapping
    public Response viewAllTasks(HttpServletRequest request){
        List<Task> list = taskService.findByUserId(tokenService.getUserFromRequest(request).getId());
        List<Trigger> triggers = new ArrayList<>();
        for (Task task : list){
            TriggerKey triggerKey = TriggerKey.triggerKey(task.getTriggerName(), task.getTriggerGroup());
            CronTrigger triggerOld = null;
            try {
                triggerOld = (CronTrigger) scheduler.getTrigger(triggerKey);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            if (triggerOld != null){
                triggers.add(triggerOld);
            }
        }
        return new Response("success","cg",triggers);
    }


}
