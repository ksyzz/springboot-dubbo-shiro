package com.example.server.controller.api_v1;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.common.domain.model.Account;
import com.example.common.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Reference
    private AccountService accountService;

    @GetMapping("/one")
    public Account getOne(
            @RequestParam("id") Long id
    ){
        return accountService.getOne(id);
    }

    @GetMapping("/self")
    public Account getSelf(){
        return (Account) SecurityUtils.getSubject().getPrincipal();
    }
}
