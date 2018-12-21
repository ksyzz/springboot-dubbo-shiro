package com.example.server.controller.api_v1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengqian
 * @since <pre>2018/12/20</pre>
 */
@RestController
@RequestMapping("/api/v1/")
public class LoginController {

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        try {
            subject.login(token);
        } catch (UnknownAccountException e1) {
            return "帐号不存在";
        } catch (LockedAccountException e2) {
            return "帐号被冻结";
        } catch (AuthenticationException e3) {
            return "密码错误";
        }
        return "success";

    }

    @GetMapping("/logout")
    public void logout(){
        SecurityUtils.getSubject().logout();
    }


}
