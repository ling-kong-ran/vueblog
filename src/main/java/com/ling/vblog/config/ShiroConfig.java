package com.ling.vblog.config;

import com.ling.vblog.shiro.CustomRealm;
import com.ling.vblog.shiro.JwtFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@SuppressWarnings("all")
public class ShiroConfig {
    @Autowired
    JwtFilter jwtFilter;

    //创建自定义ream
    @Bean
    public CustomRealm getcustomReam(){
        return new CustomRealm();
    }
    //创建session管理器
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager defaultSessionManager = new DefaultWebSessionManager();
        defaultSessionManager.setSessionDAO(redisSessionDAO);
        return defaultSessionManager;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager getSecurityManager(CustomRealm realm,
                                              SessionManager sessionManager,
                                              RedisCacheManager redisCacheManager){
        DefaultWebSecurityManager sm= new DefaultWebSecurityManager(realm);
        sm.setSessionManager(sessionManager);
        sm.setCacheManager(redisCacheManager);
        return sm;
    }


    //ShiroFilterChainDefinition
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition=new DefaultShiroFilterChainDefinition();
        Map<String, String> filter=new LinkedHashMap<>();
        filter.put("/**","jwt");
        shiroFilterChainDefinition.addPathDefinitions(filter);
        return shiroFilterChainDefinition;
    }

    //创建shiroFilter工厂
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getshiroFilter(DefaultWebSecurityManager securityManager,ShiroFilterChainDefinition shiroFilterChainDefinition){
        //1.创建过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //3.通用配置（跳转登录页面，未授权跳转的页面）
        //shiroFilterFactoryBean.setLoginUrl("/author?code=1");//未登录跳转url
        //shiroFilterFactoryBean.setUnauthorizedUrl("/author?code=2");//未授权跳转url
        //4.设置自定义的jwt过滤器
        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt",jwtFilter);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    /*
    开启注解支持
    * */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);

        return defaultAdvisorAutoProxyCreator;
    }


}
