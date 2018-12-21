package com.example.server.config;

import com.example.server.config.shiro.AuthRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private AuthRealm authRealm;


    private static Map<String, String> filterChain(){
        Map<String, String> filterChain = new LinkedHashMap<>();
        filterChain.put("/api/v1/account/self", "authc");
        filterChain.put("/api/v1/login", "anon");
        filterChain.put("/api/v1/account/one", "perms[ksyzz]");
        filterChain.put("/index", "authc");
        filterChain.put("/logout", "authc");
        filterChain.put("/unlogin", "anon");
        filterChain.put("/read", "perms[admin]");
        filterChain.put("/static/**", "anon");
        filterChain.put("/**", "authc");
        return filterChain;
    }

    @Bean
    public SessionsSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        authRealm.setAccountService();
        securityManager.setRealm(authRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置登录
        shiroFilterFactoryBean.setLoginUrl("/unlogin");
        // 设置无权限跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/unrole");
        // 设置登录成功后跳转
        shiroFilterFactoryBean.setSuccessUrl("/index");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = filterChain();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }


}
