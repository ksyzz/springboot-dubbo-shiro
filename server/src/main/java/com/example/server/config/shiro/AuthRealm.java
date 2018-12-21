package com.example.server.config.shiro;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.spring.boot.DubboProperties;
import com.example.common.domain.model.Account;
import com.example.common.service.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fengqian
 * @since <pre>2018/12/20</pre>
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private DubboProperties properties;

    private AccountService accountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Account account = (Account) principals.fromRealm(this.getClass().getName()).iterator().next();
        if (account == null) {
            throw new UnknownAccountException();
        }
        // TODO: 2018/12/20 fengqian 修改权限
        List<String> privileges = new ArrayList<>();
        privileges.add(account.getName());
        Set<String> roles = new HashSet<>();
        roles.add(account.getName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(privileges);
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        Account account = accountService.getByUsername(username);
        if (account == null) {
            throw new UnknownAccountException();
        }
        if (account.isDeleted()) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(account, usernamePasswordToken.getPassword(), this.getClass().getName());
    }

    @Override
    @Autowired
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 手动注入dubbo服务
     */
    public void setAccountService() {
        ReferenceConfig<AccountService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(properties.getApplication());
        referenceConfig.setRegistry(properties.getRegistry());
        referenceConfig.setInterface(AccountService.class);
        this.accountService = referenceConfig.get();
    }
}
