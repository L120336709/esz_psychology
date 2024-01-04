package com.sundata.edu.framework.shrio.realm;

import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.service.SysMenuService;
import com.sundata.edu.service.SysRoleService;
import com.sundata.edu.util.ShiroUtils;
import com.sundata.edu.framework.exception.ServiceException;
import com.sundata.edu.framework.shrio.service.LoginService;
import com.sundata.edu.vo.UserinfoVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm 处理登录 权限
 */
public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 角色加入AuthorizationInfo认证对象
        info.setRoles(sysRoleService.getRoleCodeByUserId(ShiroUtils.getUserId()));
        // 权限加入AuthorizationInfo认证对象
        info.setStringPermissions(sysMenuService.getPermsByUserId(ShiroUtils.getUserId()));
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String accessToken = "";
        if (upToken.getPassword() != null) {
            accessToken = new String(upToken.getPassword());
        }
        UserinfoVo user = null;
        try {
            user = loginService.login(accessToken);
        } catch (Exception e) {
            logger.info("对用户[" + accessToken + "]进行登录验证..验证未通过{}", e);
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
//    /**
//     * 登录认证
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String userId = token.getUsername();
//        String password = "";
//        if (token.getPassword() != null) {
//            password = new String(token.getPassword());
//        }
//        Userinfo userinfo;
//        try {
//            userinfo = loginService.login(userId);
//        } catch (ServiceException ex) {
//            throw new AuthenticationException(ex.getMessage(), ex);
//        } catch (Exception ex) {
//            logger.error("对用户[" + userId + "]进行登录验证..验证未通过" + ex.getMessage(), ex);
//            throw new AuthenticationException(ex.getMessage(), ex);
//        }
//        return new SimpleAuthenticationInfo(userinfo, password, getName());
//    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }


}
