package com.example.demo.repository;

import com.example.common.domain.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @author fengqian
 * @since <pre>2018/12/19</pre>
 */
@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {
    Account findByUsername(String username);
}
