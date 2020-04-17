package org.daistudy.springbootshiro.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/notAuth");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 游客接口和登录接口，开放权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        // 用户接口，需要角色权限“user”
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        // 管理员接口，需要角色权限“admin”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");

        // 其余接口一律拦截，并放在所有权限设置的组后，不然会导致所有 url 都被拦截（短路机制）
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
