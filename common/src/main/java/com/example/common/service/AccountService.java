package com.example.common.service;

import com.example.common.domain.model.Account;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
public interface AccountService extends BaseService<Account> {

    /**
     * 根据用户名查找帐号
     * @param username
     * @return
     */
    Account getByUsername(String username);
}
