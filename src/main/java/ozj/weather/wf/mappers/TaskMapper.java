package ozj.weather.wf.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;
import ozj.weather.wf.domain.Task;

import java.util.List;

/**
 * @author ozj
 * @date 2020-02-28
 */
@Mapper
public interface TaskMapper {

    /**
     * 通过taskId选择
     * @param taskId
     * @return
     */
    Task selectByTaskId(int taskId);

    /**
     * 通过用户id选择
     * @param userId
     * @return
     */
    List<Task> selectByUserId(int userId);

    /**
     * 通过triggerName选择
     * @param triggerName
     * @return
     */
    List<Task> selectByTriggerName(String triggerName);

    /**
     * 通过triggerGroup选择
     * @param triggerGroup
     * @return
     */
    List<Task> selectByTriggerGroup(String triggerGroup);

    /**
     * 插入一个任务
     * @param task
     * @return
     */
    int insertTask(Task task);

    /**
     * 更新任务
     * @param task
     * @return
     */
    int updateTask(Task task);

    /**
     * 删除任务
     * @param task
     * @return
     */
    int deleteTask(Task task);

    /**
     * 通过triggerName和triggerGroup查找task
     * @param triggerName
     * @param triggerGroup
     * @return
     */
    Task selectByTriggerNameAndGroup(@Param(value = "triggerName") String triggerName,@Param(value = "triggerGroup") String triggerGroup);
}
