package ozj.weather.wf.service;

import io.jsonwebtoken.Claims;
import ozj.weather.wf.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * token服务类
 * @author ozj
 * @date 2020-02-29 10:50
 */
public interface TokenService {

    /**
     * 得到user的token
     * @param user
     * @return
     */
    String getToken(User user);

    /**
     * 取得token对应的Claims
     * @param token
     * @return
     */
    Claims getClaims(String token);

    /**
     * 取得token里加入的Id
     * @param token
     * @return
     */
    int getIdFromToken(String token);

    /**
     * 取得token里加入的No
     * @param token
     * @return
     */
    String getNoFromToken(String token);

    /**
     * 取得token里加入的Name
     * @param token
     * @return
     */
    String getNameFromToken(String token);

    /**
     * 取得token里的email
     * @param token
     * @return
     */
    String getEmailFromToken(String token);

    /**
     * 从request里取得token，从数据库取得user后返回
     * @param request
     * @return
     */
    User getUserFromRequest(HttpServletRequest request);
}
