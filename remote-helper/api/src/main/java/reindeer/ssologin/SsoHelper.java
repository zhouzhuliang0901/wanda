package reindeer.ssologin;

import reindeer.ssologin.exception.SsoLoginException;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;

public class SsoHelper {

    private static String SSO_KEY = "SsoLogin";

    private static Long TIME_OUT = 5 * 1000 * 60L;

    private static final String SSO_PARAM = "ssols";

    private static final String EN_CODE = "UTF-8";

    public static String getSsoDes(String loginName) {
        return getSsoDes(loginName, SSO_KEY);
    }

    public static String getSafeSsoDes(String loginName) {
        return getSafeSsoDes(loginName, SSO_KEY);
    }

    @Deprecated
    public static String getEncoderSsoDes(String loginName) {
        return getEncoderSsoDes(loginName, SSO_KEY);
    }

    public static String getSsoDes(String loginName, Timestamp current) {
        return getSsoDes(loginName, SSO_KEY, current);
    }

    public static String getSafeSsoDes(String loginName, Timestamp current) {
        return getSafeSsoDes(loginName, SSO_KEY, current);
    }

    @Deprecated
    public static String getEncoderSsoDes(String loginName, Timestamp current) {
        return getEncoderSsoDes(loginName, SSO_KEY, current);
    }

    public static String getSsoDes(String loginName, String key) {
        return getSsoDes(loginName, key,
                new Timestamp(System.currentTimeMillis()));
    }

    public static String getSafeSsoDes(String loginName, String key) {
        return getSafeSsoDes(loginName, key,
                new Timestamp(System.currentTimeMillis()));
    }

    @Deprecated
    public static String getEncoderSsoDes(String loginName, String key) {
        return getEncoderSsoDes(loginName, key,
                new Timestamp(System.currentTimeMillis()));
    }

    public static String getSsoDes(String loginName, String key,
                                   Timestamp current) {
        try {
            return Utils.encode(current.toString() + "," + loginName, key);
        } catch (Exception e) {
            throw new SsoLoginException(e);
        }
    }

    public static String getSafeSsoDes(String loginName, String key,
                                       Timestamp current) {
        try {
            return Utils.safeEncode(current.toString() + "," + loginName, key);
        } catch (Exception e) {
            throw new SsoLoginException(e);
        }
    }

    @Deprecated
    public static String getEncoderSsoDes(String loginName, String key,
                                          Timestamp current) {
        try {
            String des = Utils
                    .encode(current.toString() + "," + loginName, key);
            return URLEncoder.encode(des, EN_CODE);
        } catch (Exception e) {
            throw new SsoLoginException(e);
        }
    }

    public static String getUser(String ssoDesString) {
        return getUser(ssoDesString, SSO_KEY);
    }

    public static String getSafeUser(String ssoDesString) {
        return getSafeUser(ssoDesString, SSO_KEY);
    }

    @Deprecated
    public static String getDecoderUser(String ssoDesString) {
        return getDecoderUser(ssoDesString, SSO_KEY);
    }

    public static String getUser(HttpServletRequest request) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getUser(ssoDesString, SSO_KEY);
    }

    public static String getSafeUser(HttpServletRequest request) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getSafeUser(ssoDesString, SSO_KEY);
    }

    @Deprecated
    public static String getDecoderUser(HttpServletRequest request) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getDecoderUser(ssoDesString, SSO_KEY);
    }

    public static String getUser(HttpServletRequest request, long timeOut) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getUser(ssoDesString, SSO_KEY, timeOut);
    }

    public static String getSafeUser(HttpServletRequest request, long timeOut) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getSafeUser(ssoDesString, SSO_KEY, timeOut);
    }

    @Deprecated
    public static String getDecoderUser(HttpServletRequest request, long timeOut) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getDecoderUser(ssoDesString, SSO_KEY, timeOut);
    }

    public static String getUser(HttpServletRequest request, String key) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getUser(ssoDesString, key);
    }

    public static String getSafeUser(HttpServletRequest request, String key) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getSafeUser(ssoDesString, key);
    }

    @Deprecated
    public static String getDecoderUser(HttpServletRequest request, String key) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getDecoderUser(ssoDesString, key);
    }

    public static String getUser(HttpServletRequest request, String key,
                                 long timeOut) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getUser(ssoDesString, key, timeOut);
    }

    public static String getSafeUser(HttpServletRequest request, String key,
                                     long timeOut) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getSafeUser(ssoDesString, key, timeOut);
    }

    @Deprecated
    public static String getDecoderUser(HttpServletRequest request, String key,
                                        long timeOut) {
        String ssoDesString = request.getParameter(SSO_PARAM);
        return getDecoderUser(ssoDesString, key, timeOut);
    }

    public static String getUser(String ssoDesString, String key) {
        return getUser(ssoDesString, key, TIME_OUT);
    }

    public static String getSafeUser(String ssoDesString, String key) {
        return getSafeUser(ssoDesString, key, TIME_OUT);
    }

    @Deprecated
    public static String getDecoderUser(String ssoDesString, String key) {
        return getDecoderUser(ssoDesString, key, TIME_OUT);
    }

    public static String getUser(String ssoDesString, String key, long timeOut) {
        try {
            if (ssoDesString == null)
                throw new SsoLoginException("the ssoDesString is not null");
            String des = Utils.decode(ssoDesString, key);
            String[] desArrays = des.split(",");
            if (desArrays.length <= 1) {
                throw new SsoLoginException("the des string is not match!");
            }
            long time = Timestamp.valueOf(desArrays[0]).getTime();
            if (System.currentTimeMillis() - time > timeOut) {
                throw new SsoLoginException("the des string is timeout!");
            }
            return desArrays[1];
        } catch (Exception e) {
            throw new SsoLoginException("the des string is error");
        }
    }

    public static String getSafeUser(String ssoDesString, String key,
                                     long timeOut) {
        try {
            if (ssoDesString == null)
                throw new SsoLoginException("the ssoDesString is not null");
            String des = Utils.decode(ssoDesString, key);
            String[] desArrays = des.split(",");
            if (desArrays.length <= 1) {
                throw new SsoLoginException("the des string is not match!");
            }
            long time = Timestamp.valueOf(desArrays[0]).getTime();
            if (System.currentTimeMillis() - time > timeOut) {
                throw new SsoLoginException("the des string is timeout!");
            }
            return desArrays[1];
        } catch (Exception e) {
            throw new SsoLoginException("the des string is error");
        }
    }

    @Deprecated
    public static String getDecoderUser(String ssoDesString, String key,
                                        long timeOut) {
        try {
            if (ssoDesString == null)
                throw new SsoLoginException("the ssoDesString is not null");
            ssoDesString = URLDecoder.decode(ssoDesString, EN_CODE);
            String des = Utils.decode(ssoDesString, key);
            String[] desArrays = des.split(",");
            if (desArrays.length <= 1) {
                throw new SsoLoginException("the des string is not match!");
            }
            long time = Timestamp.valueOf(desArrays[0]).getTime();
            if (System.currentTimeMillis() - time > timeOut) {
                throw new SsoLoginException("the des string is timeout!");
            }
            return desArrays[1];
        } catch (Exception e) {
            throw new SsoLoginException("the des string is error");
        }
    }
}
