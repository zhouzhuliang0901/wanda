package reindeer.shiro.realm;

import com.wondersgroup.sms.user.bean.SmsUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reindeer.shiro.config.Status;
import reindeer.shiro.token.Token;
import reindeer.utils.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    private Status status;


    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
    }

    /**
     * 登录认证
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");

        String token = (String) authenticationToken.getCredentials();
// 解密获得username，用于和数据库进行对比
        SmsUser jwtUser = JwtUtil.getUserInfo(token);

        if (jwtUser == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        if (!JwtUtil.verify(token, jwtUser)) {
            throw new AuthenticationException("token认证失败！");
        }
        return new SimpleAuthenticationInfo(jwtUser, token, "AccountRealm");
    }

    /**
     * 用户授权
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        SmsUser user = JwtUtil.getUserInfo(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();

        // 设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

}
