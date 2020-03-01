package ozj.weather.wf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ozj.weather.wf.domain.User;
import ozj.weather.wf.mappers.UserMapper;
import ozj.weather.wf.service.UserService;

import java.util.List;

/**
 * 用户管理实现类
 * @author ozj
 * @date 2020-02-28-1940
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 新建用户
     *
     * @param user
     * @return
     */
    @Override
    public int creat(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    @Override
    public User findById(int id) {
        return userMapper.selectById(id);
    }

    /**
     * 查找用户
     *
     * @param no
     * @return
     */
    @Override
    public User findByNo(String no) {
        return userMapper.selectByNo(no);
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    @Override
    public int deleteUserById(User user) {
        userMapper.deleteById(user.getId());
        return user.getId();
    }

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    /**
     * 查找所有的用户
     *
     * @return
     */
    @Override
    public List<User> findAllUsers(int state) {
        return userMapper.selectAllUser(state);
    }
}
