package com.example.demo.manager;

import com.example.common.domain.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
@Component
public class AccountManager extends BaseManager<Account> {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public BaseRepository getRepository() {
        return accountRepository;
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
