package com.example.server.config.shiro;

import com.example.common.domain.model.Account;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * 自定义shiro密码校验
 * @author fengqian
 * @since <pre>2018/12/20</pre>
 */
@Component
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        String inpassword = new String(utoken.getPassword());
        Account account = (Account) info.getPrincipals().iterator().next();
        String dbpassword = account.getPassword();
        return this.equals(inpassword, dbpassword);
    }
}
