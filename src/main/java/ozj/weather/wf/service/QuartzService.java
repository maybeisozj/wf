package ozj.weather.wf.service;

import java.util.Map;

/**
 * 服务类
 * @author ozj
 * @date 2020-02-28 15:19
 */
public interface QuartzService {

    /**
     * 添加任务可以传参数
     *  @param clazz
     * @param jobName
     * @param groupName
     * @param cronExp
     * @param param
     */
    void addJob(Class clazz, String jobName, String groupName, String cronExp, Map<String, String> param);

    /**
     * 暂停任务
     *
     * @param name
     * @param groupName
     */
    void pauseJob(String name, String groupName);

    /**
     * 恢复任务
     *
     * @param name
     * @param groupName
     */
    void resumeJob(String name, String groupName);

    /**
     * 更新任务
     *
     * @param name
     * @param groupName
     * @param cronExp
     * @param param
     */
    void updateJob(String name, String groupName, String cronExp, Map<String, Object> param);

    /**
     * 删除任务
     *
     * @param name
     * @param groupName
     */
    void deleteJob(String name, String groupName);

    /**
     * 启动所有任务
     */
    void startAllJobs();

    /**
     * 关闭所有任务
     */
    void shutdownAllJobs();
}
