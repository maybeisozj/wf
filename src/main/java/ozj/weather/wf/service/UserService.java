package ozj.weather.wf.service;

import ozj.weather.wf.domain.User;

import java.util.List;

/**
 * 用户管理类
 * @author ozj
 * @date 2020-02-28 19:30
 */
public interface UserService {

    /**
     * 新建用户
     * @param user
     * @return
     */
    int creat(User user);

    /**
     * 查找用户
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 查找用户
     * @param no
     * @return
     */
    User findByNo(String no);

    /**
     * 删除用户
     * @param user
     * @return
     */
    int deleteUserById(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 查找所有的用户
     * @param state
     * @return
     */
    List<User> findAllUsers(int state);
}
