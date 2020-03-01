package ozj.weather.wf.mappers;

import org.apache.ibatis.annotations.Mapper;
import ozj.weather.wf.domain.User;

import java.util.List;

/**
 * 用户Mapper
 * @author ozj
 * @date 2020-02-28 19:40
 */
@Mapper
public interface UserMapper {

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     * 通过no查找用户
     * @param no
     * @return
     */
    User selectByNo(String no);

    /**
     * 查找所有的用户
     * @param state
     * @return
     */
    List<User> selectAllUser(Integer state);

    /**
     * 插入用户
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateById(User user);

    /**
     * 通过id删除用户
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过no删除用户
     * @param no
     */
    void deleteByNo(String no);
}
