package com.sundata.edu.util;

import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.shrio.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

/**
 * @Description TODO
 * @Author whj
 * @Date 2018-12-21 23:28
 * @Version 1.0
 */
public class ShiroUtils {

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout() {
        getSubjct().logout();
    }

    public static Userinfo getUserInfo() {
        Userinfo userinfo = null;
        Object obj = getSubjct().getPrincipal();
        if (obj != null) {
            userinfo = new Userinfo();
            BeanUtils.copyProperties(obj, userinfo);
        }
        return userinfo;
    }

    public static void setUserInfo(Userinfo userinfo) {
        Subject subject = getSubjct();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(userinfo, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    public static String getUserId() {
        return getUserInfo().getUserId();
    }

    public static String getRealName() {
        return getUserInfo().getRealName();
    }

    public static String getOrgId() {
        return getUserInfo().getOrgId();
    }

    public static Integer getIdentity() {
        return getUserInfo().getIdentity();
    }

    public static Integer getStatus() {
        return getUserInfo().getStatus();
    }

    public static String getIp() {
        return getSubjct().getSession().getHost();
    }

    public static String getSessionId() {
        return String.valueOf(getSubjct().getSession().getId());
    }
}
