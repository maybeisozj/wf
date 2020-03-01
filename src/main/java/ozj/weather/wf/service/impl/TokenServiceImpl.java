package ozj.weather.wf.service.impl;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ozj.weather.wf.domain.User;
import ozj.weather.wf.service.TokenService;
import ozj.weather.wf.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Objects;

/**
 * @author ozj
 * @date 2020-02-29 10:50
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private UserService userService;

    private static String SECRET = "qwerasdfdxzvdfajjlkjeiojznvxndjkfaowijeiodl";

    private static final String authorization = "Authorization";

    private static final String split = " ";

    private static final long time = (long) 30 * 24 * 60 * 60 * 1000;

    /**
     * 得到user的token
     * @param user
     * @return
     */
    @Override
    public String getToken(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //创建时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date end = new Date(nowMillis + time);
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(end)
                .setAudience(String.valueOf(user.getId()))
                .claim("no", user.getNo())
                .claim("password" , user.getPassword())
                .claim("name" , user.getName())
                .claim("email", user.getEmail())
                .signWith(signatureAlgorithm, SECRET);
        return builder.compact();
    }
    /**
     * 取得token对应的Claims
     * @param token
     * @return
     */
    @Override
    public Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        }
        return claims;
    }
    /**
     * 取得token里加入的Id
     * @param token
     * @return
     */
    @Override
    public int getIdFromToken(String token) {
        return Integer.parseInt(getClaims(token).getAudience());
    }
    /**
     * 取得token里加入的No
     * @param token
     * @return
     */
    @Override
    public String getNoFromToken(String token) {
        return (String) getClaims(token).get("no");
    }

    /**
     * 取得token里加入的Name
     *
     * @param token
     * @return
     */
    @Override
    public String getNameFromToken(String token) {
        return (String) getClaims(token).get("name");
    }

    /**
     * 取得token里的email
     *
     * @param token
     * @return
     */
    @Override
    public String getEmailFromToken(String token) {
        return (String) getClaims(token).get("email");
    }

    /**
     * 从request里取得token，从数据库取得user后返回
     *
     * @param request
     * @return
     */
    @Override
    public User getUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader(authorization).split(split)[1];
        //从request里取得token失败；
        if (token == null || Objects.equals(token, "")) {
            return null;
        }
        Claims claims = getClaims(token);
        if (claims == null) {
            return null;
        }
        int id = Integer.parseInt(claims.getAudience());
        return userService.findById(id);
    }
}
