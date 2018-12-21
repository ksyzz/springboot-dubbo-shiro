package com.example.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.common.domain.model.Account;
import com.example.common.service.AccountService;
import com.example.demo.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
@Component
@Service(interfaceClass = AccountService.class)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountManager accountManager;

    @Override
    public Account getByUsername(String username) {
        return accountManager.findByUsername(username);
    }

    @Override
    public void saveOne(Account account) {
        accountManager.saveOne(account);
    }

    @Override
    public Account getOne(Long id) {
        return accountManager.getOne(id);
    }

    @Override
    public void updateOne(Account account) {
        accountManager.updateOne(account);
    }

    @Override
    public void deleteOne(Long id) {
        Account account = getOne(id);
        if (account == null) {
        }
        accountManager.deleteOne(account);
    }
}
