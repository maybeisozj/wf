package ozj.weather.wf.service;

import ozj.weather.wf.domain.Task;

import java.util.List;

/**
 * @author ozj
 * @date 2020-02-28
 */
public interface TaskService {

    /**
     * 通过id查找具体task
     * @param taskId
     * @return
     */
    Task findById(int taskId);

    /**
     * 通过用户所有的task
     * @param userId
     * @return
     */
    List<Task> findByUserId(int userId);

    /**
     * 查找同一triggerName的所有task
     * @param triggerName
     * @return
     */
    List<Task> findByTriggerName(String triggerName);

    /**
     * 查找同一triggerGroup的所有task
     * @param triggerGroup
     * @return
     */
    List<Task> findBuTriggerGroup(String triggerGroup);

    /**
     * 创建一个新task
     * @param task
     * @return
     */
    int creatTask(Task task);

    /**
     * 更新一个task
     * @param task
     * @return
     */
    int updateTask(Task task);

    /**
     * 删除一个task
     * @param task
     * @return
     */
    int deleteTask(Task task);

    /**
     * 通过triggerName和TriggerGroup查找task
     * @param triggerName
     * @param triggerGroup
     */
    Task findByTriggerNameAndGroup(String triggerName, String triggerGroup);
}
