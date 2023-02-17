package reindeer.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wondersgroup.sms.user.bean.SmsUser;
import tw.ecosystem.reindeer.config.RdConfig;

import java.util.Date;

public class JwtUtil {

    static {
        SECRET = RdConfig.get("jwt.secret");
        EXPIRE_TIME = Long.parseLong(RdConfig.get("jwt.expireTime"));
    }

    /**
     * 用户id
     */
    private static final String ID = "id";
    /**
     * 用户昵称
     */
    private static final String USER_NAME = "username";
    /**
     * 用户状态
     */
    private static final String STATUS = "status";

    /**
     * 过期时间5分钟
     */
    private static long EXPIRE_TIME;
    /**
     * 秘钥
     */
    private static String SECRET;

    /**
     * 从配置文件中注入秘钥
     *
     * @param secret 秘钥
     */
    static void setSecret(String secret) {
        SECRET = secret;
    }

    /**
     * 从配置文件中注入秘钥
     *
     * @param expireTime 存活时间
     */
    static void setExpireTime(long expireTime) {
        EXPIRE_TIME = expireTime;
    }

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token, SmsUser user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(ID, user.getStUserId())
                    .withClaim(USER_NAME, user.getStUserName())
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static SmsUser getUserInfo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            SmsUser user = new SmsUser();
            user.setStUserId(jwt.getClaim(ID).asString());
            user.setStUserName(jwt.getClaim(USER_NAME).asString());
            return user;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param user 用户信息
     * @return 加密的token
     */
    public static String sign(SmsUser user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //加密算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 附带username信息
        return JWT.create()
                .withClaim(ID, user.getStUserId())
                .withClaim(USER_NAME, user.getStUserName())
                //结束时间
                .withExpiresAt(date)
                //创建jwt使用指定算法进行加密
                .sign(algorithm);
    }

}
