package ozj.weather.wf.controller;

import cn.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ozj.weather.wf.core.Response;
import ozj.weather.wf.domain.User;
import ozj.weather.wf.service.MailService;
import ozj.weather.wf.service.TokenService;
import ozj.weather.wf.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户控制类
 * @author ozj
 * @date 2020-02-28 17:00
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @Autowired
    TokenService tokenService;

    /**
     * 用户注册
     * @param no
     * @param name
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Response creatUser(@RequestParam(value = "no") String no, @RequestParam(value = "name") String name,
                              @RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
        if (!isEmail(email)){
            return new Response("failed","检查你的邮箱格式。");
        }
        User user = new User(no,name,email,SecureUtil.md5(password));
        userService.creat(user);
        mailService.sendMailForTest(email,"验证账号","http://localhost:8080/users/test?code=" + user.getId());
        return new Response("success","请验证你的邮箱",user);
    }

    /**
     * 用户登录
     * @param no
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Response getLogin(@RequestParam(value = "no") String no, @RequestParam(value = "password") String password){
        User user = userService.findByNo(no);
        if (user.getPassword().equals(SecureUtil.md5(password))){
            String token = tokenService.getToken(user);
            return new Response("success","保护好你的token。",token);
        } else {
            return new Response("failed","登录失败了，请检查你的账号和密码是否正确");
        }
    }

    /**
     * 用户邮箱验证
     * @param id
     * @return
     */
    @GetMapping("/test")
    public User activeUser(@RequestParam(value = "code") int id){
        User user = userService.findById(id);
        userService.updateUser(user);
        return user;
    }

    /**
     * 修改密码
     * @param password
     * @param newPassword
     * @param confirmPassword
     * @param request
     * @return
     */
    @PatchMapping("/change")
    public Response changePassword(@RequestParam(value = "password") String password, @RequestParam(value = "newPassword") String newPassword,
                                   @RequestParam(value = "confirmPassword") String confirmPassword, HttpServletRequest request){
        User user = tokenService.getUserFromRequest(request);
        if (user.getPassword().equals(SecureUtil.md5(password))){
            if (newPassword != null && newPassword != "" && newPassword.equals(confirmPassword)){
                user.setPassword(SecureUtil.md5(confirmPassword));
                userService.updateUser(user);
                String token = tokenService.getToken(user);
                return new Response("success", "修改密码成功,接收你的新token",token);
            } else {
                return new Response("failed","修改密码失败，请确认你两次新密码一致");
            }
        } else {
            return new Response("failed","密码错误");
        }
    }

    /**
     * 检测邮箱地址是否合法
     *
     * @param email 需验证的邮箱
     * @author ozj
     * @return true合法 false不合法
     */
    private static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
