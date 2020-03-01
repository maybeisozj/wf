package ozj.weather.wf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ozj.weather.wf.domain.Task;
import ozj.weather.wf.mappers.TaskMapper;
import ozj.weather.wf.service.TaskService;

import java.util.List;

/**
 * 任务服务实现类
 * @author ozj
 * @date 2020-02-28
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 通过id查找具体task
     *
     * @param taskId
     * @return
     */
    @Override
    public Task findById(int taskId) {
        return taskMapper.selectByTaskId(taskId);
    }

    /**
     * 通过用户所有的task
     *
     * @param userId
     * @return
     */
    @Override
    public List<Task> findByUserId(int userId) {
        return taskMapper.selectByUserId(userId);
    }

    /**
     * 查找同一triggerName的所有task
     *
     * @param triggerName
     * @return
     */
    @Override
    public List<Task> findByTriggerName(String triggerName) {
        return taskMapper.selectByTriggerName(triggerName);
    }

    /**
     * 查找同一triggerGroup的所有task
     *
     * @param triggerGroup
     * @return
     */
    @Override
    public List<Task> findBuTriggerGroup(String triggerGroup) {
        return taskMapper.selectByTriggerGroup(triggerGroup);
    }

    /**
     * 创建一个新task
     *
     * @param task
     * @return
     */
    @Override
    public int creatTask(Task task) {
        return taskMapper.insertTask(task);
    }

    /**
     * 更新一个task
     *
     * @param task
     * @return
     */
    @Override
    public int updateTask(Task task) {
        return taskMapper.updateTask(task);
    }

    /**
     * 删除一个task
     *
     * @param task
     * @return
     */
    @Override
    public int deleteTask(Task task) {
        taskMapper.deleteTask(task);
        return task.getId();
    }

    /**
     * 通过triggerName和TriggerGroup查找task
     *
     * @param triggerName
     * @param triggerGroup
     */
    @Override
    public Task findByTriggerNameAndGroup(String triggerName, String triggerGroup) {
        return taskMapper.selectByTriggerNameAndGroup(triggerName,triggerGroup);
    }
}
