package com.sundata.edu.framework.configurer;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sundata.edu.framework.shrio.realm.UserRealm;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.*;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    /**
     * 安全管理器
     */
    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm);
        // 注入缓存管理器;
        securityManager.setCacheManager(getEhCacheManager());
        // session管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 将自己的验证方式加入容器
     *
     * @return
     */
    @Bean
    public UserRealm userRealm(EhCacheManager cacheManager) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }


    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }


    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        CacheManager cacheManager = CacheManager.getCacheManager("growth");
        EhCacheManager em = new EhCacheManager();
        if (cacheManager == null) {
            em.setCacheManager(new CacheManager(getCacheManagerConfigFileInputStream()));
            return em;
        } else {
            em.setCacheManager(cacheManager);
            return em;
        }
    }

    /**
     * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
     */
    protected InputStream getCacheManagerConfigFileInputStream() {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 会话管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        //设置不同的sessionIdCookie解决多个项目shiro的session污染问题
        manager.setSessionIdCookieEnabled(true);
        manager.setSessionIdCookie(new SimpleCookie("growth"));
        // 加入缓存管理器
        manager.setCacheManager(getEhCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 设置全局session超时时间(8小时)
        manager.setGlobalSessionTimeout(1000 * 60 * 60 * 8);
        // 去掉 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // 定义要使用的无效的Session定时调度器
        manager.setSessionValidationScheduler(sessionValidationScheduler());
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
        manager.setSessionFactory(sessionFactory());
        return manager;
    }

    /**
     * 自定义sessionFactory调度器
     */
    @Bean
    public SessionValidationScheduler sessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        // 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟 此时设置5分钟
        sessionValidationScheduler.setInterval(60 * 1000 * 5);
        // 设置会话验证调度器进行会话验证时的会话管理器
        sessionValidationScheduler.setSessionManager(sessionValidationManager());
        return sessionValidationScheduler;
    }

    /**
     * 在线WEB会话管理器
     */
    @Bean
    public DefaultWebSessionManager sessionValidationManager() {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        // 加入缓存管理器
        manager.setCacheManager(getEhCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 设置全局session超时时间(8小时)
        manager.setGlobalSessionTimeout(1000 * 60 * 60 * 8);
        // 去掉 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
        manager.setSessionFactory(sessionFactory());
        return manager;
    }

    /**
     * 自定义sessionDAO会话
     */
    @Bean
    public EnterpriseCacheSessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    /**
     * 自定义sessionFactory会话
     */
    @Bean
    public SessionFactory sessionFactory() {
        return new SimpleSessionFactory();
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl("/loginOut");
        //身份认证成功跳转页面配置
        shiroFilterFactoryBean.setSuccessUrl("/");
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/ajax/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/errors", "anon");
        filterChainDefinitionMap.put("/unauth", "anon");

        filterChainDefinitionMap.put("/sso/login/**", "anon");
        filterChainDefinitionMap.put("/sso/index/**", "anon");
        filterChainDefinitionMap.put("/psy/videoplay", "anon");
        filterChainDefinitionMap.put("/psychology/course/getctId", "anon");
        filterChainDefinitionMap.put("/psychology/course/getzj", "anon");
        filterChainDefinitionMap.put("/psychology/course/getQuestions", "anon");
        filterChainDefinitionMap.put("/psychology/course/downloadFile", "anon");

        //filterChainDefinitionMap.put("/psychology/login", "anon");

        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/api/**", "anon");

        //电子工作证检验页面
 //       filterChainDefinitionMap.put("/h5/rsjsindex/**", "anon");

        //测试跳过登录
//        filterChainDefinitionMap.put("/h5/dzgzzhindex", "anon");
//        filterChainDefinitionMap.put("/dzgzzh/basedata/getList", "anon");
//        filterChainDefinitionMap.put("/dzgzzh/basedata/getTongBuMenList", "anon");



//        filterChainDefinitionMap.put("/dzgzzh/basedata/getUrl","anon");
//        filterChainDefinitionMap.put("/dzgzzh/dzgzzhjy","anon");
//        filterChainDefinitionMap.put("/dzgzzh/basedata/getGzzhBasedata","anon");



        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");


//        Map<String, Filter> filters = new LinkedHashMap<>();
//        filters.put("onlineSession", onlineSessionFilter());
//        filters.put("syncOnlineSession", syncOnlineSessionFilter());
//        filters.put("captchaValidate", captchaValidateFilter());
//        // 注销成功，则跳转到指定页面
//        filters.put("logout", logoutFilter());
//        shiroFilterFactoryBean.setFilters(filters);

        // 所有请求需要认证
        filterChainDefinitionMap.put("/**", "user,perms,roles");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * thymeleaf模板引擎和shiro框架的整合
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 开启Shiro注解通知器 加入注解的使用，不加入这个注解不生效
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
